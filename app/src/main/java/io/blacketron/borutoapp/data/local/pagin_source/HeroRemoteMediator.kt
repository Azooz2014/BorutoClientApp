package io.blacketron.borutoapp.data.local.pagin_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import io.blacketron.borutoapp.data.local.database.BorutoDB
import io.blacketron.borutoapp.data.remote.BorutoApi
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.domain.model.HeroRemoteKeys
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDB
): RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): RemoteMediator.MediatorResult {
        return try {

            val page = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = borutoApi.getAllHeroes(page = page)

            if(response.heroes.isNotEmpty()){
                borutoDatabase.withTransaction {
                    if (loadType === LoadType.REFRESH){
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage

                    val remoteKeys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id ,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    heroDao.insertHeroes(response.heroes)
                    heroRemoteKeysDao.insertAllRemoteKeys(remoteKeys)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {

        return state.pages.firstOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKeys(hero.id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {

        return state.pages.lastOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKeys(hero.id)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }
}