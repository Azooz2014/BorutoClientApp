package io.blacketron.borutoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.blacketron.borutoapp.data.local.preferences.DataStoreOperations
import io.blacketron.borutoapp.data.local.preferences.DataStoreOperationsImpl
import io.blacketron.borutoapp.data.repository.MainRepository
import io.blacketron.borutoapp.data.repository.MainRepositoryImpl
import io.blacketron.borutoapp.domain.use_cases.UseCase
import io.blacketron.borutoapp.domain.use_cases.read_welcome_page_state.IsWelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.save_welcome_page_state.WelcomePageCompletedUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations{
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideMainRepository(dataStoreOperations: DataStoreOperations): MainRepository{
        return MainRepositoryImpl(dataStoreOperations)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: MainRepository): UseCase{
        return UseCase(
            welcomePageCompletedUseCase = WelcomePageCompletedUseCase(repository = repository),
            isWelcomePageCompletedUseCase = IsWelcomePageCompletedUseCase(repository = repository)
        )
    }
}