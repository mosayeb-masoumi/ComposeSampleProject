package com.example.composesampleproject.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composesampleproject.core.Destination
import com.example.composesampleproject.core.shimmer_placeholder.ShimmerPlaceHolder
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesampleproject.features.home.domain.model.TodosModel



//@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController) {


    val viewModel: HomeViewModel = hiltViewModel()
//    val viewModel: HomeViewModel = viewModel()

    //first way   // use first way in viewModel too
    val listResult = viewModel.todoList.value


    //second way  // use second way in viewModel too
//    val listResult by viewModel.todoList.collectAsState()


    val selectedItem = remember { mutableStateOf<TodosModel?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        ItemDialog(
            value = selectedItem.value,
            setShowDialog = { showDialog.value = it }) { id ->
            navController.navigate(Destination.TodoDetailScreen.createRoute(id))
            showDialog.value = false
            print(message = "")
        }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        if (listResult.isLoading) {

            LazyVerticalGrid(columns = GridCells.Fixed(2)){
             items(10) {
                    ShimmerPlaceHolder(100)
                }
            }
        }

        if (listResult.error.isNotBlank()) {
            Text( modifier = Modifier.align(Alignment.Center),
                text = "An error occurred while fetching data",
                color = Color.Black,
                fontSize = 25.sp
            )
        }


        LazyVerticalGrid(columns = GridCells.Fixed(2) , modifier = Modifier.fillMaxSize().padding(10.dp)) {
            items(listResult.data.size) {
                TodosListItem(todosModel = listResult.data[it], onItemClick = {
                    showDialog.value = true
                    selectedItem.value = it
                })

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp)
//        ) {
//
//            items(listResult.data.size) {
//                TodosListItem(todosModel = listResult.data[it], onItemClick = {
//                    showDialog.value = true
//                    selectedItem.value = it
//                })
//
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//
//        }


    }
}




@Composable
 fun TodosListItem(todosModel: TodosModel?, onItemClick: (TodosModel) -> Unit) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 5.dp , horizontal = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.Gray)
//                .padding(horizontal = 10.dp)
                .clickable {
                    onItemClick(todosModel!!)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = todosModel!!.title)
            Text(text = todosModel.userId.toString())
        }
 }

@Composable
fun ItemDialog(
    value: TodosModel?,
    setShowDialog: (Boolean) -> Unit,
    onEventCallback: (Int) -> Unit
) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {

        Surface(
            modifier = Modifier.padding(horizontal = 30.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(color = Color.White)
            ) {

                IconButton(modifier = Modifier.align(Alignment.End), onClick = {
                    setShowDialog(false)
                }) {
                    Icon(Icons.Default.Close, contentDescription = "")
                }

                Spacer(modifier = Modifier.height(20.dp))
                value?.let { Text(text = it.title, textAlign = TextAlign.Center) }
                Spacer(modifier = Modifier.height(50.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.Blue)
                        .padding(horizontal = 20.dp)
                        .clickable {
                            value?.let { onEventCallback(it.id) }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(text = "See more detail", color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))


            }


        }
    }
}