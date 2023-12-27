package com.jhbb.tracker_presentation.ui.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.MoneyTrackerDropDownMenu
import com.jhbb.core_ui.ui.components.MoneyTrackerSwitcher
import com.jhbb.core_ui.ui.components.MoneyTrackerTopBar
import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.core_ui.utils.UiEvent
import com.jhbb.tracker_presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun RegisterScreen(
    state: RegisterScreenState,
    actions: RegisterScreenActions,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        actions.uiEvent.collect { event ->
            when (event) {
                UiEvent.NavigateForward -> actions.onComplete()
                is UiEvent.ShowToast ->
                    Toast.makeText(context, event.messageResId, Toast.LENGTH_LONG).show()
            }
        }
    }
    Scaffold(
        topBar = {
            MoneyTrackerTopBar(
                title = stringResource(id = R.string.tracker_register),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { actions.onEvent(RegisterScreenEvent.OnRegister) }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                ValueSection(
                    isIncome = state.isIncome,
                    value = state.value,
                    onEvent = actions.onEvent,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .background(color = MaterialTheme.colors.background),
            ) {
                DetailsSection(
                    title = state.title,
                    description = state.description,
                    categories = state.categories,
                    onEvent = actions.onEvent
                )
            }
        }
    }
}

@Composable
private fun ValueSection(
    isIncome: Boolean,
    value: String,
    onEvent: (RegisterScreenEvent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoneyTrackerSwitcher(
            isActive = isIncome,
            onToggle = { onEvent(RegisterScreenEvent.OnToggleSwitcher(isIncome)) },
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.tracker_register_value_field),
                modifier = Modifier.alpha(0.7f),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = value,
                onValueChange = {
                    if (it.length <= Long.MAX_VALUE.toString().length) {
                        onEvent(RegisterScreenEvent.OnEnterValue(value = it))
                    }
                },
                textStyle = MaterialTheme.typography.h1.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.focusRequester(focusRequester),
            )
        }
    }
}

@Composable
private fun DetailsSection(
    title: String,
    description: String,
    categories: List<CategoryUiModel>,
    onEvent: (RegisterScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            placeholder = { Text(text = stringResource(id = R.string.tracker_register_value_title)) },
            onValueChange = {
                onEvent(RegisterScreenEvent.OnEnterTitle(it))
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = description,
            placeholder = {
                Text(text = stringResource(id = R.string.tracker_register_value_description))
            },
            onValueChange = {
                onEvent(RegisterScreenEvent.OnEnterDescription(it))
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        MoneyTrackerDropDownMenu(
            defaultText = stringResource(id = R.string.tracker_register_label_category),
            items = if (categories.isNotEmpty()) {
                categories.map { CategoryUiType.valueOf(it.type.name).description }
            } else emptyList(),
            onItemSelected = {
                onEvent(RegisterScreenEvent.OnSelectCategory(it))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@MultiThemePreview
@Composable
fun PreviewRegisterScreen() {
    val state = RegisterScreenState()
    MoneyTrackerTheme {
        RegisterScreen(
            state = state,
            actions = RegisterScreenActions(
                onComplete = {},
                uiEvent = MutableSharedFlow(),
                onEvent = {},
            )
        )
    }
}