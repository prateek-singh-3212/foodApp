package com.example.math.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable


// FIX: https://issuetracker.google.com/issues/292138966
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberStandardBottomSheetStateFix(
    initialValue: SheetValue = SheetValue.PartiallyExpanded,
    confirmValueChange: (SheetValue) -> Boolean = { true },
    skipHiddenState: Boolean = true
): SheetState {
    val skipPartiallyExpanded = false
    return rememberSaveable(
        skipPartiallyExpanded,
        confirmValueChange,
        saver = sheetStateSaver(
            skipPartiallyExpanded = skipPartiallyExpanded,
            confirmValueChange = confirmValueChange,
            skipHiddenState = skipHiddenState
        )
    ) {
        SheetState(skipPartiallyExpanded, initialValue, confirmValueChange, skipHiddenState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun sheetStateSaver(
    skipPartiallyExpanded: Boolean,
    confirmValueChange: (SheetValue) -> Boolean,
    skipHiddenState: Boolean = false
) = Saver<SheetState, SheetValue>(
    save = { it.currentValue },
    restore = { savedValue ->
        SheetState(skipPartiallyExpanded, savedValue, confirmValueChange, skipHiddenState)
    }
)