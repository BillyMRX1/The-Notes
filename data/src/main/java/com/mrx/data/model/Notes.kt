package com.mrx.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val body: String,
    val createdAt: Long?
) : Parcelable
