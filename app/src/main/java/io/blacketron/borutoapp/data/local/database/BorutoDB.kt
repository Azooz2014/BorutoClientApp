package io.blacketron.borutoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.blacketron.borutoapp.data.local.converters.StringListTypeConverter
import io.blacketron.borutoapp.data.local.dao.HeroDao
import io.blacketron.borutoapp.data.local.dao.HeroRemoteKeysDao
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class BorutoDB:RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao
}