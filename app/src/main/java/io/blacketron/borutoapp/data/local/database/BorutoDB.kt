package io.blacketron.borutoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.blacketron.borutoapp.data.local.converters.StringListTypeConverter
import io.blacketron.borutoapp.data.local.dao.HeroDao
import io.blacketron.borutoapp.data.local.dao.HeroRemoteKeyDao
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class BorutoDB:RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}