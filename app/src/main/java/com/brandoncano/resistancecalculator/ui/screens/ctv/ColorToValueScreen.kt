package com.brandoncano.resistancecalculator.ui.screens.ctv

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
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
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
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ValueToColorMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.shareableText
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.data.NavigationBarOptions
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun ColorToValueScreen(
    openMenu: MutableState<Boolean>,
    resistor: ResistorCtv,
    navBarPosition: Int,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ColorToValueScreenContent(
            openMenu = openMenu,
            resistor = resistor,
            navBarPosition = navBarPosition,
            onNavigateBack = onNavigateBack,
            onClearSelectionsTapped = onClearSelectionsTapped,
            onAboutTapped = onAboutTapped,
            onValueToColorTapped = onValueToColorTapped,
            onUpdateBand = onUpdateBand,
            onNavBarSelectionChanged = onNavBarSelectionChanged,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    openMenu: MutableState<Boolean>,
    resistor: ResistorCtv,
    navBarPosition: Int,
    onNavigateBack:  () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val picture = remember { Picture() }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_color_to_value),
                interactionSource = interactionSource,
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ValueToColorMenuItem(onValueToColorTapped)
                ClearSelectionsMenuItem {
                    openMenu.value = false
                    reset = true
                    onClearSelectionsTapped()
                    focusManager.clearFocus()
                }
                ShareTextMenuItem(
                    text = resistor.shareableText(),
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DrawContent(picture) {
                ResistorLayout(resistor)
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 32.dp),
                label = R.string.number_band_hint1,
                selectedOption = resistor.band1,
                items = DropdownLists.NUMBER_LIST_NO_BLACK,
                reset = reset,
            ) {
                onUpdateBand(1, it)
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.number_band_hint2,
                selectedOption = resistor.band2,
                items = DropdownLists.NUMBER_LIST,
                reset = reset,
            ) {
                onUpdateBand(2, it)
            }
            if (navBarSelection == 2 || navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.number_band_hint3,
                    selectedOption = resistor.band3,
                    items = DropdownLists.NUMBER_LIST,
                    reset = reset,
                ) {
                    onUpdateBand(3, it)
                }
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.multiplier_band_hint,
                selectedOption = resistor.band4,
                items = DropdownLists.MULTIPLIER_LIST,
                reset = reset,
            ) {
                onUpdateBand(4, it)
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = resistor.band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                ) {
                    onUpdateBand(5, it)
                }
            }
            if (navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = resistor.band6,
                    items = DropdownLists.PPM_LIST,
                    reset = reset,
                ) {
                    onUpdateBand(6, it)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            AppDivider(onCard = false)
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = stringResource(R.string.ctv_headline_text),
                    modifier = Modifier.padding(16.dp),
                    style = textStyleHeadline(),
                )
                AppArrowCardButton(
                    ArrowCardButtonContents(
                        imageVector = Icons.Outlined.Lightbulb,
                        text = stringResource(R.string.ctv_button_text),
                        onClick = onLearnColorCodesTapped,
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen4BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            openMenu = remember { mutableStateOf(false) },
            resistor = ResistorCtv(),
            navBarPosition = 1,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueToColorTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}
