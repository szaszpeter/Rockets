package com.codarchy.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codarchy.data.model.Rocket
import com.codarchy.presentation.ResetActiveFilter
import com.codarchy.presentation.ActiveFilterEnabled
import com.codarchy.presentation.InActiveFilterEnabled
import com.codarchy.presentation.RocketListViewModel

@Composable
fun RocketList(viewModel: RocketListViewModel, clickListener: (Rocket) -> Unit) {
    val rockets = when (viewModel.filterState.value) {
        is ResetActiveFilter -> viewModel.rawData
        is ActiveFilterEnabled -> viewModel.getActiveRockets()
        is InActiveFilterEnabled -> viewModel.getInactiveRockets()
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
    ) {
        items(
            rockets
        ) {
            RocketItem(rocket = it) { clickListener(it) }
        }
    }
}

@Composable
fun RocketItem(rocket: Rocket, clickListener: () -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colors.primary)
            .clickable { clickListener() }
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.Black),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                val status = if (rocket.active) {
                    "Yes"
                } else {
                    "No"
                }
                Text(
                    text = rocket.name,
                    style = TextStyle(
                        color = if (rocket.active) {
                            Color.Cyan
                        } else {
                            Color.Gray
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )
                )
                Text(
                    text = "Country : " + rocket.country,
                    Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        color = Color.Yellow,
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "Engines : " + rocket.engines.number.toString(),
                    Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        color = Color.Yellow,
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "Active : $status",
                    Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        color = Color.Yellow,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}