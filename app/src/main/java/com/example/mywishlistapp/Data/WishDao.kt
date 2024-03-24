package com.example.mywishlistapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {

    /* OnConflictStrategy.IGNORE, which means that if a conflict occurs when
     inserting a new wish item (e.g., if a wish item with the same
     ID already exists), the operation will be ignored. */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity:WishItem)

    @Update
    abstract suspend fun updateAWish(wishEntity: WishItem)

    @Delete
    abstract suspend fun deleteAWish(wishEntity: WishItem)

    /* A Flow is a stream of data that can be observed and collected.
    In this case, the Flow will emit a new list of wish items or a single
     wish item whenever the data in the database changes. */

    // Flow will used when we use @Query
    @Query("Select * from 'Wish_Table'")
    abstract fun getAllWishes():Flow<List<WishItem>>

    //This query selects a single row from the "Wish_Table" table based on the provided id.
    @Query("Select * from 'Wish_Table' where id=:id")
    abstract fun getAWish(id:Long):Flow<WishItem>
}