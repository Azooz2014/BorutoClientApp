package io.blacketron.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.blacketron.borutoapp.domain.model.HeroRemoteKey
import io.blacketron.borutoapp.util.HERO_REMOTE_KEY_DATABASE_TABLE

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM $HERO_REMOTE_KEY_DATABASE_TABLE WHERE id = :id")
    suspend fun getRemoteKey(id: Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

    @Query("DELETE FROM $HERO_REMOTE_KEY_DATABASE_TABLE")
    suspend fun deleteAllRemoteKeys()
}