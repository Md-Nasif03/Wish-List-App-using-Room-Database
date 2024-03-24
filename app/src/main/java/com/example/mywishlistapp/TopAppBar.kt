package com.example.mywishlistapp


import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywishlistapp.ui.theme.MyWishlistAppTheme

// This composable function creates a TopAppBar, which is a material design component that represents the top bar of an app.
@Composable
fun TopAppBar(
    // The title of the TopAppBar.
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    // Create a TopAppBar composable from the androidx.compose.material library.
    TopAppBar(
        // Set the title of the TopAppBar.
        title = {
            // Create a Text composable to display the title.
            Text(
                // Set the text of the Text composable to the title.
                text = title,
                // Set the color of the Text composable to white.
                color = colorResource(id = R.color.white),
                // Set the modifier of the Text composable to add padding and a maximum height.
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        // Set the elevation of the TopAppBar to 3dp.
        elevation = 3.dp,
        // Set the background color of the TopAppBar to the color resource app_bar_colour.
        backgroundColor = colorResource(id = R.color.app_bar_colour),
        navigationIcon = {
            if (!title.contains("Wish List App")){
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(imageVector =
                    Icons.AutoMirrored.Filled.ArrowBack,
                        tint = colorResource(id = R.color.white),
                        contentDescription ="back")
                }
            }else{
                null
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    MyWishlistAppTheme {
        TopAppBar(title = "nasif")
    }
}