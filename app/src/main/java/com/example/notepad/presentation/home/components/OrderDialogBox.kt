package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType
import com.example.notepad.ui.theme.Shapes

@Composable
fun OrderDialogBox(
    updateShowDialog: () -> Unit,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    AlertDialog(
        onDismissRequest = updateShowDialog,
        confirmButton = {
            TextButton(
                onClick = {  /*TODO*/ },
            ) {
                Text(text = "Confirm")
            }
        },
        text = {
            OrderDialogBoxUi(
                noteOrder = noteOrder,
                onOrderChange = onOrderChange
            )
        }
    )
}


@Composable
fun OrderDialogBoxUi(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Card(
        shape = Shapes.medium
    ) {
        Column {
            Text(text = "Sort by")
            Spacer(modifier = Modifier.height(8.dp))
            Column(Modifier.selectableGroup()) {
                DefaultRadioButton(
                    text = "Title",
                    selected = noteOrder is NoteOrder.Title,
                    onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
                )
                DefaultRadioButton(
                    text = "Date",
                    selected = noteOrder is NoteOrder.Date,
                    onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
                )
            }

            Text(text = "Order")
            Spacer(modifier = Modifier.height(8.dp))
            Column(Modifier.selectableGroup()) {
                DefaultRadioButton(
                    text = "Ascending",
                    selected = noteOrder.orderType is OrderType.Ascending,
                    onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) }
                )
                DefaultRadioButton(
                    text = "Descending",
                    selected = noteOrder.orderType is OrderType.Descending,
                    onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) }
                )
            }
        }
    }
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

