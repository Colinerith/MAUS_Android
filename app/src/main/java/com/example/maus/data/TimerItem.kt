package com.example.maus.data

import java.io.Serializable

data class TimerItem(
        var date: String?,
        var day: String?,
        var time: String,
        var on:String
): Serializable
