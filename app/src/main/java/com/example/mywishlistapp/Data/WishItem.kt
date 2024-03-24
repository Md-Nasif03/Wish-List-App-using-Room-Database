package com.example.mywishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Wish_Table")
data class WishItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("Wish")
    val wish:String="",
    @ColumnInfo("Description")
    val description:String=""
)

