package com.d121211042.recipeapplication.domain.mapper

import com.d121211042.recipeapplication.util.AppResourcesProvider
import com.d121211042.recipeapplication.R
import com.d121211042.recipeapplication.data.api.model.RecipeDto
import com.d121211042.recipeapplication.domain.model.Recipe
import com.d121211042.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState
import javax.inject.Inject

class RecipeDetailsMapper @Inject constructor(
    private val resourcesProvider: AppResourcesProvider
) {

    fun map(recipeDto: RecipeDto): RecipeDetailsUiState {
        return try {
            // All fields are mandatory, if any field is NULL, neglect that recipe.
            val recipe = Recipe(
                id = recipeDto.recipeId!!,
                title = recipeDto.title!!,
                rating = recipeDto.rating?.toString()!!,
                imageUrl = recipeDto.imageUrl!!,
                ingredients = recipeDto.ingredients!!
            )
            RecipeDetailsUiState.Success(recipe)
        } catch (e: NullPointerException) {
            val errorMessage = resourcesProvider.getString(
                resourceId = R.string.error_null_dto_field,
                substitutingValue = "${e.cause}"
            )
            RecipeDetailsUiState.Error(errorMessage)
        }
    }
}
