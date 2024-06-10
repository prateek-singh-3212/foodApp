package com.example.math.repo

import android.util.Log
import com.example.math.utils.Constants
import com.example.math.utils.Constants.convertToErrorModel
import com.example.math.utils.FoodSharedPreference
import com.example.network.FoodAPIService
import com.example.math.utils.Result

class Repository(
    private val foodAPIService: FoodAPIService,
    private val foodSharedPreference: FoodSharedPreference,
) {

    suspend fun searchRecipe(searchString: String): Result<Any> {
        return try {
            val token = foodSharedPreference.getString(Constants.TOKEN)!!
            val result = foodAPIService.search(apiKey = token, searchQuery = searchString)
            if (result.isSuccessful) {
                Result.success(result.body()!!)
            }else {
                Result.error(convertToErrorModel(result.errorBody()!!).message)
            }
        }catch (ex: Exception) {
            Log.e("apiError", ex.stackTraceToString())
            Result.error("Unable to Fetch")
        }
    }

    suspend fun popularRecipe(count: Int): Result<Any> {
        return try {
            val token = foodSharedPreference.getString(Constants.TOKEN)!!
            val result = foodAPIService.popularRecipes(apiKey = token, count = count)
            if (result.isSuccessful) {
                Result.success(result.body()!!)
            }else {
                Result.error(convertToErrorModel(result.errorBody()!!).message)
            }
        }catch (ex: Exception) {
            Log.e("apiError", ex.stackTraceToString())
            Result.error("Unable to Fetch")
        }
    }

    suspend fun fetchRecipe(id: Int): Result<Any> {
        return try {
            val token = foodSharedPreference.getString(Constants.TOKEN)!!
            val result = foodAPIService.fetchRecipeData(apiKey = token, id = id)
            if (result.isSuccessful) {
                Result.success(result.body()!!)
            }else {
                Result.error(convertToErrorModel(result.errorBody()!!).message)
            }
        }catch (ex: Exception) {
            Log.e("apiError", ex.stackTraceToString())
            Result.error("Unable to Fetch")
        }
    }

    suspend fun fetchAllRecipe(ids: List<Int>): Result<Any> {
        return try {
            val token = foodSharedPreference.getString(Constants.TOKEN)!!
            val idsString = ids.joinToString()
            val result = foodAPIService.fetchAllRecipes(apiKey = token, ids = idsString)
            if (result.isSuccessful) {
                Result.success(result.body()!!)
            } else {
                Result.error(convertToErrorModel(result.errorBody()!!).message)
            }
        } catch (ex: Exception) {
            Log.e("apiError", ex.stackTraceToString())
            Result.error("Unable to Fetch")
        }
    }

    suspend fun fetchSimilarRecipes(id: Int, count: Int): Result<Any> {
        return try {
            val token = foodSharedPreference.getString(Constants.TOKEN)!!
            val result = foodAPIService.fetchSimilarRecipeData(apiKey = token, id = id, count = count)
            if (result.isSuccessful) {
                Result.success(result.body()!!)
            } else {
                Result.error(convertToErrorModel(result.errorBody()!!).message)
            }
        } catch (ex: Exception) {
            Log.e("apiError", ex.stackTraceToString())
            Result.error("Unable to Fetch")
        }
    }
}