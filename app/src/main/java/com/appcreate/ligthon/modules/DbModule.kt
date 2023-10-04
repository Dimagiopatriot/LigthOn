package com.appcreate.ligthon.modules

import android.content.Context
import androidx.room.Room
import com.appcreate.ligthon.db.AppDatabase
import com.appcreate.ligthon.db.PowerTrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun providePowerTrackDao(appDatabase: AppDatabase): PowerTrackDao {
        return appDatabase.getPowerTrackDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "PowerTrackerDb"
        ).fallbackToDestructiveMigration()
            .build()
    }
}