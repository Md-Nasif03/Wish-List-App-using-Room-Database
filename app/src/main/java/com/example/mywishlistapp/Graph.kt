package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.Data.WishDataBase
import com.example.mywishlistapp.Data.WishRepository

/*Kotlin objects are singletons, which means that there can only be one instance of the Graph object.
this mean it can be implemented only by once. that,s why it create only one data base location
 */
object Graph {
    //The lateinit keyword indicates that the variable will be initialized later.
    private lateinit var database:WishDataBase

    /*The by lazy keyword indicates that the wishRepository property
    will only be initialized when it is first accessed.*/

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    //function initializes the database variable using the Room.databaseBuilder() method.
    fun Provide(context: Context){
        /*The Room.databaseBuilder() method creates a new instance of
        WishDataBase using the provided context, database class, and database name. */
        database=Room.databaseBuilder(context,WishDataBase::class.java,"wishList.db").build()
    }

}