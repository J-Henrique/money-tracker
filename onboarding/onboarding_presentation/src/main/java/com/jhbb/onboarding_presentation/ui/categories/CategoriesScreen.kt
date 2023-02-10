package com.jhbb.onboarding_presentation.ui.categories

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.ErrorDialog
import com.jhbb.core_ui.ui.components.MoneyTrackerTopBar
import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import com.jhbb.core_ui.ui.components.category_card.MoneyTrackerCategoryItem
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.core_ui.utils.UiEvent
import com.jhbb.onboarding_presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun CategoriesScreen(
    state: CategoriesScreenState,
    actions: CategoriesScreenActions,
) {
    val animateComponents = remember {
        MutableTransitionState(true).apply {
            targetState = true
        }
    }
    LaunchedEffect(true) {
        actions.uiEvent.collect { event ->
            when (event) {
                UiEvent.NavigateForward -> actions.onNext()
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MoneyTrackerTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { actions.onEvent(CategoriesScreenEvent.OnNextButtonClick) }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    ) { paddingValues ->
        AnimatedVisibility(
            visibleState = animateComponents,
            enter = fadeIn() + slideInHorizontally()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.onboarding_categories),
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 56.dp, top = 128.dp, bottom = 56.dp)
                    )
                }
                items(state.categories) { category ->
                    MoneyTrackerCategoryItem(category) {
                        actions.onEvent(CategoriesScreenEvent.OnItemClick(it))
                    }
                }
            }
        }
        if (state.showError) {
            ErrorDialog(onDismiss = { actions.onEvent(CategoriesScreenEvent.OnErrorDialogDismiss) })
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewCategoriesScreen() {
    MoneyTrackerTheme {
        CategoriesScreen(
            state = CategoriesScreenState(
                categories = listOf(
                    CategoryUiModel(1, CategoryUiType.BAR, true),
                    CategoryUiModel(2, CategoryUiType.FOOD, false),
                    CategoryUiModel(3, CategoryUiType.EDUCATION, true),
                    CategoryUiModel(4, CategoryUiType.PETS, true)
                ),
            ),
            actions = CategoriesScreenActions(
                onNext = {},
                uiEvent = MutableSharedFlow(),
                onEvent = {}
            )
        )
    }
}