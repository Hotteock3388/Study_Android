package com.nflux.compose

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
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

        android.graphics.Rect
        setContent {
            FreeWriteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White.copy(),
                ) {
                    Box{
//                        BlurImage{
//                            AsyncImage( //coil or imageComposable
//                                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
//                                model = ImageRequest.Builder(LocalContext.current).data(R.drawable.device_img).build(),
//                                contentDescription = null,
//                                contentScale = ContentScale.Fit // blur가 짤린다면 넣어보세요
//                            )
//                        }
                        DropdownDemo()
                    }

                }
            }
        }
    }
}

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
): Unit {
    androidx.compose.material.DropdownMenu(expanded, onDismissRequest, modifier, offset, properties, content)
}

@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex],modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .background(
                Color.Gray
            ))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
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