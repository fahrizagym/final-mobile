package com.d121211042.recipeapplication.domain.mapper

import com.d121211042.recipeapplication.data.api.model.RecipeDto
import com.d121211042.recipeapplication.domain.model.Recipe
import javax.inject.Inject

class RecipeListMapper @Inject constructor() {
    fun map(recipeDtoList: List<RecipeDto>): List<Recipe> {
        // All fields are mandatory, if any field is NULL, neglect that recipe.
        return recipeDtoList.mapNotNull {
            Recipe(
                id = it.recipeId ?: return@mapNotNull null,
                title = it.title ?: return@mapNotNull null,
                rating = it.rating?.toString() ?: return@mapNotNull null,
                imageUrl = it.imageUrl ?: return@mapNotNull null,
                ingredients = it.ingredients ?: return@mapNotNull null
            )
        }
    }
}
