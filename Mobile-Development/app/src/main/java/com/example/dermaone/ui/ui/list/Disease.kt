package com.example.dermaone.ui.ui.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Disease(
    val name: String,
    val reason: String,
    val symptom: String,
    val imageResourceId: Int
) : Parcelable