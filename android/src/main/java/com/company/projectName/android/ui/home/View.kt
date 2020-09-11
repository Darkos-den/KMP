package com.company.projectName.android.ui.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.company.projectName.android.compose.FillParentProgressIndicator
import com.company.projectName.domain.feature.home.HomeState
import com.company.projectName.entity.models.SampleModel

@Composable
fun HomeScreen(
    state: HomeState,
    reloadClick: () -> Unit
) {
    Stack {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            when (state) {
                is HomeState.Empty -> {
                    EmptyHomeScreen(reloadClick)
                }
                is HomeState.Data -> {
                    DataHomeScreen(state.list)
                }
                is HomeState.Initial -> {
                    InitialHomeScreen()
                }
            }
        }

        if (state.showProgress) {
            FillParentProgressIndicator()
        }
    }
}

@Composable
fun EmptyHomeScreen(reloadClick: ()->Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(text = "List is empty")
    }

    Button(
        onClick = reloadClick,
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
    ) {
        Text(text = "Reload content")
    }
}

@Composable
fun DataHomeScreen(items: List<SampleModel>) {
    TODO()
}

@Composable
fun InitialHomeScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(text = "Skeletons mock")
    }
}

@Preview
@Composable
fun initialPreview() {
    HomeScreen(
        state = HomeState.Initial,
        reloadClick = {}
    )
}