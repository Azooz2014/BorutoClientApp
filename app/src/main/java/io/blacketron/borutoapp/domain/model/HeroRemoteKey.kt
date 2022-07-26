package io.blacketron.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.blacketron.borutoapp.util.HERO_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prePage: Int?,
    val nextPage: Int?
)
