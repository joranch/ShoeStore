package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    private val defaultShoes = listOf<Shoe>(
        Shoe("Red shoes",43.5, "Adidas", "Fancy red sneakers"),
        Shoe("Running shoe A",39.0, "Adidas", "Shoes for runnning"),
        Shoe("Hiking shoe A",42.0, "Under Armour", "Shoes for hiking"),
        Shoe("Sneakers",43.0, "Nike", "Fancy white sneakers"))

    private var userLoggedIn = false
    private val _shoeList = MutableLiveData<List<Shoe>>(defaultShoes)
    val shoeList: LiveData<List<Shoe>> = _shoeList

    fun authenticateUser(): Boolean {
        userLoggedIn = true

        return userLoggedIn
    }

    fun userLogOut() {
        userLoggedIn = false
    }
}