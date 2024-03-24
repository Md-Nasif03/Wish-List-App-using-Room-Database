package com.example.mywishlistapp.Data

import androidx.room.Database
import androidx.room.RoomDatabase


//build database here
//make a instance of database
@Database(
    /* entities = [WishItem::class]: This specifies that the WishItem class
    is the only entity that will be stored in this database. */
    entities = [WishItem::class],
    version = 1,
    exportSchema = false
)

//store the data
abstract class WishDataBase:RoomDatabase() {
    /* WishDao: This declares an abstract method called wishDao()
    that returns an instance of the WishDao interface.*/
    abstract fun wishDao():WishDao
}