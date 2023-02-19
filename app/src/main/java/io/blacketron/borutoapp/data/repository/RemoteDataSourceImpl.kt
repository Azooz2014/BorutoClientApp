package io.blacketron.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.blacketron.borutoapp.data.local.database.BorutoDB
import io.blacketron.borutoapp.data.local.pagin_source.HeroRemoteMediator
import io.blacketron.borutoapp.data.local.pagin_source.SearchHeroesSource
import io.blacketron.borutoapp.data.remote.BorutoApi
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.util.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDB: BorutoDB
) : RemoteDataSource {

    private val heroDao = borutoDB.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDB
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { SearchHeroesSource(borutoApi, query) }
        ).flow
    }
}