package com.example.composesampleproject.features.item_detail.presentation

import com.example.composesampleproject.features.home.domain.model.TodosModel
import com.example.composesampleproject.features.item_detail.domain.model.DetailModel

data class DetailState(
    val isLoading:Boolean = false,
    val data: DetailModel? = null,
    val error:String = ""
)
