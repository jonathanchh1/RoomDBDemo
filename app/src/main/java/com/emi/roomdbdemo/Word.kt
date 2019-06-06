package com.emi.roomdbdemo

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word : String)