- 👋 Hi, I’m Md Nasif
- 👀 I’m interested in Android development
- 🌱 I’m currently learning Android development using Jetpack compose in Kotlin
- 📫 Gmail: mdnasif03@gmail.com
- 😄 Pronouns: He/Him
# Wish List App
This Android Studio project is a wishlist app that allows users to create, update, and delete wishlist items. The app uses Jetpack Compose 
for the UI and Room for the database.
# Key features:
- Create, update, and delete wishlist items
- Swipe to delete individual wishlist items
- View a list of all wishlist items
- Edit individual wishlist items
- Navigate between the home screen and the add/edit screen
# Data Model:
The WishItem data class represents a single wishlist item. It has three properties:
- id: The unique identifier for the wishlist item.
- wish: The name of the wishlist item.
- description: A description of the wishlist item.
# Database:
- The WishDataBase class represents the database for the app. It contains a single table, Wish_Table, which stores the WishItem objects.
# Repository:
- The WishRepository class provides access to the database operations. It has methods for adding, updating, deleting, and retrieving wishlist items.
# UI:
- The app uses Jetpack Compose to create the UI. The HomeView composable function displays a list of all wishlist items. The AddEditDataView composable function allows users to create or edit a wishlist item.
# Navigation:
- The app uses the Navigation component to navigate between the home screen and the add/edit screen. The NavHost composable function defines the navigation graph for the app.
