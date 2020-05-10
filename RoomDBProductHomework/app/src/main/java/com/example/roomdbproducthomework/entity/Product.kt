package com.example.roomdbproducthomework.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
class Product {

        @PrimaryKey(autoGenerate = true)
        var productId: Int = 0

        @ColumnInfo(name = "product")
        var productName: String = ""

        @ColumnInfo(name = "price")
        var price: Int = 0

        @ColumnInfo(name = "quantity")
        var quantity: Int = 0

}

