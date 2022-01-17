package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    private val defaultShoes = mutableListOf<Shoe>(
        Shoe("Red shoes", 43.5, "Adidas", "Fancy red sneakers"),
        Shoe("Running shoe A", 39.0, "Adidas", "Shoes for runnning"),
        Shoe("Hiking shoe A", 42.0, "Under Armour", "Shoes for hiking"),
        Shoe("Sneakers", 43.0, "Nike", "Fancy white sneakers")
    )

    private var userLoggedIn = false
    private val _shoeList = MutableLiveData<MutableList<Shoe>>(defaultShoes)
    val shoeList: LiveData<MutableList<Shoe>> = _shoeList

    private val _newShoeName = MutableLiveData<String>()
    val newShoeName: LiveData<String> = _newShoeName

    private val _newShoeCompany = MutableLiveData<String>()
    val newShoeCompany: LiveData<String> = _newShoeCompany

    private val _newShoeSize = MutableLiveData<String>()
    val newShoeSize: LiveData<String> = _newShoeSize

    private val _newShoeDescription = MutableLiveData<String>()
    val newShoeDescription: LiveData<String> = _newShoeDescription

    fun saveNewShoe(name: String, size: String, company: String, desc: String) : Boolean {
        if(isValidShoeItem(name, size, company, desc)) {
            addNewShoe(Shoe(name, size.toDouble(), company, desc))
            resetNewShoe()
            return true
        }

        return false
    }

    private fun addNewShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

    private fun isValidShoeItem(name: String, size: String, company: String, desc: String): Boolean {
        return !name.isNullOrEmpty()
                && !size.isNullOrEmpty()
                && !company.isNullOrEmpty()
                && !desc.isNullOrEmpty()
    }

    fun resetNewShoe() {
        _newShoeCompany.value = ""
        _newShoeDescription.value = ""
        _newShoeName.value = ""
        _newShoeSize.value = ""
    }

    fun authenticateUser(): Boolean {
        userLoggedIn = true

        return userLoggedIn
    }

    fun userLogOut() {
        userLoggedIn = false
    }

}