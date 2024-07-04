package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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

    val artId = when (state) {
        0 -> R.drawable.birds
        1 -> R.drawable.bridge
        else -> R.drawable.robins
    }

    val nameId = when (state) {
        0 -> R.string.artist_credit
        1 -> R.string.artist_credit
        else -> R.string.artist_credit
    }

    val descriptionId = when (state) {
        0 -> R.string.description_birds
        1 -> R.string.description_bridge
        else -> R.string.description_robins
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize()
    ) {
        ArtDisplay(
            artId = artId,
            modifier = Modifier
                .padding(16.dp)
                .size(400.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ArtDescription(
            nameId = nameId,
            descriptionId = descriptionId,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(120.dp))
        NavigationButtons(
            prevId = R.string.prev,
            nextId = R.string.next,
            onClickPrev = {
                state--
                if (state < 0) {
                    state = 2
                }
            },
            onClickNext = {
                state++
                state %= 3
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun ArtDisplay(
    @DrawableRes artId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(artId),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ArtDescription(
    @StringRes nameId: Int,
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.by_author, stringResource(nameId)),
            textAlign = TextAlign.Start,
            fontSize = 40.sp,
            fontWeight = FontWeight(500),
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(descriptionId),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            maxLines = 3,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun NavigationButtons(
    @StringRes prevId: Int,
    @StringRes nextId: Int,
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClickPrev,
            colors = ButtonColors(
                Color(0xFF8BC34A),
                Color(0xFFFFFFFF),
                Color(0xFF8BC34A),
                Color(0xFFFFFFFF)
            ),
            modifier = modifier
        ) {
            Text(
                text = stringResource(prevId),
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
            )
        }
        Button(
            onClick = onClickNext,
            colors = ButtonColors(
                Color(0xFF8BC34A),
                Color(0xFFFFFFFF),
                Color(0xFF8BC34A),
                Color(0xFFFFFFFF)
            ),
            modifier = modifier
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
