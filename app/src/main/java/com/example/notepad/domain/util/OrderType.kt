package com.example.notepad.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()

}
