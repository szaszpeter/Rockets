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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codarchy.data.model.Rocket
import com.codarchy.presentation.ResetActiveFilter
import com.codarchy.presentation.ActiveFilterEnabled
import com.codarchy.presentation.InActiveFilterEnabled
import com.codarchy.presentation.R
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
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
    )
    Box(
        modifier = Modifier
            .border(0.5.dp, Color.LightGray)
            .clickable { clickListener() }
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Cyan
                            )
                        ) {
                            append(rocket.name)
                        }
                    }
                },
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Yellow,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append(stringResource(R.string.country_tag) + " ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = Color.LightGray
                            )
                        ) {
                            append(rocket.country)
                        }
                    }
                },
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Yellow,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append(stringResource(R.string.engines_tag) + " ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = Color.LightGray
                            )
                        ) {
                            append(rocket.engines.number.toString())
                        }
                    }
                },
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Yellow,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append(stringResource(R.string.status_tag) + " ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = if (rocket.active) {
                                    Color.Green
                                } else {
                                    Color.Red
                                }
                            )
                        ) {
                            append(
                                if (rocket.active) {
                                    stringResource(R.string.active)
                                } else {
                                    stringResource(R.string.inactive)
                                }
                            )
                        }
                    }
                },
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            )
        }
    }
}