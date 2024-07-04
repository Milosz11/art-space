package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var state by remember { mutableIntStateOf(0) }

    var artId = when(state) {
        0 -> 99
        1 -> 10
        else -> 42
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize()
    ) {
        ArtDisplay(
            artId = 0
        )
        ArtDescription(
            nameId = 0,
            descriptionId = 0
        )
        NavigationButtons(
            prevId = R.string.prev,
            nextId = R.string.next,
            onClickPrev = {
                if (state == 0) {
                    state = 2
                } else {
                    state--
                }
            },
            onClickNext = {
                state++
                state %= 3
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun ArtDisplay(
    @DrawableRes artId: Int,
    modifier: Modifier = Modifier
) {
    // content
}

@Composable
fun ArtDescription(
    @StringRes nameId: Int,
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
) {
    // content
}

@Composable
fun NavigationButtons(
    @StringRes prevId: Int,
    @StringRes nextId: Int,
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        Button(
            onClick = onClickPrev,
            modifier = modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(prevId),
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
            )
        }
        Button(
            onClick = onClickNext,
            modifier = modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = stringResource(nextId),
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}
