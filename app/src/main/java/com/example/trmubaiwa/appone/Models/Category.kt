package com.example.trmubaiwa.appone.Models


data class Category(val title: String, val image: String) {

    override fun toString(): String {
        return title
    }
}