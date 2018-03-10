package com.example.trmubaiwa.appone.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.trmubaiwa.appone.Models.Category
import com.example.trmubaiwa.appone.R

/**
 * Created by trmubaiwa on 2018/03/10.
 */
class CategoryRecycleAdapter(val context: Context, val categories: List<Category>, private val itemClick: (Category) -> Unit) : RecyclerView.Adapter<CategoryRecycleAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.category_list_item, parent, false)

        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return categories.count()

    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bindCategory(categories[position], context)
    }

    inner class Holder(itemView: View?, private val itemClick: (Category) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val categoryImage = itemView?.findViewById<TextView>(R.id.emailsText)
        val categoryName = itemView?.findViewById<TextView>(R.id.nameTextView)

        fun bindCategory(category: Category, context: Context) {
            categoryName?.text = category.title
            categoryImage?.text = category.image
            itemView.setOnClickListener{ itemClick(category)}
        }
    }
}