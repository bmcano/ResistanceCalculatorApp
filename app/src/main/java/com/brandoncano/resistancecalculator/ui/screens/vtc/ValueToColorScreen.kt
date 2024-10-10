package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.graphics.Picture
import android.os.Build
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.DropdownLists
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.shareableText
import com.brandoncano.sharedcomponents.composables.AppButton
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
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
    isResistanceStandard: ESeriesCardContent,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueChanged: (String, String, String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onValidateResistanceTapped: () -> Unit,
    onUseValueTapped: () -> String,
    onLearnMoreTapped: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ValueToColorScreenContent(
            resistor = resistor,
            navBarPosition = navBarPosition,
            isError = isError,
            openMenu = openMenu,
            isResistanceStandard = isResistanceStandard,
            onOpenThemeDialog = onOpenThemeDialog,
            onNavigateBack = onNavigateBack,
            onClearSelectionsTapped = onClearSelectionsTapped,
            onAboutTapped = onAboutTapped,
            onColorToValueTapped = onColorToValueTapped,
            onValueChanged = onValueChanged,
            onNavBarSelectionChanged = onNavBarSelectionChanged,
            onValidateResistanceTapped = onValidateResistanceTapped,
            onUseValueTapped = onUseValueTapped,
            onLearnMoreTapped = onLearnMoreTapped,
        )
    }
}

@Composable
private fun ValueToColorScreenContent(
    resistor: ResistorVtc,
    navBarPosition: Int,
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    isResistanceStandard: ESeriesCardContent,
    onOpenThemeDialog: () -> Unit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueChanged: (String, String, String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onValidateResistanceTapped: () -> Unit,
    onUseValueTapped: () -> String,
    onLearnMoreTapped: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val picture = remember { Picture() }
    val resistance = remember { mutableStateOf(resistor.resistance) }

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ShareImageMenuItem(
                        applicationId = Symbols.APPLICATION_ID,
                        showMenu = openMenu,
                        picture = picture,
                    )
                }
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
            ResistorDisplay(picture, resistor, isError)
            AppTextField(
                label = stringResource(id = R.string.type_resistance_hint),
                modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                value = resistance,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_resistance)
            ) {
                resistance.value = it
                onValueChanged(resistance.value, resistor.units, resistor.band5, resistor.band6)
                reset = false
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.units_hint),
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
                selectedOption = resistor.units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                focusManager.clearFocus()
                onValueChanged(resistance.value, it, resistor.band5, resistor.band6)
                reset = false
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = resistor.band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                    isValueToColor = true
                ) {
                    focusManager.clearFocus()
                    onValueChanged(resistance.value, resistor.units, it, resistor.band6)
                    reset = false
                }
            }
            if (navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = resistor.band6,
                    items = DropdownLists.PPM_LIST,
                    reset = reset,
                    isValueToColor = true,
                ) {
                    focusManager.clearFocus()
                    onValueChanged(resistance.value, resistor.units, resistor.band5, it)
                    reset = false
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            AppButton(
                label = stringResource(R.string.vtc_validate_e_series_button),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                onValidateResistanceTapped()
            }
            ESeriesCard(
                eSeriesCardContent = isResistanceStandard,
                onLearnMoreTapped = onLearnMoreTapped,
                onUseValueTapped = {
                    resistance.value = onUseValueTapped()
                }
            )
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
            isResistanceStandard = ESeriesCardContent.NoContent,
            onOpenThemeDialog = {},
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onColorToValueTapped = {},
            onValueChanged = { _, _, _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onValidateResistanceTapped = {},
            onUseValueTapped = { "" },
            onLearnMoreTapped = {},
        )
    }
}
