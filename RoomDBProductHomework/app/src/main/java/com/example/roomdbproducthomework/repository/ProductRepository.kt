package com.example.roomdbproducthomework.repository

import androidx.lifecycle.LiveData
import com.example.roomdbproducthomework.dao.ProductDao
import com.example.roomdbproducthomework.entity.Product

class ProductRepository(private val productDao: ProductDao) {

    val allProduct: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Int) {
        productDao.deleteProduct(product)
    }

    //val productbyPrice: LiveData<List<Product>> = productDao.getProductbyPrice()
    //val productbyName: LiveData<List<Product>> = productDao.getProductbyName()


    fun productbyPrice(min: Int, max: Int): LiveData<List<Product>> = productDao.getProductbyPrice(min, max)
    fun productbyName(name: String): LiveData<List<Product>> = productDao.getProductbyName(name)


}