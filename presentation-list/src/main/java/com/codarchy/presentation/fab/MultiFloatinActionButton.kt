package com.codarchy.presentation.fab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.codarchy.presentation.R.*

@Composable
fun MultiFloatingActionButton(
    toState: MultiFabState,
    stateChanged: (fabState: MultiFabState) -> Unit,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {

    val transition: Transition<MultiFabState> = updateTransition(targetState = toState)
    val rotation: Float by transition.animateFloat { state ->
        if (state == MultiFabState.EXPANDED) 180f else 0f
    }
    val scale: Float by transition.animateFloat { state ->
        if (state == MultiFabState.EXPANDED) 56f else 0f
    }

    val alpha: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 100)
        }
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1f else 0f
    }

    Column(horizontalAlignment = Alignment.End) {
        if (transition.currentState == MultiFabState.EXPANDED) {
            MiniFloatingActionButton(
                MultiFabItem(
                    "active",
                    "Only Active",
                    ImageBitmap.imageResource(id = drawable.ic_airplanemode_active)
                ), onFabItemClicked, scale, alpha
            )
            Spacer(modifier = Modifier.height(20.dp))
            MiniFloatingActionButton(
                MultiFabItem(
                    "inactive",
                    "Only Inactive",
                    ImageBitmap.imageResource(id = drawable.ic_airplanemode_inactive)
                ), onFabItemClicked, scale, alpha
            )
            Spacer(modifier = Modifier.height(20.dp))
            MiniFloatingActionButton(
                MultiFabItem(
                    "reset",
                    "Reset Filters",
                    ImageBitmap.imageResource(id = drawable.ic_cancel_presentation)
                ), onFabItemClicked, scale, alpha
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        FloatingActionButton(onClick = {
            stateChanged(
                if (transition.currentState == MultiFabState.EXPANDED) {
                    MultiFabState.COLLAPSED
                } else MultiFabState.EXPANDED
            )
        }) {
            Image(
                painterResource(drawable.ic_filter_alt),
                "icon",
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}

@Composable
private fun MiniFloatingActionButton(
    item: MultiFabItem,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
    buttonScale: Float,
    iconAlpha: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Text(
            item.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .alpha(iconAlpha)
                .background(color = MaterialTheme.colors.surface)
                .padding(start = 6.dp, end = 6.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    onClick = { onFabItemClicked(item) },
                )
        ) {
            drawCircle(color = Color.Gray, buttonScale)
            drawImage(item.imageBitmap, alpha = iconAlpha)
        }
    }
}


class MultiFabItem(
    val identifier: String,
    val label: String,
    val imageBitmap: ImageBitmap
)


enum class MultiFabState {
    COLLAPSED, EXPANDED
}