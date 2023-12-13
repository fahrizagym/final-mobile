package com.d121211042.recipeapplication.data.repository

import com.d121211042.recipeapplication.data.api.model.RecipeDto
import com.d121211042.recipeapplication.data.api.model.SearchRecipesResponse
import com.d121211042.recipeapplication.util.RecipeResult

interface RecipeRepository {

    suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): RecipeResult<SearchRecipesResponse>

    suspend fun fetchRecipeDetails(
        recipeId: Int
    ): RecipeResult<RecipeDto>
}
