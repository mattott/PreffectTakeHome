package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ottmatt.preffectfitnesstracker.R
import com.ottmatt.preffectfitnesstracker.ui.theme.PreffectFitnessTrackerTheme
import com.ottmatt.preffectfitnesstracker.ui.theme.SubtitleStyle
import com.ottmatt.preffectfitnesstracker.ui.theme.TitleStyle

@Composable
fun FitnessTrackerScreen(viewModel: FitnessTrackerViewModel = viewModel()) {
    ConstraintLayout(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        val (dailyCount, dailyGoal) = createRefs()
        FitnessCardWithProgress(
            viewModel.stepCountUiState.collectAsState(),
            R.string.daily_step_count,
            Modifier.constrainAs(dailyCount) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(dailyGoal.top)
            }
        )

        FitnessCardWithProgress(
            viewModel.stepCountGoalUiState.collectAsState(),
            R.string.personal_daily_goal,
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
fun <T> FitnessCardWithProgress(
    state: State<FitnessUiState<T>>,
    @StringRes titleResId: Int,
    modifier: Modifier
) {
    Card(
        modifier = modifier.then(Modifier.padding(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (title, subtitle, isLoading) = createRefs()
            Text(
                text = stringResource(id = titleResId),
                style = TitleStyle,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(subtitle.top)
                }
            )

            Text(
                text = "${state.value.fitnessValue}",
                style = SubtitleStyle,
                modifier = Modifier
                    .constrainAs(subtitle) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(PaddingValues(top = 6.dp))
                    .alpha(if (state.value.isLoading) 0f else 1f)
            )

            if (state.value.isLoading) {
                Box(
                    modifier = Modifier.constrainAs(isLoading) {
                        top.linkTo(subtitle.top)
                        start.linkTo(subtitle.start)
                        end.linkTo(subtitle.end)
                        bottom.linkTo(subtitle.bottom)
                    },
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(24.dp)
                            .aspectRatio(1f),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
            createVerticalChain(title, subtitle, chainStyle = ChainStyle.Packed)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepTrackerPreview() {
    PreffectFitnessTrackerTheme {
        FitnessTrackerScreen()
    }
}