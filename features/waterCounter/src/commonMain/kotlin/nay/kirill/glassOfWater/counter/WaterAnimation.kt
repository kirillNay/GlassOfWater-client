package nay.kirill.glassOfWater.counter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun WaterAnimation(
    modifier: Modifier = Modifier,
    progress: Float
)