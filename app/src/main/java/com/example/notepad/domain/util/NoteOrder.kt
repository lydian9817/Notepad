package com.example.notepad.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title (orderType: OrderType): NoteOrder(orderType)
    class Date (orderType: OrderType): NoteOrder(orderType)

    // Function to keep the order type (title or date) when changing the
    // sort order to ascending or descending
    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}