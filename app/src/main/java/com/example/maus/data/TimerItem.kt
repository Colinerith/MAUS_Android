package com.example.maus.data

import java.io.Serializable

data class TimerItem(
        var key:String,
        var date: String,
        var day: String,
        var time: String,
        var on:String,
        var turningOn: String // 불을 켜는 동작이면 1, 끄는 동작이면 0
): Serializable
