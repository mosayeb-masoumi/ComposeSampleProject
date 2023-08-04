package com.example.composesampleproject.features.favorite.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(): ViewModel() {

    private val _favList = mutableStateOf<List<String>>(emptyList())
    val favList: State<List<String>> = _favList

    private val _loading = mutableStateOf<Boolean>(true)
    val loading: State<Boolean> = _loading



    init {
        viewModelScope.launch {
            getList()
        }
    }

    suspend fun getList(){
        _loading.value = true
        delay(5000L)
        val favList = listOf("hassan" , "ali" , "milad"," farhad")
        _loading.value = false
        _favList.value = favList
    }
}
