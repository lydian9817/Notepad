package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType
import com.example.notepad.ui.theme.Shapes

@Composable
fun OrderDialog(
    updateShowDialog: () -> Unit,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    var selectedOrder by remember  { mutableStateOf(noteOrder) }
    AlertDialog(
        onDismissRequest = updateShowDialog,
        confirmButton = {
            TextButton(
                onClick = {  onOrderChange(selectedOrder) },
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = updateShowDialog,
            ) {
                Text(text = "Cancel")
            }
        },
        text = {
            Card(
                shape = Shapes.medium
            ) {
                Column {
                    Text(text = "Sort by")
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(Modifier.selectableGroup()) {
                        DefaultRadioButton(
                            text = "Title",
                            selected = selectedOrder is NoteOrder.Title,
                            onSelect = {
                                selectedOrder = NoteOrder.Title(selectedOrder.orderType)

                            }
                        )
                        DefaultRadioButton(
                            text = "Date",
                            selected = selectedOrder is NoteOrder.Date,
                            onSelect = {
                                selectedOrder = NoteOrder.Date(selectedOrder.orderType)

                            }
                        )
                    }

                    Text(text = "Order")
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(Modifier.selectableGroup()) {
                        DefaultRadioButton(
                            text = "Ascending",
                            selected = selectedOrder.orderType is OrderType.Ascending,
                            onSelect = { selectedOrder = selectedOrder.copy(OrderType.Ascending) }
                        )
                        DefaultRadioButton(
                            text = "Descending",
                            selected = selectedOrder.orderType is OrderType.Descending,
                            onSelect = { selectedOrder = selectedOrder.copy(OrderType.Descending) }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        Modifier.selectable(
            selected = selected,
            onClick = onSelect,
            role = Role.RadioButton
        )
    ) {
        RadioButton(
            selected = selected,
            onClick = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

