package com.example.math.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.math.FoodApplication
import com.example.math.repo.Repository
import com.example.math.utils.Constants
import com.example.math.utils.FoodSharedPreference
import com.example.math.utils.Result
import com.example.network.data.RecipeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val foodSharedPreference: FoodSharedPreference,
    private val repository: Repository
): ViewModel() {

    val isFavouriteRecipeLoading = MutableLiveData<Boolean>()
    val recipeData = MutableLiveData<List<RecipeData>>()
    val recipeError = MutableLiveData<String>()

    fun addToFavourites(recipeData: RecipeData) {
        val gson = Gson()
        val favListString = foodSharedPreference.getString(Constants.FAVOURITE_RECIPE)

        var favList = if (favListString != null) {
            val type = object : TypeToken<List<RecipeData>>() {}.type
            gson.fromJson(favListString, type)
        }else {
            emptyList<RecipeData>()
        }

        favList = favList.toMutableList().apply {
            forEach {
                if (it.id == recipeData.id) {
                    return
                }
            }
            add(recipeData)
        }

        val json = gson.toJson(favList)
        foodSharedPreference.setString(Constants.FAVOURITE_RECIPE, json)
    }

    fun removeFavourite(recipeData: RecipeData) {
        val gson = Gson()
        val favListString = foodSharedPreference.getString(Constants.FAVOURITE_RECIPE)

        var favList = if (favListString != null) {
            val type = object : TypeToken<List<RecipeData>>() {}.type
            gson.fromJson(favListString, type)
        }else {
            emptyList<RecipeData>()
        }

        favList = favList.toMutableList().apply {
            if (!isFavouriteRecipe(recipeData.id)) {
                return
            }
            remove(recipeData)
        }

        val json = gson.toJson(favList)
        foodSharedPreference.setString(Constants.FAVOURITE_RECIPE, json)
    }

    fun isFavouriteRecipe(id: Int): Boolean {
        val gson = Gson()
        val favListString = foodSharedPreference.getString(Constants.FAVOURITE_RECIPE)

        val favList = if (favListString != null) {
            val type = object : TypeToken<List<RecipeData>>() {}.type
            gson.fromJson(favListString, type)
        }else {
            emptyList<RecipeData>()
        }
        return favList.any { it.id == id }
    }

    fun getAllFavouriteRecipes(): List<RecipeData> {
        val gson = Gson()
        val favListString = foodSharedPreference.getString(Constants.FAVOURITE_RECIPE)

        val favList = if (favListString != null) {
            val type = object : TypeToken<List<RecipeData>>() {}.type
            gson.fromJson(favListString, type)
        }else {
            emptyList<RecipeData>()
        }
        return favList
    }

    fun getRecipeData(id: Int): RecipeData {
        return getAllFavouriteRecipes().first { it.id == id }
    }

    fun fetchAllRecipes(id: List<Int>): Job {
        return viewModelScope.launch {
            isFavouriteRecipeLoading.postValue(true)
            val response = repository.fetchAllRecipe(id)
            when(response) {
                is Result.Success -> {
                    val data = response.data as Array<RecipeData>
                    recipeData.postValue(data.toList())
                }
                is Result.Error -> {
                    recipeError.postValue(response.exception)
                }
            }
            isFavouriteRecipeLoading.postValue(false)
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
                return FavoriteViewModel(
                    application.foodSharedPreference,
                    application.repo,
                ) as T
            }
        }
    }
}