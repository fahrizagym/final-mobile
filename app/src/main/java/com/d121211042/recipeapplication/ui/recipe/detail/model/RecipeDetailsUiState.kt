package com.d121211042.recipeapplication.ui.recipe.detail.model

import com.d121211042.recipeapplication.domain.model.Recipe

sealed class RecipeDetailsUiState {
    object Loading : RecipeDetailsUiState()
    data class Error(
        val errorMessage: String
    ) : RecipeDetailsUiState()

    data class Success(
        val recipe: Recipe
    ) : RecipeDetailsUiState()
}
