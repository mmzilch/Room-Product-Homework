package com.example.roomdbproducthomework.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdbproducthomework.entity.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product:Product)

    @Query(value = "Select * From product_table Order By productId")
    fun getAllProducts():LiveData<List<Product>>

    @Query(value = "Select * From product_table Where price Between :min AND :max")
    fun getProductbyPrice(min : Int ,max:Int) : LiveData<List<Product>>

    @Query(value = "Select * From product_table Where product = :name Order by productId")
    fun getProductbyName(name:String):LiveData<List<Product>>

    @Query(value="Delete From product_table Where productId= :product")
    suspend fun deleteProduct(product: Int)

}