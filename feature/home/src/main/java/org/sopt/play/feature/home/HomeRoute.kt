package org.sopt.play.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.feature.home.screen.HomeScreen

@Composable
fun HomeRoute(
    vieModel: HomeViewModel = viewModel()
){
    val uiState by vieModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(

    )
}