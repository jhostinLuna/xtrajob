package com.droidper.xtrajob.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_day")
data class RecordDay (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "start_day") val startDay: Long = 0,
    @ColumnInfo(name = "end_day") val endDay:Long = 0,
    @ColumnInfo(name = "start_break_work") val startBreakWork: Long = 0,
    @ColumnInfo(name = "end_break_work") val endBreakWork: Long = 0,
    @ColumnInfo(name = "is_worked") val isWorked: Boolean = false,
    @ColumnInfo(name = "observations") val observations: String = ""
        )