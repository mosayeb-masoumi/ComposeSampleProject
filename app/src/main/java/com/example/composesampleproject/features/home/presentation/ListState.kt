package com.example.composesampleproject.features.home.presentation

import com.example.composesampleproject.features.home.domain.model.TodosModel

data class ListState(
    val isLoading: Boolean = false,
    val data : List<TodosModel?> = emptyList(),
    val error: String = ""
)