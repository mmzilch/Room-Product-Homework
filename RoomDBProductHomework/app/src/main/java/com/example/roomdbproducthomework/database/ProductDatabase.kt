package com.example.roomdbproducthomework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbproducthomework.dao.ProductDao
import com.example.roomdbproducthomework.entity.Product

@Database(entities = arrayOf(Product::class), version = 1,exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {//singleton prevents multiple instance of db
        //opening at the same time

        private var INSTACE: ProductDatabase? = null //obj


        fun getDatabase(context: Context): ProductDatabase {//return productDb

            val tempInstance = INSTACE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {//create db table

                val instance =
                    Room.databaseBuilder(context,
                            ProductDatabase::class.java,
                            "product_database").build()
                INSTACE = instance
                return instance
            }
        }
    }
}