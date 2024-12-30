package com.ottmatt.preffectfitnesstracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ottmatt.preffectfitnesstracker.ui.fitnesstracker.FitnessTrackerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fitnessTrackerFragment = FitnessTrackerFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fitnessTrackerFragment, FitnessTrackerFragment.TAG)
            .commit()
    }

}
