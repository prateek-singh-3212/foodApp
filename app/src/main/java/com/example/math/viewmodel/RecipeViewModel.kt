package com.example.math.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.math.FoodApplication
import com.example.math.repo.Repository
import com.example.math.utils.Result
import com.example.network.data.RecipeData
import com.example.network.data.RecipeDataList
import com.example.network.data.SimilarRecipeData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: Repository
): ViewModel() {

    val isPopularDataLoading = MutableLiveData<Boolean>()
    val popularRecipeData = MutableLiveData<List<RecipeData>>()
    val popularRecipeError = MutableLiveData<String>()

    val isRecipeLoading = MutableLiveData<Boolean>()
    val recipeData = MutableLiveData<RecipeData>()
    val recipeError = MutableLiveData<String>()

    val isAllDataLoading = MutableLiveData<Boolean>()
    val allRecipeData = MutableLiveData<List<RecipeData>>()
    val allRecipeError = MutableLiveData<String>()

    val similarRecipeData = MutableLiveData<List<SimilarRecipeData>>()

    fun fetchPopularRecipe(count: Int): Job {
        return viewModelScope.launch {
            isPopularDataLoading.postValue(true)
            val response = repository.popularRecipe(count)
            when(response) {
                is Result.Success -> {
                    val data = response.data as RecipeDataList
                    popularRecipeData.postValue(data.recipes)
                }
                is Result.Error -> {
                    popularRecipeError.postValue(response.exception)
                }
            }
            isPopularDataLoading.postValue(false)
        }
    }

    fun fetchAllRecipe(count: Int): Job {
        return viewModelScope.launch {
            isAllDataLoading.postValue(true)
            val response = repository.popularRecipe(count)
            when(response) {
                is Result.Success -> {
                    val data = response.data as RecipeDataList
                    allRecipeData.postValue(data.recipes)
                }
                is Result.Error -> {
                    allRecipeError.postValue(response.exception)
                }
            }
            isAllDataLoading.postValue(false)
        }
    }

    fun fetchRecipe(id: Int): Job {
        return viewModelScope.launch {
            isRecipeLoading.postValue(true)
            val response = repository.fetchRecipe(id)
            when(response) {
                is Result.Success -> {
                    val data = response.data as RecipeData
                    recipeData.postValue(data)
                }
                is Result.Error -> {
                    recipeError.postValue(response.exception)
                }
            }
            isRecipeLoading.postValue(false)
        }
    }

    fun fetchSimilarRecipes(id: Int): Job {
        return viewModelScope.launch {
            val response = repository.fetchSimilarRecipes(id, 3)
            when(response) {
                is Result.Success -> {
                    val data = response.data as Array<SimilarRecipeData>
                    similarRecipeData.postValue(data.toList())
                }
                is Result.Error -> {
                    recipeError.postValue(response.exception)
                }
            }
            isRecipeLoading.postValue(false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as FoodApplication
                return RecipeViewModel(
                    application.repo
                ) as T
            }
        }
    }
}