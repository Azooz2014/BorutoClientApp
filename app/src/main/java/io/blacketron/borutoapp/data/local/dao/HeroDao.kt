package io.blacketron.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.util.HERO_DATABASE_TABLE

@Dao
interface HeroDao {

    @Query("SELECT * FROM $HERO_DATABASE_TABLE ORDER BY id ASC")
    fun getAllHeroes():PagingSource<Int, Hero>

    @Query("SELECT * FROM $HERO_DATABASE_TABLE WHERE id = :heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(list: List<Hero>)

    @Query("DELETE FROM $HERO_DATABASE_TABLE")
    suspend fun deleteAllHeroes()
}