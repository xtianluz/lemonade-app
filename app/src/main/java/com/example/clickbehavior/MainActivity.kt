package com.example.clickbehavior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clickbehavior.ui.theme.ClickBehaviorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickBehaviorTheme {
                LemonadeGlass(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun LemonadeGlass(modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf(1)}
    var clickTimes by remember { mutableStateOf(0) }

    val imageResource = when(currentScreen){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when(currentScreen){
        1 -> R.string.lemon_tree_screen
        2 -> R.string.lemon_squeeze_screen
        3 -> R.string.lemon_drink_screen
        else -> R.string.lemon_empty_screen
    }
    val descriptionResource = when(currentScreen){
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_squeeze_description
        3 -> R.string.lemon_drink_description
        else -> R.string.lemon_empty_description
    }

    fun imageClickBehavior(){
            when(currentScreen){
                1 -> {
                    currentScreen = 2
                    clickTimes = (2..4).random()
                }
                2 -> {
                    clickTimes--
                    if(clickTimes==0){
                        currentScreen = 3
                    }
                }
                3 -> currentScreen = 4
                else -> currentScreen = 1
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(textResource))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(id = descriptionResource),
            modifier = Modifier.clickable { imageClickBehavior() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    ClickBehaviorTheme {
        LemonadeGlass()
    }
}