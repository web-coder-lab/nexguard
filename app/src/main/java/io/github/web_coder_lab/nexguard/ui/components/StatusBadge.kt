package io.github.web_coder_lab.nexguard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.web_coder_lab.nexguard.ui.theme.StatusAlert
import io.github.web_coder_lab.nexguard.ui.theme.StatusGood
import io.github.web_coder_lab.nexguard.ui.theme.StatusInfo
import io.github.web_coder_lab.nexguard.ui.theme.StatusWarning

enum class BadgeType {
    SUCCESS,
    WARNING,
    ALERT,
    NEUTRAL,
    INFO
}

@Composable
fun StatusBadge(
    text: String,
    type: BadgeType,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor) = when (type) {
        BadgeType.SUCCESS -> Pair(StatusGood.copy(alpha = 0.15f), StatusGood)
        BadgeType.WARNING -> Pair(StatusWarning.copy(alpha = 0.15f), StatusWarning)
        BadgeType.ALERT -> Pair(StatusAlert.copy(alpha = 0.15f), StatusAlert)
        BadgeType.INFO -> Pair(StatusInfo.copy(alpha = 0.15f), StatusInfo)
        BadgeType.NEUTRAL -> Pair(
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
