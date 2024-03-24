package com.example.mywishlistapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mywishlistapp.Data.WishItem
import com.example.mywishlistapp.ui.theme.MyWishlistAppTheme



// This composable function creates a HomeView, which is the main screen of the app.
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    wishViewModel: WishViewModel,
    navController: NavController
) {
    /* Create a Scaffold composable, which provides a default layout structure for the app.
    This layout structure is provided by the Scaffold composable in Jetpack Compose.
    The Scaffold composable takes several parameters that allow you to customize the layout,
     such as the top bar, bottom bar, and floating action button. */
    Scaffold(
        // Set the top bar of the Scaffold to a TopAppBar composable with the title "Wish List App".
        topBar = { TopAppBar(title = "Wish List App",{})},

        floatingActionButton = {
            FloatingActionButton(
                onClick =  {navController.navigate(Screen.AddScreen.route + "/0L")},
                modifier = Modifier.padding(20.dp),
                contentColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.black)
                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="Add" )
            }
        }
    ) { innerPadding ->

        val wishList=wishViewModel.getAllWishes.collectAsState(initial = listOf())
        // Create a LazyColumn composable, which is a vertically scrolling list.
        LazyColumn(
            // Set the modifier of the LazyColumn to fill the maximum size and add padding.
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(wishList.value,
                // this code arrange the sequence the list if we delete a list from up
                key = {wishItem->wishItem.id}){
                wishItem->
                val dismissState= rememberDismissState(
                    confirmStateChange = {
                        if (it== DismissValue.DismissedToEnd||it==DismissValue.DismissedToStart){
                            wishViewModel.deleteWish(wishItem)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    //swipe direction
                    directions = setOf(DismissDirection.EndToStart),
                    //how much have to swipe
                    dismissThresholds = {FractionalThreshold(0.75f)},
                    state = dismissState,
                    background = {
                                 //make colour when dismiss in background
                                 val colour by animateColorAsState(
                                     if (dismissState.dismissDirection==DismissDirection.EndToStart){
                                         Color.Red
                                     }else{
                                         Color.Transparent
                                     }
                                 )
                        Box(
                            modifier = Modifier
                                .fillMaxSize().background(colour)
                                .padding(20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "Null",
                                tint = Color.White
                                )
                        }

                    },
                    dismissContent = {
                        Wish(wishItem = wishItem) {
                            val id=wishItem.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    })



            }
        }
    }
}

@Composable
fun Wish(wishItem: WishItem,onClick:()->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 5.dp, end = 5.dp)
            .clickable { onClick() },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.white)
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ){
            Text(text = wishItem.wish, fontWeight = FontWeight.ExtraBold, color = colorResource(id = R.color.black))
            Text(text = wishItem.description,color = colorResource(id = R.color.black))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWishlistAppTheme {
        HomeView(viewModel(), rememberNavController())
    }
}