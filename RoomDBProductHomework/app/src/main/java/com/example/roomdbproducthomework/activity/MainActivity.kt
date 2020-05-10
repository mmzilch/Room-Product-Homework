package com.example.roomdbproducthomework.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbproducthomework.R
import com.example.roomdbproducthomework.adapter.ProductAdapter
import com.example.roomdbproducthomework.entity.Product
import com.example.roomdbproducthomework.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val AddProductActivityCode = 1
    private val ProductbyPriceActivityCode = 2
    private var productAdapter = ProductAdapter()

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcyProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProduct.observe(
                this, Observer { products ->
            products?.let {
                productAdapter.setProduct(it)
            }
        }
        )

        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddProductActivity::class.java)
            startActivityForResult(intent, AddProductActivityCode)
        }

        btnAll.setOnClickListener {
            productViewModel.allProduct.observe(
                    this, Observer { products ->
                products?.let {
                    productAdapter.setProduct(it)
                }
            }
            )
        }


        btnDelete.setOnClickListener {
            if (TextUtils.isEmpty(editSearch.text)){
                editSearch.error="Search Value is needed"
            }
            else{
                var deletebyId=editSearch.text.toString().toInt()
                productViewModel.deleteProduct(deletebyId)
                productViewModel.allProduct.observe(this,
                        Observer {
                            product ->
                            product?.let {
                                productAdapter.setProduct(it)
                            }
                        })
            }
        }

        btnbyPrice.setOnClickListener {
            val intentbyPrice = Intent(this@MainActivity, ProductbyPriceActivity::class.java)
            startActivityForResult(intentbyPrice, ProductbyPriceActivityCode)
        }

        btnbyName.setOnClickListener {
            if (TextUtils.isEmpty(editSearch.text)){
                editSearch.error="Search Value is needed"
            }
            else{
                var searchbyName=editSearch.text.toString()
                Log.d("Name+++++++++++++++++++",searchbyName)
                productViewModel.productbyName(searchbyName)
                productViewModel.productbyName.observe(this,
                        Observer {
                            product ->
                            product?.let {
                                productAdapter.setProduct(it)
                                Log.d("Name*************",it.toString())
                            }
                        })
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddProductActivityCode && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayExtra(AddProductActivity.EXTRA_REPLY)?.let {
                var product = Product()
                product.productId = it.get(0).toInt()
                product.productName = it.get(1).toString()
                product.price = it.get(2).toInt()
                product.quantity = it.get(3).toInt()
                productViewModel.insertProduct(product)
            }

        }

        if(requestCode==ProductbyPriceActivityCode && resultCode==Activity.RESULT_OK)
        {
            data?.getStringArrayExtra(ProductbyPriceActivity.EXTRA_REPLY)?.let {
                productViewModel.productbyPrice(it!![0].toString().toInt(),it!![1].toString().toInt())
                productViewModel.productbyPrice.observe(this, Observer {
                    products ->
                    products.let {
                        productAdapter.setProduct(it)
                        Log.d("ProductbyPrice>>>>>>>>", it.toString())
                    }
                })
            }
        }
    }

}
