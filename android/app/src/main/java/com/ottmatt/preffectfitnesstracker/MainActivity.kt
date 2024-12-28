package com.ottmatt.preffectfitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ottmatt.preffectfitnesstracker.ui.theme.PreffectFitnessTrackerTheme
import com.ottmatt.preffectfitnesstracker.ui.fitnesstracker.FitnessTrackerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreffectFitnessTrackerTheme {
                FitnessTrackerScreen()
            }
        }
        // todo: fetch results here
    }
}
