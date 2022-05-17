package com.example.composeperformance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SnackDetail()
		}
	}
}

@Composable
fun SnackDetail() {
	Box(modifier = Modifier.fillMaxSize()) {
		val scroll = rememberScrollState()

		LaunchedEffect(Unit) {
			while (true) {
				delay(1000L)
				scroll.scrollTo(Random.nextInt(100))
			}
		}

		Title(scroll = scroll.value)
//		RecommendedTitle {
//			scroll.value
//		}
	}
}

@Composable
fun Title(scroll: Int) {
	val offset = with(LocalDensity.current) { scroll.toDp() }

	Column(modifier = Modifier.offset(y = offset)) {
		Text("test")
	}
}

@Composable
fun RecommendedTitle(scrollProvider: () -> Int) {
	Column(modifier = Modifier
		.offset { IntOffset(x = 0, y = scrollProvider()) }
	) {
		Text("test")
	}
}