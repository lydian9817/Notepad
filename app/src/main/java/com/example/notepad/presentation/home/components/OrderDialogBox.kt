package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun OrderDialogBox(updateShowDialog: () -> Unit) {
    val orderType = listOf("Date", "Title")
    val order = listOf("Ascending", "Descending")
    val (selectedType, onTypeSelected) = remember { mutableStateOf(orderType[0]) }
    val (selectedOrder, onOrderSelected) = remember { mutableStateOf(order[0]) }

    Dialog(onDismissRequest = updateShowDialog) {
        OrderDialogBoxUi(
            orderType = orderType,
            order = order,
            selectedType = selectedType,
            onTypeSelected = onTypeSelected,
            selectedOrder = selectedOrder,
            onOrderSelected = onOrderSelected,
            updateShowDialog = updateShowDialog
        )
    }
}

@Composable
fun OrderDialogBoxUi(
    orderType: List<String>,
    order: List<String>,
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    selectedOrder: String,
    onOrderSelected: (String) -> Unit,
    updateShowDialog: () -> Unit
) {
    Card() {
        Column {
            Text(text = "Sort by")
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonGroup(
                orderList = orderType,
                selected = selectedType,
                onSelected = onTypeSelected
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Order")

            Spacer(modifier = Modifier.height(8.dp))

            RadioButtonGroup(
                orderList = order,
                selected = selectedOrder,
                onSelected = onOrderSelected
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = updateShowDialog) {
                Text(text = "Confirm")
            }
        }
    }
}

@Composable
fun RadioButtonGroup(orderList: List<String>, selected: String, onSelected: (String) -> Unit) {
    Column(Modifier.selectableGroup()) {
        orderList.forEach { type ->
            Row(
                Modifier
                    .selectable(
                        selected = (type == selected),
                        onClick = { onSelected(type) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (type == selected),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = type,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

