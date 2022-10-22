package com.example.notepad.data

/*
    This file is for preview purposes only
 */

data class SampleNote(val title: String, val content: String)

object SampleData {
    // Sample notes data
    val noteListSample = listOf(
        SampleNote(
            "Colleague",
            "Test...Test...Test..."
        ),
        SampleNote(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n"
        ),
        SampleNote(
            "Colleague",
            "I think Kotlin is my favorite programming language.\n" +
                    "It's so much fun!"
        ),
        SampleNote(
            "Colleague",
            "Searching for alternatives to XML layouts..."
        ),
        SampleNote(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "It's the Android's modern toolkit for building native UI." +
                    "It simplifies and accelerates UI development on Android." +
                    "Less code, powerful tools, and intuitive Kotlin APIs :)"
        ),
        SampleNote(
            "Colleague",
            "It's available from API 21+ :)"
        ),
        SampleNote(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        SampleNote(
            "Colleague",
            "Android Studio next version's name is Arctic Fox"
        ),
        SampleNote(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        SampleNote(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        SampleNote(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        SampleNote(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting"
        ),
        SampleNote(
            "Colleague",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}