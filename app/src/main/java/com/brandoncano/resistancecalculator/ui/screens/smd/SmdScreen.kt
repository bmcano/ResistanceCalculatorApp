package com.brandoncano.resistancecalculator.ui.screens.smd

import android.graphics.Picture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Explicit
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.DropdownLists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.data.NavigationBarOptions
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import java.util.Locale

@Composable
fun SmdScreen(
    openMenu: MutableState<Boolean>,
    resistor: SmdResistor,
    isError: Boolean,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    navBarPosition: Int,
    onLearnSmdCodesTapped: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        SmdScreenContent(
            openMenu = openMenu,
            resistor = resistor,
            isError = isError,
            onOpenThemeDialog = onOpenThemeDialog,
            onNavigateBack = onNavigateBack,
            onClearSelectionsTapped = onClearSelectionsTapped,
            onAboutTapped = onAboutTapped,
            onValueChanged = onValueChanged,
            onNavBarSelectionChanged = onNavBarSelectionChanged,
            navBarPosition = navBarPosition,
            onLearnSmdCodesTapped = onLearnSmdCodesTapped,
        )
    }
}

@Composable
private fun SmdScreenContent(
    openMenu: MutableState<Boolean>,
    resistor: SmdResistor,
    isError: Boolean,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    navBarPosition: Int,
    onLearnSmdCodesTapped: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    var reset by remember { mutableStateOf(false) }
    val picture = remember { Picture() }
    val code = remember { mutableStateOf(resistor.code) }
    var units by remember { mutableStateOf(resistor.units) }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_smd),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem {
                    focusManager.clearFocus()
                    onClearSelectionsTapped()
                    reset = true
                }
                ShareTextMenuItem(
                    text = resistor.toString(),
                    showMenu = openMenu,
                )
                ShareImageMenuItem(
                    applicationId = Symbols.APPLICATION_ID,
                    showMenu = openMenu,
                    picture = picture,
                )
                FeedbackMenuItem(
                    app = Symbols.APP_NAME,
                    showMenu = openMenu,
                )
                AppThemeMenuItem(openMenu, onOpenThemeDialog)
                AboutAppMenuItem(onAboutTapped)
            }
        },
        bottomBar = {
            AppNavigationBar(
                selection = navBarSelection,
                onClick = {
                    navBarSelection = it
                    onNavBarSelectionChanged(it)
                },
                options = listOf(
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_three_eia),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_four_eia),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_eia_96),
                        imageVector = Icons.Outlined.Explicit,
                    ),
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DrawContent(picture) {
                SmdResistorLayout(resistor, isError)
            }
            AppTextField(
                label = stringResource(id = R.string.hint_smd_code),
                modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                value = code,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_code),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            ) {
                code.value = it.uppercase(Locale.getDefault())
                onValueChanged(code.value, units)
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.units_hint),
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                units = it
                focusManager.clearFocus()
                onValueChanged(code.value, units)
            }
            AppDivider(modifier = Modifier.padding(vertical = 24.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = stringResource(R.string.smd_headline_text),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    style = textStyleHeadline(),
                )
                AppArrowCardButton(
                    ArrowCardButtonContents(
                        imageVector = Icons.Outlined.Lightbulb,
                        text = stringResource(R.string.smd_button_text),
                        onClick = onLearnSmdCodesTapped,
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    ResistorCalculatorTheme {
        SmdScreen(
            openMenu = remember { mutableStateOf(false) },
            resistor = SmdResistor(),
            isError = false,
            onOpenThemeDialog = {},
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            navBarPosition = 1,
            onLearnSmdCodesTapped = {},
        )
    }
}
