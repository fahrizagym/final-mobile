package com.d121211042.recipeapplication.domain.usecase

import com.d121211042.recipeapplication.R
import com.d121211042.recipeapplication.data.repository.RecipeRepository
import com.d121211042.recipeapplication.domain.mapper.RecipeListMapper
import com.d121211042.recipeapplication.domain.model.Recipe
import com.d121211042.recipeapplication.ui.recipe.list.model.RecipeListUiState
import com.d121211042.recipeapplication.util.AppResourcesProvider
import com.d121211042.recipeapplication.util.RecipeResult
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeListMapper: RecipeListMapper,
    private val appResourcesProvider: AppResourcesProvider
) {
    private val currentRecipeList: MutableList<Recipe> = mutableListOf()

    suspend operator fun invoke(
        page: Int,
        searchQuery: String
    ): RecipeListUiState {
        return when (val searchResult = repository.searchRecipes(page, searchQuery)) {
            RecipeResult.Loading -> RecipeListUiState()

            is RecipeResult.Error -> {
                val errorMessage: String = searchResult.message
                RecipeListUiState(errorMessage = errorMessage)
            }

            is RecipeResult.Success -> {
                val recipeDtoList = searchResult.data.recipes
                val recipes: List<Recipe> = recipeListMapper.map(recipeDtoList)
                appendRecipesToCurrentRecipeList(recipes)
            }
        }
    }

    private fun appendRecipesToCurrentRecipeList(recipes: List<Recipe>): RecipeListUiState {
        // Append new recipes to the current list of recipes for pagination.
        currentRecipeList.addAll(recipes)

        // Immutable version of list must be provided to Compose components
        val appendedRecipeList: List<Recipe> = currentRecipeList.toList()
        return if (appendedRecipeList.isEmpty()) {
            val errorMessage = appResourcesProvider.getString(R.string.error_no_data_found)
            RecipeListUiState(errorMessage = errorMessage)
        } else {
            RecipeListUiState(
                recipes = appendedRecipeList,
                isLoading = false
            )
        }
    }

    fun clearCurrentRecipeList() {
        currentRecipeList.clear()
    }
}
