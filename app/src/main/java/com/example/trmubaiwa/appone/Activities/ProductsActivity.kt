package com.example.trmubaiwa.appone.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.trmubaiwa.appone.R
import com.example.trmubaiwa.appone.Utilities.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryType = intent.getStringExtra(EXTRA_CATEGORY)
        productCategoryTextView.text = categoryType

    }
}
