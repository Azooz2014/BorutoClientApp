package io.blacketron.borutoapp.data.local.pagin_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.blacketron.borutoapp.data.remote.BorutoApi
import io.blacketron.borutoapp.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val query: String
) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Hero> {
        return try {
            val response = borutoApi.searchHero(query)
            val heroes = response.heroes

            if (heroes.isEmpty()) {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}