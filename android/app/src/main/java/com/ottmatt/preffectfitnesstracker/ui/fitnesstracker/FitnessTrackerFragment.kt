package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ottmatt.preffectfitnesstracker.ui.theme.PreffectFitnessTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FitnessTrackerFragment : Fragment() {

    companion object {
        val TAG = FitnessTrackerFragment::javaClass.name
    }

    private val viewModel by viewModels<FitnessTrackerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PreffectFitnessTrackerTheme {
                    FitnessTrackerScreen()
                }
            }
        }
    }
}