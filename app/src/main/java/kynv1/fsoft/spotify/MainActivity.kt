package kynv1.fsoft.spotify

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kynv1.fsoft.spotify.ui.theme.GradientOne
import kynv1.fsoft.spotify.ui.theme.GradientTwo
import kynv1.fsoft.spotify.ui.theme.SpotifyTheme
import java.time.Duration

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpotifyScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SpotifyScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush
                    .verticalGradient(
                        colors = listOf(
                            GradientOne,
                            GradientTwo
                        )
                    )
            )
            .padding(horizontal = 10.dp)
    ) {
        TopAppBar()
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.na),
                contentDescription = "avatar",
                modifier = Modifier
                    .sizeIn(maxWidth = 500.dp, maxHeight = 500.dp)
                    .aspectRatio(1f)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .weight(10f)
            )
            Spacer(modifier = Modifier.height(30.dp))
            SongAndSingerName()
            Spacer(modifier = Modifier.height(35.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(10f)
            ) {
                PlayerSlider(ofHours = Duration.ofHours(2))
                Spacer(modifier = Modifier.height(40.dp))
                PlayerButtons(modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.PlaylistAdd,
                contentDescription = "play list",
                tint = Color.White
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "menu",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}

@Composable
fun SongAndSingerName(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tinh yeu mang Theo",
            style = androidx.compose.material.MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            androidx.compose.material.Text(
                text = "Nguyen Anh Ky",
                style = androidx.compose.material.MaterialTheme.typography.body2,
                maxLines = 1,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun SongAndSingerNamePreview() {
    SongAndSingerName()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerSlider(ofHours: Duration?) {
    if (ofHours != null) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Slider(
                value = 0f,
                onValueChange = {},
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0s", color = Color.White)
                Text(text = "${ofHours.seconds}s", color = Color.White)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PlayerSliderPreview() {
    PlayerSlider(Duration.ofHours(2))
}

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    sizeButtonImage: Dp = 42.dp,
    playButtonImage: Dp = 72.dp,
) {
    val buttonModifier = Modifier
        .size(sizeButtonImage)
        .semantics { role = Role.Button }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.Replay10,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.PlayCircleFilled,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = Modifier
                .size(playButtonImage)
                .semantics { role = Role.Button }
        )
        Image(
            imageVector = Icons.Filled.Forward10,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.SkipNext,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = buttonModifier
        )

    }
}

@Preview
@Composable
fun PlayerButtonsPreview() {
    PlayerButtons()
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SpotifyScreenPreview() {
    SpotifyTheme {
        SpotifyScreen()
    }
}