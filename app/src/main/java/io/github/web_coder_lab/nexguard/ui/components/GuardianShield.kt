package io.github.web_coder_lab.nexguard.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.web_coder_lab.nexguard.domain.model.ProtectionState
import io.github.web_coder_lab.nexguard.ui.theme.StatusAlert
import io.github.web_coder_lab.nexguard.ui.theme.StatusGood
import io.github.web_coder_lab.nexguard.ui.theme.StatusWarning

@Composable
fun GuardianShield(
    state: ProtectionState,
    modifier: Modifier = Modifier,
    size: Dp = 130.dp,
    reducedMotion: Boolean = false,
    onClick: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shield_pulse")
    val pulseScale by if (!reducedMotion && (state == ProtectionState.ACTIVE || state == ProtectionState.READY)) {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.03f,
            animationSpec = infiniteRepeatable(
                animation = tween(2200, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_scale"
        )
    } else {
        remember { androidx.compose.runtime.mutableFloatStateOf(1f) }
    }

    val primaryColor = when (state) {
        ProtectionState.ACTIVE, ProtectionState.READY -> StatusGood
        ProtectionState.ACTION_NEEDED, ProtectionState.DISCONNECTED -> StatusAlert
        ProtectionState.BATTERY_RESTRICTED -> StatusWarning
        ProtectionState.PAUSED -> Color(0xFF71717A)
    }

    val outlineColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)

    Box(
        modifier = modifier
            .size(size)
            .testTag("guardian_shield")
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = size / 2),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size * pulseScale)) {
            val w = this.size.width
            val h = this.size.height

            // Shield Geometry
            val shieldPath = Path().apply {
                moveTo(w * 0.5f, h * 0.08f)
                cubicTo(w * 0.80f, h * 0.08f, w * 0.92f, h * 0.16f, w * 0.90f, h * 0.42f)
                cubicTo(w * 0.88f, h * 0.68f, w * 0.68f, h * 0.86f, w * 0.5f, h * 0.94f)
                cubicTo(w * 0.32f, h * 0.86f, w * 0.12f, h * 0.68f, w * 0.10f, h * 0.42f)
                cubicTo(w * 0.08f, h * 0.16f, w * 0.20f, h * 0.08f, w * 0.5f, h * 0.08f)
                close()
            }

            // Subtle background fill
            drawPath(
                path = shieldPath,
                color = primaryColor.copy(alpha = 0.12f)
            )

            // Minimal elegant border
            drawPath(
                path = shieldPath,
                color = primaryColor,
                style = Stroke(width = 2.5.dp.toPx())
            )

            // Inner subtle border
            drawPath(
                path = shieldPath,
                color = outlineColor,
                style = Stroke(width = 1.dp.toPx())
            )

            // Small notification dot at top right
            val dotCenter = Offset(w * 0.78f, h * 0.22f)
            val dotRadius = w * 0.055f

            drawCircle(
                color = primaryColor,
                radius = dotRadius,
                center = dotCenter
            )
            drawCircle(
                color = Color.White,
                radius = dotRadius * 0.4f,
                center = dotCenter
            )
        }
    }
}
