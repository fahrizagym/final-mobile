package com.d121211042.recipeapplication.ui.recipe.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.d121211042.recipeapplication.R
import com.d121211042.recipeapplication.domain.usecase.FetchRecipeDetailsUseCase
import com.d121211042.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState
import com.d121211042.recipeapplication.util.AppResourcesProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val fetchRecipeDetailsUseCase: FetchRecipeDetailsUseCase,
    private val appResourcesProvider: AppResourcesProvider
) : ViewModel() {
    var detailsUiState: MutableState<RecipeDetailsUiState> = mutableStateOf(RecipeDetailsUiState.Loading)
        private set

    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            if (recipeId == INVALID_RECIPE_ID) {
                val errorMessage: String = appResourcesProvider.getString(
                    resourceId = R.string.error_invalid_recipe_id,
                    substitutingValue = "$recipeId"
                )
                detailsUiState.value = RecipeDetailsUiState.Error(errorMessage)
            } else {
                detailsUiState.value = fetchRecipeDetailsUseCase(recipeId)
            }
        }
    }
}
