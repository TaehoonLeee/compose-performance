package com.example.composeperformance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

			// Read value at Layout Phase
//			SnackDetail()

			// Read value at Draw Phase
			AnimatedBox()
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

	// version 1
	// Read value at Title Function
//	val offset = with(LocalDensity.current) { scrollProvider().toDp() }
//
//	Column(modifier = Modifier.offset(y = offset)) {
//
//	}

	// version 2
  	// Read value at Layout Phase
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

@Composable
fun AnimatedBox() {
	val infiniteTransition = rememberInfiniteTransition()
	val color by infiniteTransition.animateColor(
		initialValue = Color.Cyan,
		targetValue = Color.Magenta,
		animationSpec = infiniteRepeatable(
			animation = tween(1000, easing = LinearEasing),
			repeatMode = RepeatMode.Reverse
		)
	)

	Box(Modifier.fillMaxSize().background(color))

	// Read value at Draw Phase
//	Box(Modifier.fillMaxSize().drawBehind { drawRect(color) })
}