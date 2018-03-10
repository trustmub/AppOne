package com.example.trmubaiwa.appone.Services

import com.example.trmubaiwa.appone.Models.Category
import com.example.trmubaiwa.appone.Models.Product

/**
 * Created by trmubaiwa on 2017/12/26.
 */
object DataService {

    val categories = listOf(
            Category("Shirts", "shirstimage"),
            Category("Hoodies", "hoodiesimage"),
            Category("hats", "hatimage"),
            Category("Shirts", "shirstimage"),
            Category("Hoodies", "hoodiesimage"),
            Category("hats", "hatimage"),
            Category("Shirts", "shirstimage"),
            Category("Hoodies", "hoodiesimage"),
            Category("hats", "hatimage"),
            Category("Shirts", "shirstimage"),
            Category("Hoodies", "hoodiesimage"),
            Category("hats", "hatimage"),
            Category("Shirts", "shirstimage"),
            Category("Hoodies", "hoodiesimage"),
            Category("hats", "hatimage"),
            Category("Digitl", "digitlagoodsmage")
    )

    val hats = listOf(
            Product("Fallen Black hat", "$23", "blackhat"),
            Product("Fallen Red hat", "$22", "redhat"),
            Product("Fallen Grey hat", "$21", "greyhat")
    )

    val hoodies = listOf(
            Product("Fallen Hoodie Grey", "$20", "greyhoodie"),
            Product("Fallen Hoodie Red", "$18", "redhoodie"),
            Product("Fallen Hoodie Yellow", "$21", "yellowhoodie"),
            Product("Fallen Hoodie Black", "$15", "blackhoodie")
    )


    val shirts = listOf(
            Product("Fallen Red Shirt", "$55", "redshirt"),
            Product("Fallen Yellow Shirt", "$60", "yellowshirst"),
            Product("Fallen Black Shirt", "$50", "blackshirt"),
            Product("Fallen Grey Shirt", "$55", "greyshirt")
    )

    val digitalProducts = listOf<Product>()

    fun getProducts(category: String): List<Product> {
        return when (category) {
            "Shirts" -> shirts
            "Hoodies" -> hoodies
            "hats" -> hats
            else -> digitalProducts
        }
    }
}