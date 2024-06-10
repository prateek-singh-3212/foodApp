package com.example.network

import com.example.network.data.RecipeData
import com.example.network.data.RecipeDataList
import com.example.network.data.SearchData
import com.example.network.data.SimilarRecipeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodAPIService {

    @GET("/recipes/complexSearch")
    suspend fun search(
        @Query("apiKey") apiKey: String = "8851271eea694e769bfb32c8c5562d69",
        @Query("query") searchQuery: String,
    ): Response<SearchData>

    @GET("/recipes/random")
    suspend fun popularRecipes(
        @Query("apiKey") apiKey: String = "8851271eea694e769bfb32c8c5562d69",
        @Query("number") count: Int
    ): Response<RecipeDataList>

    @GET("/recipes/{id}/information")
    suspend fun fetchRecipeData(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = "8851271eea694e769bfb32c8c5562d69",
        @Query("includeNutrition") includeNutrition: Boolean = true
    ): Response<RecipeData>

    @GET("/recipes/informationBulk")
    suspend fun fetchAllRecipes(
        @Query("apiKey") apiKey: String = "8851271eea694e769bfb32c8c5562d69",
        @Query("ids") ids: String
    ): Response<Array<RecipeData>>

    @GET("/recipes/{id}/similar")
    suspend fun fetchSimilarRecipeData(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = "8851271eea694e769bfb32c8c5562d69",
        @Query("number") count: Int
    ): Response<Array<SimilarRecipeData>>

}