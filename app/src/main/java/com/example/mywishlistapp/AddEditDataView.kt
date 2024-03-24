package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mywishlistapp.Data.WishItem
import com.example.mywishlistapp.ui.theme.MyWishlistAppTheme
import kotlinx.coroutines.launch

@Composable
fun AddEditDataView(wishViewModel: WishViewModel,
                    id:Long,
                    navController: NavController,
                    ){

    var context= LocalContext.current
    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    //make sure that outfield id fill with the wish when go for update
    if (id!=0L){
        val wish=wishViewModel.getAWish(id).collectAsState(initial = WishItem(0L,"",""))
        wishViewModel.wishTitle=wish.value.wish
        wishViewModel.wishDescription=wish.value.description
    }else{
        wishViewModel.wishTitle=""
        wishViewModel.wishDescription=""
    }




    Scaffold (
        topBar = {
            TopAppBar(title =
            if (id!=0L) stringResource(id = R.string.update_data) else stringResource(id = R.string.add_data)
            ){
                navController.navigateUp()
            }
        },
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = wishViewModel.wishTitle,
                onValueChange = {
                    wishViewModel.wishTitleChange(it)},
                label ="Wish")

            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = wishViewModel.wishDescription,
                onValueChange = {
                    wishViewModel.wishDescriptionChange(it)},
                label ="Description")

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (wishViewModel.wishTitle.isNotEmpty()&&
                    wishViewModel.wishDescription.isNotEmpty()){
                    if (id!=0L){
                        wishViewModel.updateWish(WishItem(
                            //id must be same with the previous id that's why store in the same row
                            id=id,
                            wishViewModel.wishTitle.trim(),
                            wishViewModel.wishDescription
                        ))

                    }else{
                        wishViewModel.addWish(WishItem(
                            wish = wishViewModel.wishTitle.trim(),
                            description = wishViewModel.wishDescription,
                        ))
                    }
                }else{
                    Toast.makeText(context, "Enter a wish to save",Toast.LENGTH_LONG).show()
                }
                scope.launch {
                   //
                    navController.navigateUp()
                }

            }) {
                Text(
                    text = if (id != 0L) {
                        stringResource(id = R.string.update_data)
                    }else{
                        stringResource(id = R.string.add_data)
                    }
                )
            }
        }
    }
}


@Composable
fun TextField(
    value:String,
    onValueChange:(String)->Unit,
    label:String
){
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors=TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.focus_colour),
            focusedLabelColor = colorResource(id = R.color.focus_colour),
            unfocusedBorderColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            // The cursor is a small, blinking line or symbol that indicates where the next character will be inserted when typing.
            cursorColor = colorResource(id = R.color.black),
        )
    )
}


@Preview(showBackground = true)
@Composable
fun AddEditDataViewPreview() {
    MyWishlistAppTheme {
        AddEditDataView(wishViewModel = WishViewModel(), id = 0, rememberNavController())
    }
}