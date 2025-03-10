package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.data.OnBoardingModel
import com.a2.swifting_fitness.screens.compose.CustomButton
import com.a2.swifting_fitness.view_model.OnBoardingViewModel
import com.a2.swifting_fitness.view_model.SplashViewModel
import com.a2.swifting_fitness.view_model.SplashViewModelFactory
import kotlinx.coroutines.launch


@Preview
@Composable
private fun Preview() {
    OnBoardingScreen(goToLogin = {

    })
}


@Composable
fun OnBoardingScreen(goToLogin: () -> Unit) {
    val context = LocalContext.current
    val viewModel: SplashViewModel = viewModel(factory = SplashViewModelFactory(context))
    val data = OnBoardingViewModel()
    val welcomeScreenCount = 1
    val pageCount = data.onBoardingModel.size + welcomeScreenCount
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pageCount })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize(1f)
    ) { pageIndex ->
        if (pageIndex == 0) {
            WelcomeCard(pagerState)
        } else {
            OnBoardingCard(
                data.onBoardingModel[pageIndex - welcomeScreenCount],
                goNext = {
                    if (pageIndex == pageCount - 1) {
                        scope.launch {
                            viewModel.setNotFirstTime()
                            goToLogin()
                        }

                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pageIndex + 1)
                        }

                    }
                },
                goPrevious = {
                    scope.launch {
                        pagerState.animateScrollToPage(pageIndex - 1)
                    }

                },
            )
        }
    }


}

@Composable
fun OnBoardingCard(data: OnBoardingModel, goNext: () -> Unit, goPrevious: () -> Unit) {
    Box {
        Image(
            painter = painterResource(data.imageRes),
            contentDescription = "On Boarding Images",
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x11000000))
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(
                    20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                data.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(10.0.dp))
            Text(
                data.description,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(40.0.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                NextPreviousButton(
                    resources = R.drawable.solid_arrow_right_sm,
                    onPressed = {
                        goPrevious()
                    },
                    description = "Go Previous",
                )
                Spacer(modifier = Modifier.size(30.0.dp))
                NextPreviousButton(
                    resources = R.drawable.monotone_arrow_right,
                    onPressed = {
                        goNext()
                    },
                    description = "Go Next",
                )
            }
            Spacer(modifier = Modifier.size(60.0.dp))
        }
    }
}

@Composable
fun NextPreviousButton(modifier:Modifier=Modifier,resources: Int, onPressed: () -> Unit, description: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color(0x66FFFFFF), shape = RoundedCornerShape(12.dp))
            .clickable {
                onPressed()
            }
            .height(75.dp)
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))

    ) {
        Image(
            painter = painterResource(resources),
            contentDescription = description
        )
    }
}

@Composable
fun WelcomeCard(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    Box {
        Image(
            painter = painterResource(R.drawable.onboarding1),
            contentDescription = "Welcome Image",
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Welcome To Fitness App!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(10.0.dp))
            Text(
                "Look good, Feel good!",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(40.0.dp))
            CustomButton(modifier = Modifier.width(200.dp), onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }, title = "Get Started")
            Spacer(modifier = Modifier.size(60.0.dp))
        }
    }
}