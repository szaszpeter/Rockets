package com.codarchy.presentation_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codarchy.common.ErrorContent
import com.codarchy.common.Loading
import com.codarchy.data.model.Launch

@Composable
fun RocketDetailsScreenContent(viewModel: RocketDetailsViewModel = hiltViewModel()) {
    viewModel.state.value.let { state ->
        when (state) {
            is Loading -> Loading()
            is NetworkError -> ErrorContent()
            is GenericError -> ErrorContent()
            is RocketDetailsReady -> RocketDetails(state.launches, viewModel)
        }
    }
}

@Composable
fun RocketDetails(launches: List<Launch>, viewModel: RocketDetailsViewModel) {
    RocketTechnicalData(viewModel, launches)
}

@Composable
fun RocketTechnicalData(viewModel: RocketDetailsViewModel, launches: List<Launch>) {
    Column(
        modifier = Modifier
            .padding(16.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Yellow,
                            fontWeight = FontWeight.Light,
                            fontSize = 24.sp
                        )
                    ) {
                        append(stringResource(R.string.name_tag) + " ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Cyan
                        )
                    ) {
                        append(viewModel.selectedRocket?.name ?: "")
                    }
                }
            },
            modifier = Modifier.padding(16.dp)
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
                        append(stringResource(R.string.description_tag))
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Light,
                            color = Color.LightGray
                        )
                    ) {
                        append(viewModel.selectedRocket?.description ?: "")
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
                        append(stringResource(R.string.country_tag) + " ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Light,
                            color = Color.LightGray
                        )
                    ) {
                        append(viewModel.selectedRocket?.country ?: "")
                    }
                }
            },
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
        )

        Box(
            modifier = Modifier
                .border(0.5.dp, Color.LightGray)
                .fillMaxWidth()
                .background(Color.Transparent),
            contentAlignment = Alignment.CenterStart
        ) {
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Yellow,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(stringResource(R.string.height_tag) + " ")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = Color.LightGray
                                )
                            ) {
                                append((viewModel.selectedRocket?.height?.meters) ?: "0")
                                append(" " + stringResource(R.string.meter_um))
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
                                append(stringResource(R.string.mass_tag))
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = Color.LightGray
                                )
                            ) {
                                append(viewModel.selectedRocket?.mass?.kg.toString())
                                append(" " + stringResource(R.string.kg_um))
                            }
                        }
                    },
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)
                )
            }
        }

        launches.map {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Box(
                modifier = Modifier
                    .border(0.5.dp, Color.LightGray)
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
                                        color = Color.Yellow,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(stringResource(R.string.launch_name_tag) + " ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Light,
                                        color = Color.LightGray
                                    )
                                ) {
                                    append(it.name)
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
                                    append(stringResource(R.string.date) + " ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Light,
                                        color = Color.LightGray
                                    )
                                ) {
                                    append(it.dateUtc)
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
                                    append(stringResource(R.string.result_tag))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Light,
                                        color = if (it.success) {
                                            Color.Green
                                        } else {
                                            Color.Red
                                        }
                                    )
                                ) {
                                    append(
                                        if (it.success) {
                                            stringResource(R.string.success_label)
                                        } else {
                                            stringResource(R.string.failure_label)
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
    }
}
