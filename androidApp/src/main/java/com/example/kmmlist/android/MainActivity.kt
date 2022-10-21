package com.example.kmmlist.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.api.models.PhotosResponseItem
import com.example.helper.Result
import com.example.kmmlist.android.ui.MyApplicationTheme
import com.example.viewmodels.EntriesViewModel
import com.example.viewstate.PhotosViewState

class MainActivity : ComponentActivity() {
    private val viewModel: EntriesViewModel by viewModels<EntriesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    SetupMainContentView(viewModel.photos.collectAsState())
                }
            }
        }
        viewModel.getPhotoList()
    }

    @Composable
    fun SetupMainContentView(stateData: State<PhotosViewState>) {
        when {
            stateData.value.isLoadingContent() -> {
                ShowLoadingState()
            }
            stateData.value.isFailureContent() -> {
                ShowErrorState() {
                    viewModel.retryApi()
                }
            }
            stateData.value.isSuccessContent() -> {
                ShowSuccessState(response = stateData.value.response)
            }
        }
    }

    @Composable
    fun ShowSuccessState(response: Result<List<PhotosResponseItem>>?) {
        when (response) {
            is Result.Success -> {
                LazyColumn {
                    items(response.data) { singleItem ->
                        PutSingleRowForContent(singleItem)
                    }
                }
            }
            else -> {
                ShowErrorState { viewModel.retryApi() }
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun ShowErrorState(onClick: () -> Unit) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Error loading from api",
                modifier = Modifier.wrapContentSize(),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color.Black
            )

            Divider(thickness = 5.dp, color = Color.White)

            Button(onClick = onClick) {
                Text(
                    text = "Retry",
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun PutSingleRowForContent(singleItem: PhotosResponseItem) {
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.LightGray)
                    .padding(5.dp)
                    .clickable {
                        Log.i("SingleRowItem", "Single row clicked with data $singleItem")
                    }
            ) {
                ConstraintLayout(decoupledConstraints()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(singleItem.download_url)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .layoutId("image_view")
                            .size(100.dp)
                    )

                    Text(
                        text = singleItem.author,
                        fontSize = TextUnit(
                            20f, TextUnitType.Sp
                        ),
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        modifier = Modifier.layoutId("title")
                    )
                }
            }
        }
    }

    private fun decoupledConstraints(): ConstraintSet {
        return ConstraintSet {
            val imageView = createRefFor("image_view")
            val titleTV = createRefFor("title")

            constrain(imageView) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }

            constrain(titleTV) {
                start.linkTo(imageView.end, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        }
    }

    @Composable
    fun ShowLoadingState() {
        Surface {
            CircularProgressIndicator(
                modifier = Modifier.padding(180.dp)
            )
        }
    }
}
