package io.blacketron.borutoapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.blacketron.borutoapp.data.local.database.BorutoDB
import io.blacketron.borutoapp.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        BorutoDB::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}