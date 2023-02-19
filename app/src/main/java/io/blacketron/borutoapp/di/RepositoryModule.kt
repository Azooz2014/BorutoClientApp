package io.blacketron.borutoapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.blacketron.borutoapp.data.local.database.BorutoDB
import io.blacketron.borutoapp.data.local.preferences.DataStoreOperations
import io.blacketron.borutoapp.data.local.preferences.DataStoreOperationsImpl
import io.blacketron.borutoapp.data.remote.BorutoApi
import io.blacketron.borutoapp.data.repository.MainRepositoryImpl
import io.blacketron.borutoapp.data.repository.RemoteDataSource
import io.blacketron.borutoapp.data.repository.RemoteDataSourceImpl
import io.blacketron.borutoapp.domain.use_cases.UseCases
import io.blacketron.borutoapp.domain.use_cases.read_welcome_page_state.IsWelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.remote_data_source.GetAllHeroesUseCase
import io.blacketron.borutoapp.domain.use_cases.save_welcome_page_state.WelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    /*@Provides
    @Singleton
    fun provideMainRepository(
        dataStoreOperations: DataStoreOperations,
        remoteDataSource: RemoteDataSource
    ): MainRepository{
        return MainRepositoryImpl(dataStoreOperations, remoteDataSource)
    }*/

    @Provides
    @Singleton
    fun provideUseCase(repository: MainRepositoryImpl): UseCases {
        return UseCases(
            welcomePageCompletedUseCase = WelcomePageCompletedUseCase(repository = repository),
            isWelcomePageCompletedUseCase = IsWelcomePageCompletedUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository)
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDB: BorutoDB
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            borutoApi = borutoApi,
            borutoDB = borutoDB
        )
    }
}