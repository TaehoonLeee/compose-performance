package com.example.composeperformance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
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
	Column(modifier = Modifier.fillMaxSize()) {
		val scroll = rememberScrollState()

		Title(scroll.value)
		Content(scroll)
	}
}

@Composable
fun Title(scroll: Int) {
	val offset = with(LocalDensity.current) { scroll.toDp() }


	Column(modifier = Modifier.offset(y = offset)) {
		Text("test")
	}

//	version 1
//	val offset = with(LocalDensity.current) { scrollProvider().toDp() }
//
//	Column(modifier = Modifier.offset(y = offset)) {
//
//	}

//  version 2
//	Column(modifier = Modifier
//		.offset { IntOffset(x = 0, y = scrollProvider()) }
//	) {
//		Text("test")
//	}
}

@Composable
fun Content(scroll: ScrollState) {

	fun randomColor() = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

	Column(modifier = Modifier
		.fillMaxSize()
		.verticalScroll(scroll)) {
		repeat(10) {
			Box(modifier = Modifier
				.fillMaxWidth()
				.height(300.dp)
				.background(randomColor())
			)
		}
	}
}