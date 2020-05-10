package com.example.roomdbproducthomework.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.roomdbproducthomework.R
import com.example.roomdbproducthomework.activity.AddProductActivity.Companion.EXTRA_REPLY
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        btnSave.setOnClickListener {

            val replyIntent = Intent()

            if(TextUtils.isEmpty(editProductid.text) || TextUtils.isEmpty(editProductName.text) || TextUtils.isEmpty(editProductPrice.text) || TextUtils.isEmpty(editProductQuantity.text)){
                Toast.makeText(applicationContext, "Please fill the product", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                val product:Array<String> = arrayOf(editProductid.text.toString(),editProductName.text.toString(),editProductPrice.text.toString(),editProductQuantity.text.toString())
                replyIntent.putExtra(EXTRA_REPLY,product)
                setResult(Activity.RESULT_OK,replyIntent)
            }

            finish()
        }
    }

    companion object{const val EXTRA_REPLY="REPLY_DATA" }
}
