package com.example.mywishlistapp.Data

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {
    suspend fun addWish(wishItem: WishItem){
        wishDao.addAWish(wishItem)
    }
    suspend fun updateWish(wishItem: WishItem){
        wishDao.updateAWish(wishItem)
    }
    suspend fun deleteWish(wishItem: WishItem){
        wishDao.deleteAWish(wishItem)
    }
     fun getWishes():Flow<List<WishItem>>{
        return wishDao.getAllWishes()
    }
    fun getAWish(id:Long):Flow<WishItem>{
        return wishDao.getAWish(id)
    }
}