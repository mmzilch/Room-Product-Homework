package com.example.roomdbproducthomework.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdbproducthomework.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_productby_price.*
import java.util.*

class ProductbyPriceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productby_price)

        btnGetPriceData.setOnClickListener {

                val replyIntent = Intent()

                if(TextUtils.isEmpty(editMinValue.text) || TextUtils.isEmpty(editMaxValue.text)){
                    if (TextUtils.isEmpty(editMinValue.text)){
                        editMinValue.error = "Please fill the minimum price"
                    }
                    if (TextUtils.isEmpty(editMaxValue.text)){
                        editMaxValue.error = "Please fill the maximum price"
                    }
                    setResult(Activity.RESULT_CANCELED,replyIntent)
                }else{
                    val product:Array<String> = arrayOf(editMinValue.text.toString(),editMaxValue.text.toString())
                    replyIntent.putExtra(EXTRA_REPLY,product)
                    setResult(Activity.RESULT_OK,replyIntent)
                    finish()
                }

            }
        }

        companion object{const val EXTRA_REPLY="REPLY_DATA" }
    }

