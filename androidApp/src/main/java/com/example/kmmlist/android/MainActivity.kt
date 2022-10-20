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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.api.models.Entity
import com.example.api.models.EntityResponse
import com.example.helper.Result
import com.example.kmmlist.android.ui.MyApplicationTheme
import com.example.viewmodels.EntriesViewModel
import com.example.viewmodels.EntriesViewState

class MainActivity : ComponentActivity() {
    private val viewModel: EntriesViewModel by viewModels<EntriesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupMainContentView(viewModel.entries.collectAsState())
                }
            }
        }
        viewModel.getEntryList()
    }

    @Composable
    fun SetupMainContentView(stateData: State<EntriesViewState>) {
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
    fun ShowSuccessState(response: Result<EntityResponse>?) {
        when(response) {
            is Result.Success -> {
                LazyColumn {
                    items(response.data.entries) { singleItem ->
                        run {
                            PutSingleRowForContent(singleItem)
                        }
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
                modifier = Modifier
                    .wrapContentSize(),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color.Black
            )

            Divider(thickness = 5.dp, color = Color.White)

            Button(onClick = onClick) {
                Text(
                    text = "Retry",
                    modifier = Modifier
                        .wrapContentSize(),
                    color = Color.White,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun PutSingleRowForContent(singleItem: Entity) {
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.LightGray)
                    .padding(5.dp)
                    .clickable {
                        Log.i("SingleRowItem", "Single row clicked with data $singleItem")
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = singleItem.toString(),
                    fontSize = TextUnit(
                        10f, TextUnitType.Sp
                    ),
                    fontStyle = FontStyle.Italic
                )
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
