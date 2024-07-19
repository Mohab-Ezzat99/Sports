package com.app.sports.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.sports.data.local.entity.TeamEntity

@Database(
    entities = [TeamEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {
    abstract val dao: AppDao
}
