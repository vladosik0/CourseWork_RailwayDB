package com.example.coursework

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursework.ui.CourseWorkTopAppBar
import com.example.coursework.ui.train.screens.TrainHomeDestination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutApp(screenContent: MutableState<String>) {
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                screenContent = screenContent,
                title = stringResource(TrainHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = stringResource(id = R.string.about_info), fontSize = 20.sp)
        }
    }
}