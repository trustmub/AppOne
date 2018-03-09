package com.example.trmubaiwa.appone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.trmubaiwa.appone.Models.Category
import com.example.trmubaiwa.appone.R
import kotlinx.android.synthetic.main.activity_shop.*
import org.w3c.dom.Text


class CategoryAdapter constructor(private val context: Context, private val categories: List<Category>) : BaseAdapter() {


    /***/
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val categoryView: View = LayoutInflater.from(context).inflate(R.layout.category_list_item, null)

        val categoryName: TextView = categoryView.findViewById(R.id.nameTextView)
        val categoryImage: TextView = categoryView.findViewById(R.id.emailsText)
        val category = categories[position]
        categoryName.text = category.title
        categoryImage.text = category.image

        return categoryView
    }

    /** retreave the item associated with the position at which the list view is suposed to be at*/
    override fun getItem(position: Int): Any {
        return categories[position]
    }

    /** defines a unique id for each row */
    override fun getItemId(position: Int): Long {
        return 0
    }


    /** return the number of rows in the list */
    override fun getCount(): Int {
        return categories.count()
    }
}