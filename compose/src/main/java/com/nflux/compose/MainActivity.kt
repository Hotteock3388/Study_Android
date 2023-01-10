package com.nflux.compose

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.drawToBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nflux.compose.ui.theme.FreeWriteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//            WindowManager.LayoutParams.FLAG_BLUR_BEHIND
//        )

        setContent {
            FreeWriteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black.copy(alpha = 0f),
                ) {
                    Box{
                        BlurImage{
                            AsyncImage( //coil or imageComposable
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                model = ImageRequest.Builder(LocalContext.current).data(R.drawable.device_img).build(),
                                contentDescription = null,
                                contentScale = ContentScale.Fit // blur가 짤린다면 넣어보세요
                            )
                        }
                    }

                }
            }
        }
    }
}
@Composable
fun BlurImage(content: @Composable () -> Unit) {
    Box {
        content()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .drawWithContent {
                    clipRect(top = 0f) {

                        //  val colors = listOf(Color.Transparent, Color.White)
                        this@drawWithContent.drawContent()
                        //   drawRect(
                        //      brush = Brush.verticalGradient(colors),
                        //      blendMode = BlendMode.DstIn
                        //  )
                    }
                }
                .blur(
                    radiusX = 8.dp, radiusY = 8.dp
                )
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FreeWriteTheme {
        Greeting("Android")
    }
}