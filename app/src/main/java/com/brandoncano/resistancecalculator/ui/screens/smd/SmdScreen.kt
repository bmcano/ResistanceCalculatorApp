package com.brandoncano.resistancecalculator.ui.screens.smd

import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistance
import com.brandoncano.resistancecalculator.util.isSmdInputInvalid
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
import java.util.Locale

@Composable
fun SmdScreen(
    context: Context,
    navController: NavController,
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    smdResistor: LiveData<SmdResistor>
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context, navController, viewModel, navBarPosition, smdResistor)
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    smdResistor: LiveData<SmdResistor>
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val showMenu = remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val resistor by smdResistor.observeAsState(SmdResistor())
    val code = remember { mutableStateOf(resistor.code) }
    var units by remember { mutableStateOf(resistor.units) }
    var isError by remember { mutableStateOf(resistor.isSmdInputInvalid()) }
    var picture = remember { Picture() }

    fun postSelectionActions() {
        reset = false
        viewModel.updateValues(code.value, units)
        isError = resistor.isSmdInputInvalid()
        if (!isError) {
            viewModel.saveResistorValues(resistor)
            resistor.formatResistance()
        }
    }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(stringResource(R.string.title_smd), interactionSource, showMenu) {
                ClearSelectionsMenuItem {
                    showMenu.value = false
                    reset = true
                    viewModel.clear()
                    focusManager.clearFocus()
                }
                ShareTextMenuItem(context, resistor.toString(), showMenu)
                ShareImageMenuItem(context, Symbols.APPLICATION_ID, showMenu, picture)
                FeedbackMenuItem(context, Symbols.APP_NAME, showMenu)
                AboutAppMenuItem(navController, showMenu)
            }
        },
        bottomBar = {
            AppNavigationBar(
                selection = navBarSelection,
                onClick = {
                    navBarSelection = it
                    viewModel.saveNavBarSelection(it)
                    isError = resistor.isSmdInputInvalid()
                    if (!isError) {
                        viewModel.saveResistorValues(resistor)
                        resistor.formatResistance()
                    }
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            picture = smdResistorPicture(resistor, isError)
            AppTextField(
                label = stringResource(id = R.string.hint_smd_code),
                modifier = Modifier.padding(top = 24.dp, start = 32.dp, end = 32.dp),
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
                postSelectionActions()
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.units_hint),
                modifier = Modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
                selectedOption = resistor.units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                units = it
                focusManager.clearFocus()
                postSelectionActions()
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    val app = MainActivity()
    val viewModel = viewModel<SmdResistorViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<SmdResistor>()
    ResistorCalculatorTheme {
        SmdScreen(app, NavController(app), viewModel, 0, resistor)
    }
}
