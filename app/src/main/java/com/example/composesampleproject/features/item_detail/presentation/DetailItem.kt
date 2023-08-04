package com.example.composesampleproject.features.item_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composesampleproject.core.Constants



@Composable
fun TodoDetailScreen(navController: NavHostController, itemId: Int) {



    val id = remember { mutableStateOf(itemId) }



    val viewModel: DetailItemViewModel = hiltViewModel()



    // use clause to prevent call data when every time recomposition
    if (id.value == itemId) {
        viewModel.setItemId(itemId)
        id.value =id.value +1
    }


    val result = viewModel.detailResult.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {


        if (result.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (result.error.isNotBlank()) {
            Text(text = "An error occurred while fetching data")
        }

        if (result.data != null) {
            Column(modifier = Modifier.fillMaxSize()) {

                Spacer(modifier = Modifier.height(50.dp))
                Text(

                    text = result.data.id.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = result.data.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                if (result.data.completed) Text(
                    text = "COMPLETED",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                else Text(text = "NOT COMPLETED", fontWeight = FontWeight.Bold, color = Color.Red)
            }
        }

    }

}