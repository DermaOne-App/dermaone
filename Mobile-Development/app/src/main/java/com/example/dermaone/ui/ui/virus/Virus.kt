package com.example.dermaone.ui.ui.virus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Virus(
    val name: String,
    val explanation: String,
    val type: String,
    val imageResourceId: Int
) : Parcelable