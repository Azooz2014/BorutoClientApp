package io.blacketron.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.blacketron.borutoapp.domain.model.HeroRemoteKeys
import io.blacketron.borutoapp.util.HERO_REMOTE_KEYS_DATABASE_TABLE

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM $HERO_REMOTE_KEYS_DATABASE_TABLE WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM $HERO_REMOTE_KEYS_DATABASE_TABLE")
    suspend fun deleteAllRemoteKeys()
}