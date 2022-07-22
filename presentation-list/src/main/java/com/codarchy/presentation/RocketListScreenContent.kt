package com.codarchy.presentation

import android.os.Bundle
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import com.codarchy.presentation.fab.MultiFabState
import com.codarchy.presentation.fab.MultiFloatingActionButton
import com.codarchy.presentation.list.RocketList
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun RocketListScreenContent(viewModel: RocketListViewModel = hiltViewModel()) {
    val view = LocalView.current
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
                is RocketListReady -> RocketList(viewModel) {
                    val request = NavDeepLinkRequest.Builder
                        .fromUri("android-app://com.codarchy.presentationdetail.details".toUri())
                        .build()
                    Navigation.findNavController(view).navigate(request)
                }
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

