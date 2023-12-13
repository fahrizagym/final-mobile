package com.d121211042.recipeapplication.ui.recipe.list.model

import com.d121211042.recipeapplication.domain.model.Recipe
import com.d121211042.recipeapplication.ui.recipe.detail.INVALID_ERROR_MESSAGE
import com.d121211042.recipeapplication.ui.recipe.detail.INVALID_RECIPE_ID

/**
 * UI events:
 * https://developer.android.com/topic/architecture/ui-layer/events#compose
 *
 * Handle ViewModel events:
 * https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events
 */
data class RecipeListUiState(
    val isLoading: Boolean = true,
    val recipes: List<Recipe> = emptyList(),
    val recipeId: Int = INVALID_RECIPE_ID,
    val errorMessage: String = INVALID_ERROR_MESSAGE
)
