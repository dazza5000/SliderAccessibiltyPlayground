@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    var sliderPosition by remember { mutableStateOf(0f) }
                    val sliderSize = 10f

                    val labels: MutableList<String> = mutableListOf()
                    for (i in 0..sliderSize.toInt()) {
                        labels.add(i.toString())
                    }


                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        WellSlider(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            value = sliderPosition,
                            onValueChange = { newValue: Float ->
                                sliderPosition = newValue
                            },
                            labelFormatter = labels,
                            valueRange = 0f..sliderSize,
                            stepSize = 1f
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}


const val WELL_SLIDER_TEST_TAG = "WELL_SLIDER_TEST_TAG"

@Composable
fun WellSlider(
    value: Float,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit = {},
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    stepSize: Float = 0f,
    labelFormatter: List<String>,
) {
    val interactionSource = remember { MutableInteractionSource() }

    var selectedLabel by remember {
        mutableStateOf(
            labelFormatter.getOrNull(value.toInt()).orEmpty()
        )
    }

    val thumb: @Composable (SliderState) -> Unit = {
        Label(
            label = {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary,
                    ) {
                        Column {
                            Text(
                                selectedLabel,
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .rotate(180f)
                            .size(8.dp),
                        shape = TriangleShape(),
                        color = MaterialTheme.colorScheme.primary,
                    ) {
                    }
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }

            },
            interactionSource = interactionSource,
        ) {
            SliderDefaults.Thumb(
                interactionSource = interactionSource,
                colors = SliderDefaults.colors(),
                enabled = enabled,
            )
        }
    }

    val steps =
        if (stepSize == 0f) {
            0
        } else {
            ((valueRange.endInclusive - valueRange.start) / stepSize + 1).toInt()
        }



    Slider(
        value = value,
        valueRange = valueRange,
        modifier = modifier
            .testTag(WELL_SLIDER_TEST_TAG),
        onValueChange = {
            onValueChange(it)
            selectedLabel = labelFormatter.getOrNull(value.toInt()).orEmpty()
        },
        enabled = enabled,
        onValueChangeFinished = {
            // launch some business logic update with the state you hold
            // viewModel.updateSelectedSliderValue(sliderPosition)
        },
        thumb = thumb,
        interactionSource = interactionSource,
        steps = if (stepSize == 0f) 0 else steps,
    )
}

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(
        Path().apply {
            moveTo(x = size.width / 2, y = 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
        },
    )
}
