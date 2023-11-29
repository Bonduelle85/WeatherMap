package com.example.weathermap.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.ViewDay
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathermap.presentation.ui.theme.DarkGray
import com.example.weathermap.presentation.ui.theme.LightBlue
import com.example.weathermap.presentation.ui.theme.LightBlueOpacity
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {
    val itemsList = listOf<TabItem>(
        TabItem(
            title = "Hours",
            unselected = Icons.Outlined.AccessTime,
            selected = Icons.Filled.AccessTimeFilled
        ),
        TabItem(
            title = "Days",
            unselected = Icons.Outlined.ViewDay,
            selected = Icons.Filled.ViewDay
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var pagerState = rememberPagerState { itemsList.size }
    val coroutineScopeForAnimation = rememberCoroutineScope()

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LightBlue, shape = RoundedCornerShape(5.dp)),
            containerColor = LightBlueOpacity,
        ) {
            itemsList.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.background(
                        shape = RoundedCornerShape(5.dp),
                        color = LightBlueOpacity
                    ),
                    unselectedContentColor = DarkGray,
                    selectedContentColor = DarkGray,
                    selected = index == selectedTabIndex,
                    onClick = {
                        coroutineScopeForAnimation.launch { selectedTabIndex = index }
                    },
                    text = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector =
                            if (index == selectedTabIndex) item.selected else item.unselected,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    when (index) {
                        0 -> {
                            items(12) {
                                HourItem()
                            }
                        }

                        1 -> {
                            items(7) {
                                DayItem()
                            }
                        }
                    }
                }
            }
        }
    }
}