package nay.kirill.glassOfWater

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.appName
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.minus
import nay.kirill.glassOfWater.res.plus
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.uiKit.AppColors
import nay.kirill.glassOfWater.uiKit.AppTextStyle

@Composable
fun GlassOfWaterScreen() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = Res.dimens.horizontalPadding.dp,
                        vertical = Res.dimens.verticalPadding.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var count: Int by remember { mutableStateOf(0) }
                var isPlaying: Boolean by remember { mutableStateOf(false) }

                val composition by rememberLottieComposition(LottieCompositionSpec.Url("https://lottie.host/ac766dc0-a771-4ca1-b42f-5f415b6f25d7/Vikmy2i49g.json"))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    isPlaying = isPlaying
                )

                LaunchedEffect(key1 = progress) {
                    if (progress == 1f) {
                        isPlaying = false
                    }
                }

                Text(
                    text = stringResource(Res.string.appName),
                    style = AppTextStyle.Header2
                )
                Spacer(modifier = Modifier.height(100.dp))
                LottieAnimation(
                    modifier = Modifier
                        .height(100.dp),
                    composition = composition,
                    progress = { progress }
                )
                Spacer(modifier = Modifier.height(74.dp))
                Text(
                    text = count.toString(),
                    style = AppTextStyle.Header
                )
                Spacer(modifier = Modifier.height(24.dp))
                Controllers(
                    onDown = { count-- },
                    onUp = {
                        count++
                        isPlaying = true
                    },
                    isDownEnabled = count > 0
                )
            }
        }
    }
}

@Composable
private fun Controllers(
    onDown: () -> Unit,
    onUp: () -> Unit,
    isDownEnabled: Boolean
) {
    Row {
        ControlButton(
            onDown,
            stringResource(Res.string.minus),
            isEnabled = isDownEnabled
        )
        Spacer(modifier = Modifier.width(48.dp))
        ControlButton(
            onUp,
            stringResource(Res.string.plus)
        )
    }
}

@Composable
private fun ControlButton(
    onClick: () -> Unit,
    text: String,
    isEnabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .height(44.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppColors.Primary
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = AppTextStyle.ButtonStyle
        )
    }
}