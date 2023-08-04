package com.example.composesampleproject.features.favorite.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composesampleproject.core.shimmer_placeholder.ShimmerPlaceHolder


@Composable
fun FavoriteScreen(navController: NavHostController) {

    val searchText = remember { mutableStateOf("") }
    val viewModel: FavViewModel = hiltViewModel()

    val listResult = viewModel.favList.value
    val loading = viewModel.loading.value

    val screenWidth = LocalConfiguration.current.screenWidthDp


    val filteredNames = if (searchText.value.isEmpty()) {
        listResult
    } else {
        listResult.filter { it.contains(searchText.value, ignoreCase = true) }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = searchText.value,
            onValueChange = {
                searchText.value = it
            },
            textStyle = TextStyle(color = Color.Black), // Set the text color
            label = { Text(text = "search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )


        if (loading) {

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(10.dp),
//                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(10) {
                    item {
                        ShimmerPlaceHolder(150)
                    }
                }
            }

        }else{
            LazyColumn (modifier = Modifier.padding(10.dp)){
                itemsIndexed(filteredNames) { index, title ->
                    BuildFavSearchItem(index, title)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }




    }
}

@Composable
fun BuildFavSearchItem(index: Int, title: String) {

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.Gray)
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment  = Alignment.CenterVertically
    ) {
        Text(
            text = title, color = Color.White, fontSize = 20.sp
        )
    }

}
