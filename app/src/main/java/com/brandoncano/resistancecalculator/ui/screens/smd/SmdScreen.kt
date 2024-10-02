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
import androidx.compose.material.icons.outlined.Looks
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.TempAppTextField
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.isSmdInputInvalid
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.NavigationBarOptions
import java.util.Locale

@Composable
fun SmdScreen(
    openMenu: MutableState<Boolean>,
    resistor: LiveData<SmdResistor>,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, SmdResistor) -> Unit,
    onNavBarSelectionChanged: (Int, SmdResistor) -> Unit,
    navBarPosition: Int,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        SmdScreenContent(
            openMenu = openMenu,
            resistor = resistor,
            onClearSelectionsTapped = onClearSelectionsTapped,
            onAboutTapped = onAboutTapped,
            onValueChanged = onValueChanged,
            onNavBarSelectionChanged = onNavBarSelectionChanged,
            navBarPosition = navBarPosition,
        )
    }
}

@Composable
private fun SmdScreenContent(
    openMenu: MutableState<Boolean>,
    resistor: LiveData<SmdResistor>,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, SmdResistor) -> Unit,
    onNavBarSelectionChanged: (Int, SmdResistor) -> Unit,
    navBarPosition: Int,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val resistorState by resistor.observeAsState(SmdResistor())
    var reset by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(resistorState.isSmdInputInvalid()) }
    val picture = remember { Picture() }
    val code = remember { mutableStateOf(resistorState.code) }
    var units by remember { mutableStateOf(resistorState.units) }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_smd),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
            ) {
                ClearSelectionsMenuItem {
                    focusManager.clearFocus()
                    onClearSelectionsTapped()
                    reset = true
                }
                ShareTextMenuItem(
                    context = context,
                    text = resistorState.toString(),
                    showMenu = openMenu,
                )
                ShareImageMenuItem(
                    context = context,
                    applicationId = Symbols.APPLICATION_ID,
                    showMenu = openMenu,
                    picture = picture,
                )
                FeedbackMenuItem(
                    context = context,
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
                    onNavBarSelectionChanged(it, resistorState)
                    isError = resistorState.isSmdInputInvalid()
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
                        imageVector = Icons.Outlined.Looks,
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
                SmdResistorLayout(resistorState, isError)
            }
            TempAppTextField(
                label = stringResource(id = R.string.hint_smd_code),
                modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp),
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
                reset = false
                code.value = it.uppercase(Locale.getDefault())
                onValueChanged(code.value, units, resistorState)
                isError = resistorState.isSmdInputInvalid()
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.units_hint),
                modifier = Modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                reset = false
                focusManager.clearFocus()
                units = it
                onValueChanged(code.value, units, resistorState)
                isError = resistorState.isSmdInputInvalid()
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
            resistor = MutableLiveData(),
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onNavBarSelectionChanged = { _, _ -> },
            navBarPosition = 1,
        )
    }
}
