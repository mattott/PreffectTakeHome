package com.ottmatt.preffectstepcounter.ui.fitnesstracker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ottmatt.preffectstepcounter.R
import com.ottmatt.preffectstepcounter.ui.theme.PreffectStepCounterTheme
import com.ottmatt.preffectstepcounter.ui.theme.SubtitleStyle
import com.ottmatt.preffectstepcounter.ui.theme.TitleStyle

@Composable
fun FitnessTrackerScreen(viewModel: FitnessTrackerViewModel = viewModel()) {
    // TODO: different styles for daily count and goal
    ConstraintLayout(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        val (dailyCount, dailyGoal) = createRefs()
        DailyStepCount(
            viewModel.fitnessUiState.collectAsState(),
            Modifier.constrainAs(dailyCount) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(dailyGoal.top)
            }
        )

        PersonalDailyGoal(
            viewModel.fitnessGoalUiState.collectAsState(),
            Modifier.constrainAs(dailyGoal) {
                top.linkTo(dailyCount.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
        createVerticalChain(dailyCount, dailyGoal, chainStyle = ChainStyle.Packed)
    }
}

@Composable
fun DailyStepCount(fitnessUiState: State<FitnessUiState>, modifier: Modifier) {
    Card(
        modifier = modifier.then(Modifier.padding(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (title, subtitle) = createRefs()
            Text(
                text = stringResource(id = R.string.daily_step_count),
                style = TitleStyle,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(subtitle.top)
                }
            )

            Text(
                text = "${fitnessUiState.value.currentSteps}",
                style = SubtitleStyle,
                modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
            createVerticalChain(title, subtitle, chainStyle = ChainStyle.Packed)
        }
    }
}

@Composable
fun PersonalDailyGoal(fitnessGoalUiState: State<FitnessGoalUiState>, modifier: Modifier) {
    Card(
        modifier = modifier.then(Modifier.padding(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (title, subtitle) = createRefs()
            Text(
                text = stringResource(id = R.string.personal_daily_goal),
                style = TitleStyle,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(subtitle.top)
                }
            )

            Text(
                text = "${fitnessGoalUiState.value.stepsInGoal}",
                style = SubtitleStyle,
                modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
            createVerticalChain(title, subtitle, chainStyle = ChainStyle.Packed)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepTrackerPreview() {
    PreffectStepCounterTheme {
        FitnessTrackerScreen()
    }
}