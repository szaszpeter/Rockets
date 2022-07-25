package com.codarchy.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import com.codarchy.presentation.fab.MultiFabState
import com.codarchy.presentation.fab.MultiFloatingActionButton
import com.codarchy.presentation.list.RocketList


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
                    viewModel.selectRocket(it)
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

