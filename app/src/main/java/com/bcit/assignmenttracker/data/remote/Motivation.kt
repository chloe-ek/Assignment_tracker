package com.bcit.assignmenttracker.data.remote

import com.google.gson.annotations.SerializedName

data class Motivation (
    @SerializedName("q") val q: String,
    @SerializedName("a") val a: String
)