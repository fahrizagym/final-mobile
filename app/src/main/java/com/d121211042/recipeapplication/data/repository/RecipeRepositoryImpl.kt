package com.d121211042.recipeapplication.data.repository

import com.d121211042.recipeapplication.data.api.RecipeService
import com.d121211042.recipeapplication.data.api.model.RecipeDto
import com.d121211042.recipeapplication.data.api.model.SearchRecipesResponse
import com.d121211042.recipeapplication.util.RecipeResult
import com.d121211042.recipeapplication.util.safeApiCall

class RecipeRepositoryImpl(
    private val service: RecipeService
) : RecipeRepository {
    override suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): RecipeResult<SearchRecipesResponse> {
        return safeApiCall {
            service.searchRecipes(page, searchQuery)
        }
    }

    override suspend fun fetchRecipeDetails(
        recipeId: Int
    ): RecipeResult<RecipeDto> {
        return safeApiCall {
            service.fetchRecipeDetails(recipeId)
        }
    }
}
