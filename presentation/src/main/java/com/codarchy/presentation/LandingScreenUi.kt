package com.codarchy.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codarchy.data.model.AgeEstimation

@Composable
fun EnterTextItem(viewModel: LandingViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Enter Your Name") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onValueChange = {
            text = it
        }
    )

    Button(
        onClick = {
            viewModel.searchForAgeEstimation(text.text)
        },
        Modifier
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Search")
    }
}

@Composable
fun DisplayAgeEstimationItem(viewModel: LandingViewModel) {
    viewModel.state.value.let { state ->
        when (state) {
            is Idle -> GenericContent()
            is Loading -> Loading()
            is NetworkError -> ErrorContent()
            is GenericError -> ErrorContent()
            is AgeEstimationReady -> DisplayAgeEstimationValue(state.ageEstimation)
        }
    }
}

@Composable
fun DisplayAgeEstimationValue(ageEstimation: AgeEstimation) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Text is a predefined composable that does exactly what you'd expect it to -
        // display text on the screen. It allows you to customize its appearance using
        // the style property.
        Text(
            text = ageEstimation.age.toString(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Loading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun GenericContent() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Hola and welcome to ")
                }

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                ) {
                    append("CleanArchitecture! \n")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("This is a simple app that it will show you statistics of ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                ) {
                    append("age ")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("based on your ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                ) {
                    append("name.")
                }
            }
        },
        modifier = Modifier.padding(16.dp)
    )

    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Please type in your name and hit the ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                ) {
                    append("Search ")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("button, to trigger the api call. ")
                }
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ErrorContent() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append("Uff..something went wrong!")
                }
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}