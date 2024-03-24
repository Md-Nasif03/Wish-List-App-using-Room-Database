package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.Data.WishItem
import com.example.mywishlistapp.Data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishViewModel(
    private var wishRepository: WishRepository =Graph.wishRepository
):ViewModel() {
    var wishTitle by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    // use for on value change
    fun wishTitleChange(newTitle:String){
        wishTitle=newTitle
    }
    fun wishDescriptionChange(newDescription:String){
        wishDescription=newDescription
    }

    lateinit var getAllWishes:Flow<List<WishItem>>
    init {
        viewModelScope.launch{
            getAllWishes=wishRepository.getWishes()
        }
    }

    fun addWish(wishItem: WishItem){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wishItem)
        }
    }
    fun updateWish(wishItem: WishItem){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.updateWish(wishItem)
        }
    }
    fun deleteWish(wishItem: WishItem){
        viewModelScope.launch (Dispatchers.IO){
            wishRepository.deleteWish(wishItem)
        }
    }
    fun getAWish(id:Long):Flow<WishItem>{
        return wishRepository.getAWish(id)
    }


}