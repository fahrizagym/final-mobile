package com.d121211042.recipeapplication.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val rating: String,
    val imageUrl: String,
    val ingredients: List<String>
)
