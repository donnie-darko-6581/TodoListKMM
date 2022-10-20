package com.example.kmmlist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kmmlist.android.ui.MyApplicationTheme
import com.example.kmmlist.android.ui.PutVerticalScrollableView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetViewContent(
                        fetchContent()
                    )
                }
            }
        }
    }

    @Composable
    fun SetViewContent(content: List<Int>) {
        PutVerticalScrollableView(content)
    }

    private fun fetchContent(): List<Int> {
        val content = mutableListOf<Int>()
        for(index in 0..50) {
            content.add(index)
        }
        return content
    }
}
