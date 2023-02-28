package io.blacketron.borutoapp.data.repository

import io.blacketron.borutoapp.data.local.database.BorutoDB
import io.blacketron.borutoapp.domain.model.Hero

class LocalDataSourceImp(
    database: BorutoDB
) : LocalDataSource {

    val heroDao = database.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId)
    }
}