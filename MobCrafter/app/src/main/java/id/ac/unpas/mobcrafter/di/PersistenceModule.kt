package id.ac.unpas.mobcrafter.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.mobcrafter.persistences.AppDatabase

import id.ac.unpas.mobcrafter.persistences.PerkuliahanDao


import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "pengelolaan-perkuliahan"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun providePerkuliahanDao(appDatabase: AppDatabase): PerkuliahanDao {
        return appDatabase.perkuliahanDao()
    }

}