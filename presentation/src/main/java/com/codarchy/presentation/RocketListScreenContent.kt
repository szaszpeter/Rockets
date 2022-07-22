package com.codarchy.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codarchy.presentation.fab.MultiFabState
import com.codarchy.presentation.fab.MultiFloatingActionButton
import com.codarchy.presentation.list.RocketList


@Composable
fun RocketListScreenContent(viewModel: RocketListViewModel = hiltViewModel()) {
    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(toState, { state ->
                toState = state
            }, {
                viewModel.toggleFilter(it.identifier)
                toState = MultiFabState.COLLAPSED
            })
        }
    ) {
        viewModel.state.value.let { state ->
            when (state) {
                is Loading -> Loading()
                is NetworkError -> ErrorContent()
                is GenericError -> ErrorContent()
                is RocketListReady -> RocketList(viewModel)
            }
        }
        val alpha = if (toState == MultiFabState.EXPANDED) 0.4f else 0f
        Box(
            modifier = Modifier
                .alpha(animateFloatAsState(targetValue = alpha).value)
                .background(Color.Black)
                .fillMaxSize()
        )
    }
}

