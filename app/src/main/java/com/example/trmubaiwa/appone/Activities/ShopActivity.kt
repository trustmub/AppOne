package com.example.trmubaiwa.appone.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trmubaiwa.appone.Adapters.CategoryAdapter
import com.example.trmubaiwa.appone.R
import com.example.trmubaiwa.appone.Services.DataService
import kotlinx.android.synthetic.main.activity_shop.*
import org.jetbrains.anko.longToast

class ShopActivity : AppCompatActivity() {

    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        adapter = CategoryAdapter(this,DataService.categories)

        categoryListView.adapter = adapter


        categoryListView.setOnItemClickListener { adapterView, view, position, id ->
            val category = DataService.categories[position]
            longToast("Yo cliecked on  the ${category.title} item") // anko library
        }


    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected

    }
}
