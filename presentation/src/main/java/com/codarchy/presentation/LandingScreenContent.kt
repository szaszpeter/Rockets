package com.codarchy.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LandingScreenContent(viewModel: LandingViewModel = hiltViewModel()) {
    Column {
        EnterTextItem(viewModel)
        DisplayAgeEstimationItem(viewModel)
    }
}