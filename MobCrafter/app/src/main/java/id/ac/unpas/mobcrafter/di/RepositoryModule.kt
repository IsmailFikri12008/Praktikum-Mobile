package id.ac.unpas.mobcrafter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.mobcrafter.networks.MatakuliahApi
import id.ac.unpas.mobcrafter.persistences.MatakuliahDao
import id.ac.unpas.mobcrafter.repositories.MatakuliahRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMatakuliahRepository(
        api: MatakuliahApi,
        dao: MatakuliahDao
    ): MatakuliahRepository {
        return MatakuliahRepository(api, dao)
    }
}