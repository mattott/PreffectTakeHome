package com.ottmatt.preffectstepcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ottmatt.preffectstepcounter.ui.theme.PreffectStepCounterTheme
import com.ottmatt.preffectstepcounter.ui.fitnesstracker.FitnessTrackerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreffectStepCounterTheme {
                FitnessTrackerScreen()
            }
        }
        // todo: fetch results here
    }
}
