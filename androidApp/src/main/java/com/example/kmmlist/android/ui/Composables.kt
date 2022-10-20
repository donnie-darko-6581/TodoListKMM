package com.example.kmmlist.android.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun PutVerticalScrollableView(content: List<Int>) {
    LazyColumn {
        items(content) { singleItem ->
            run {
                PutSingleRowForContent(singleItem)
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PutSingleRowForContent(singleItem: Int) {
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
