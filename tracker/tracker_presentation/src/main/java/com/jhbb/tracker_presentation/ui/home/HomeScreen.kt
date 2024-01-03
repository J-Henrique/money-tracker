package com.jhbb.tracker_presentation.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.core_ui.ui.components.expense_card.MoneyTrackerExpenseCard
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(
    state: HomeScreenState,
    actions: HomeScreenActions,
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomSheetState.isVisible.not(),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                BottomAppBar(
                    cutoutShape = CircleShape,
                ) {
                    BottomNavigationItem(
                        selected = true,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home, contentDescription = null
                            )
                        },
                        onClick = { },
                    )
                    BottomNavigationItem(
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person, contentDescription = null
                            )
                        },
                        onClick = { },
                    )
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = bottomSheetState.isVisible.not(), enter = fadeIn(), exit = fadeOut()
            ) {
                FloatingActionButton(onClick = { actions.onRegister() }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center, isFloatingActionButtonDocked = true,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {
//                MoneyTrackerDropDownMenu(
//                    defaultText = "Mês", items = state.getMonths(), onItemSelected = {}
//                )
                IconButton(
                    onClick = { coroutineScope.launch { bottomSheetState.show() } },
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.small)
                        .border(
                            border = BorderStroke(
                                width = 1.dp, color = MaterialTheme.colors.primary
                            ), shape = MaterialTheme.shapes.small
                        )
                ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                items(state.registers.size) {
                    MoneyTrackerExpenseCard(
                        register = state.registers[it],
                        onRefresh = actions.onRefresh
                    )
                }
            }
        }
        ModalBottomSheetLayout(sheetState = bottomSheetState, sheetContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { coroutineScope.launch { bottomSheetState.hide() } }) {
                    Text(text = "Hide Sheet")
                }
            }
        }) {}
    }
}

@MultiThemePreview
@Composable
fun PreviewHomeScreen() {
    val stubItems = listOf(
        Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.EDUCATION,
            isIncome = true,
            syncStatus = SynchronizationStatus.PENDING
        ),
        Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.BAR,
            isIncome = false,
            syncStatus = SynchronizationStatus.SUCCESS
        ),
        Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.HEALTH,
            isIncome = true,
            syncStatus = SynchronizationStatus.ERROR
        )
    )
    val state = HomeScreenState().apply {
        registers.addAll(stubItems)
    }
    MoneyTrackerTheme {
        HomeScreen(state, HomeScreenActions({}, {}))
    }
}