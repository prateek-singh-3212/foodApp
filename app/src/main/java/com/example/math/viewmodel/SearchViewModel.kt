package com.example.math.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.math.FoodApplication
import com.example.math.repo.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.math.utils.Result.Error
import com.example.math.utils.Result.Success
import com.example.network.data.SearchData

class SearchViewModel(
    private val repository: Repository
): ViewModel() {

    val isSearchDataLoading = MutableLiveData<Boolean>()
    val searchData = MutableLiveData<SearchData>()
    val searchErrorState = MutableLiveData<String>()

    fun searchRecipe(searchQuery: String): Job {
        return viewModelScope.launch {
            isSearchDataLoading.postValue(true)
            val response = repository.searchRecipe(searchQuery)
            when(response) {
                is Success -> {
                    val data = response.data as SearchData
                    searchData.postValue(data)
                }
                is Error -> {
                    searchErrorState.postValue(response.exception)
                }
            }
            isSearchDataLoading.postValue(false)
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
                return SearchViewModel(
                    application.repo
                ) as T
            }
        }
    }
}