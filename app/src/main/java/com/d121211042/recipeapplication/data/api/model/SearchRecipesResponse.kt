@file:Suppress("HardCodedStringLiteral")

package com.d121211042.recipeapplication.data.api.model

import com.google.gson.annotations.SerializedName

data class SearchRecipesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val recipes: List<RecipeDto>
)
