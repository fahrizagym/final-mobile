package com.d121211042.recipeapplication.domain.usecase

import com.d121211042.recipeapplication.data.repository.RecipeRepository
import com.d121211042.recipeapplication.domain.mapper.RecipeDetailsMapper
import com.d121211042.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState
import com.d121211042.recipeapplication.util.RecipeResult
import javax.inject.Inject

class FetchRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeDetailsMapper: RecipeDetailsMapper
) {
    suspend operator fun invoke(recipeId: Int): RecipeDetailsUiState {
        return when (val result = repository.fetchRecipeDetails(recipeId)) {
            RecipeResult.Loading -> RecipeDetailsUiState.Loading
            is RecipeResult.Error -> RecipeDetailsUiState.Error(result.message)
            is RecipeResult.Success -> recipeDetailsMapper.map(result.data)
        }
    }
}
