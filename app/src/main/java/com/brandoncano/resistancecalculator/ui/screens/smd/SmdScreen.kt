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
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppMenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.SmdNavigationBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistance
import com.brandoncano.resistancecalculator.util.isSmdInputInvalid
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
    var code by remember { mutableStateOf(resistor.code) }
    var units by remember { mutableStateOf(resistor.units) }
    var isError by remember { mutableStateOf(resistor.isSmdInputInvalid()) }
    var picture = remember { Picture() }

    fun postSelectionActions() {
        reset = false
        viewModel.updateValues(code, units)
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
                ShareImageMenuItem(context, showMenu, picture)
                FeedbackMenuItem(context, showMenu)
                AboutAppMenuItem(navController, showMenu)
            }
        },
        bottomBar = {
            SmdNavigationBar(navBarSelection) {
                navBarSelection = it
                viewModel.saveNavBarSelection(it)
                isError = resistor.isSmdInputInvalid()
                if (!isError) {
                    viewModel.saveResistorValues(resistor)
                    resistor.formatResistance()
                }
            }
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
                modifier = Modifier.padding(top = 24.dp),
                label = R.string.hint_smd_code,
                text = code,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_code),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            ) {
                code = it.uppercase(Locale.getDefault())
                postSelectionActions()
            }
            AppDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.units_hint,
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
