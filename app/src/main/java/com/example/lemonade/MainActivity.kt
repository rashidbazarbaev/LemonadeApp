package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Yellow,
                            titleContentColor = Color.Black
                        ),
                        title = {
                            Text("Lemonade")
                        }
                    )
                }) { innerPadding ->
                    LemonadeApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeCard()
}

@Composable
fun LemonadeCard() {

    var сurrentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    val imagePainter = when (сurrentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val contentDesc = when (сurrentStep) {
        1 -> R.string.desc_tree
        2 -> R.string.desc_lemon
        3 -> R.string.desc_glass
        4 -> R.string.desc_empty
        else -> R.string.desc_tree
    }

    val textRes = when (сurrentStep) {
        1 -> R.string.tap_tree
        2 -> R.string.keep_tap
        3 -> R.string.tap_to_drink
        4 -> R.string.tap_glass
        else -> R.string.tap_tree
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {

        Button(
            onClick = {
                when (сurrentStep) {
                    1 -> {
                        сurrentStep = 2
                        squeezeCount = (2..6).random()
                    }

                    2 -> {
                        squeezeCount--
                        if (squeezeCount == 0)
                            сurrentStep = 3
                    }

                    3 -> {
                        сurrentStep = 4
                    }

                    4 -> {
                        сurrentStep = 1
                    }

                }
            },
            modifier = Modifier.size(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60ebac)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Image(
                painter = painterResource(imagePainter),
                contentDescription = stringResource(contentDesc)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textRes),
            fontSize = 18.sp
        )

    }
}




