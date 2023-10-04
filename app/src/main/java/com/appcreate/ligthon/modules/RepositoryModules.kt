package com.appcreate.ligthon.modules

import com.appcreate.ligthon.domain.repos.DbPowerTrackRepository
import com.appcreate.ligthon.domain.repos.PowerTrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindDbRepository(dbPowerTrackRepository: DbPowerTrackRepository): PowerTrackRepository
}