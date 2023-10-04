package com.appcreate.ligthon.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PowerTrackDb::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPowerTrackDao(): PowerTrackDao
}