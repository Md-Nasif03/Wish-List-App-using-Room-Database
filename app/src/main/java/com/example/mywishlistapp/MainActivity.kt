package com.example.mywishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mywishlistapp.ui.theme.MyWishlistAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWishlistAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel())
                }
            }
        }
    }
}


@Composable
fun MyApp(viewModel: WishViewModel ){
    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route){
            HomeView(viewModel,navControl)

        }
        composable(Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type= NavType.LongType
                    defaultValue=0L
                    nullable=false
                }
            )
            ){

            //if we click add button then no id will pass(0L) but when we click to update it will pass the item id
            val id = if (it.arguments!=null) it.arguments!!.getLong("id") else 0L
            AddEditDataView(wishViewModel =viewModel , id = id,navControl)
        }

    }
}
/* if we want to pass more then one data to second screen.
fun MyApp(viewModel: WishViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeView(viewModel, navController)
        }

        composable(
            route = Screen.AddScreen.route + "/{id}/{name}/{age}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("age") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getLong("id")
            val name = backStackEntry.arguments!!.getString("name")!!
            val age = backStackEntry.arguments!!.getString("age")!!

            AddEditDataView(wishViewModel = viewModel, id = id, name = name, age = age, navController)
        }
    }
}
 */



