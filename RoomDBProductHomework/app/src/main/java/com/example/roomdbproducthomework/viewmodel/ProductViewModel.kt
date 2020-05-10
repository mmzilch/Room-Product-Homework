package com.example.roomdbproducthomework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdbproducthomework.database.ProductDatabase
import com.example.roomdbproducthomework.entity.Product
import com.example.roomdbproducthomework.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application){

    private val repository:ProductRepository//obj decleration

    val allProduct: LiveData<List<Product>>//to hold data
    lateinit var  productbyPrice: LiveData<List<Product>>
    lateinit var productbyName: LiveData<List<Product>>

    init {
        val productDao=ProductDatabase.getDatabase(application).productDao()
        // return db and call dao
        repository= ProductRepository(productDao)//obj
        allProduct=repository.allProduct

        //productbyPrice=repository.productbyPrice
        //productbyName=repository.productbyName
    }

    //to input data
    fun insertProduct(product:Product)=viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun deleteProduct(product:Int)=viewModelScope.launch {
        repository.deleteProduct(product)
    }

    fun productbyPrice(min:Int,max:Int) = viewModelScope.launch {
        productbyPrice=repository.productbyPrice(min,max)
    }

    fun productbyName(name:String) = viewModelScope.launch {
        productbyName=repository.productbyName(name)
    }

}