package com.example.nutrak.ui.screens

import android.Manifest
import android.app.ActionBar.LayoutParams
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.graphics.decodeBitmap
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraTab(
    processImage: (Bitmap) -> Unit
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var showDialog by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        showDialog = !isGranted
    }

    LaunchedEffect(cameraPermissionState) {
        showDialog = !cameraPermissionState.status.isGranted
    }

    if (showDialog) {
        CameraPermissionDialog(onClick = { requestPermissionLauncher.launch(Manifest.permission.CAMERA) })
    } else {
        CameraScanner(processImage)
    }
}

@Composable
fun CameraScanner(
    processImage: (Bitmap) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            val selectedImage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }
            processImage(selectedImage)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.camera_mask),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            )

            Row(
                modifier = Modifier
                    .padding(vertical = 64.dp, horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .background(Color(0x33FFFFFF))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_scan),
                    contentDescription = ""
                )

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Scan Your Food",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                    Text(
                        text = "Ensure your food is well-lit and in focus for the most accurate scan.",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 32.dp)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(R.drawable.gallery),
                        contentDescription = "",
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .clip(CircleShape)
                            .background(Color(0x1FFFFFFF))
                            .padding(8.dp)
                            .clickable {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                    )
                }

                Image(
                    painter = painterResource(R.drawable.camera_shutter),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        val mainExecutor = ContextCompat.getMainExecutor(context)
                        cameraController.takePicture(mainExecutor, object : OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                val imgBitmap = image.toBitmap()
                                processImage(imgBitmap)
                                image.close()
                            }
                        })
                    }
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

    }
}

@Composable
fun CameraPermissionDialog(
    onClick: () -> Unit,
) {
    var isDismissed by remember { mutableStateOf(false) }

    if (!isDismissed) {
        Dialog(
            onDismissRequest = { isDismissed = true }
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardColors(
                    containerColor = AppTheme.colorScheme.background,
                    contentColor = AppTheme.colorScheme.onBackground,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified,
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(AppTheme.colorScheme.divider)
                            .height(64.dp)
                            .width(64.dp)
                            .padding(16.dp),
                        colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
                    )

                    Text(
                        text = "Allow “Nutrition Scanning” to use your camera?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = AppTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "You can change this setting in the App section of your device Settings.",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )

                    NutrakButton(
                        buttonText = "Allow Access",
                        onClick = {
                            isDismissed = true
                            onClick()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    NutrakButton(
                        buttonText = "Don't Allow",
                        onClick = { isDismissed = true },
                        modifier = Modifier.fillMaxWidth(),
                        isNegative = true
                    )
                }
            }
        }
    }
}
