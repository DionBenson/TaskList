package com.example.tasklist

// task object that holds the description/title of the task and a checked or not boolean

data class Task (val title: String, var isChecked: Boolean = false )