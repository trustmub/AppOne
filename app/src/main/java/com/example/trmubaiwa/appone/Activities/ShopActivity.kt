package com.example.trmubaiwa.appone.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.trmubaiwa.appone.Adapters.CategoryAdapter
import com.example.trmubaiwa.appone.Models.Category
import com.example.trmubaiwa.appone.R
import com.example.trmubaiwa.appone.Services.DataService
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() {

    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        adapter = CategoryAdapter(this,DataService.categories)

        categoryListView.adapter = adapter


    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected

    }
}
