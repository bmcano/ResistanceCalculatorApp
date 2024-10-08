package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.DropdownLists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.shareableText
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
import com.brandoncano.sharedcomponents.data.NavigationBarOptions

@Composable
fun ValueToColorScreen(
    resistor: ResistorVtc,
    navBarPosition: Int,
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueChanged: (String, String, String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ValueToColorScreenContent(
            resistor = resistor,
            navBarPosition = navBarPosition,
            isError = isError,
            openMenu = openMenu,
            onOpenThemeDialog = onOpenThemeDialog,
            onNavigateBack = onNavigateBack,
            onClearSelectionsTapped = onClearSelectionsTapped,
            onAboutTapped = onAboutTapped,
            onColorToValueTapped = onColorToValueTapped,
            onValueChanged = onValueChanged,
            onNavBarSelectionChanged = onNavBarSelectionChanged,
        )
    }
}

@Composable
private fun ValueToColorScreenContent(
    resistor: ResistorVtc,
    navBarPosition: Int,
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueChanged: (String, String, String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val picture = remember { Picture() }
    val resistance = remember { mutableStateOf(resistor.resistance) }
    var units by remember { mutableStateOf(resistor.units) }
    var band5 by remember { mutableStateOf(resistor.band5) }
    var band6 by remember { mutableStateOf(resistor.band6) }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_value_to_color),
                interactionSource = interactionSource,
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ColorToValueMenuItem(onColorToValueTapped)
                ClearSelectionsMenuItem {
                    reset = true
                    onClearSelectionsTapped()
                    focusManager.clearFocus()
                }
                ShareTextMenuItem(
                    text = resistor.shareableText(),
                    showMenu = openMenu
                )
                ShareImageMenuItem(
                    applicationId = Symbols.APPLICATION_ID,
                    showMenu = openMenu,
                    picture = picture
                )
                FeedbackMenuItem(
                    app = Symbols.APP_NAME,
                    showMenu = openMenu
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
                        label = stringResource(id = R.string.navbar_three_band),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_four_band),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_five_band),
                        imageVector = Icons.Outlined.Looks5,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_six_band),
                        imageVector = Icons.Outlined.Looks6,
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawContent(picture) {
                ResistorLayout(resistor, isError)
            }
            AppTextField(
                label = stringResource(id = R.string.type_resistance_hint),
                modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                value = resistance,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_resistance)
            ) {
                resistance.value = it
                onValueChanged(resistance.value, units, band5, band6)
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
                onValueChanged(resistance.value, units, band5, band6)
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                    isValueToColor = true
                ) {
                    band5 = it
                    focusManager.clearFocus()
                    onValueChanged(resistance.value, units, band5, band6)
                }
            }
            if (navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.PPM_LIST,
                    reset = reset,
                    isValueToColor = true,
                ) {
                    band6 = it
                    focusManager.clearFocus()
                    onValueChanged(resistance.value, units, band5, band6)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@AppScreenPreviews
@Composable
private fun ValueToColorScreenPreview() {
    ResistorCalculatorTheme {
        ValueToColorScreen(
            resistor = ResistorVtc(),
            navBarPosition = 1,
            isError = false,
            openMenu = remember { mutableStateOf(false) },
            onOpenThemeDialog = {},
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onColorToValueTapped = {},
            onValueChanged = { _, _, _, _ -> },
            onNavBarSelectionChanged = { _ -> },
        )
    }
}
