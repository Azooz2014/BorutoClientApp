package io.blacketron.borutoapp.data.repository

import io.blacketron.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}