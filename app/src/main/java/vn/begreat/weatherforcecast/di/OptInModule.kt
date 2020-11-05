package vn.begreat.weatherforcecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet
import vn.begreat.weatherforcecast.opt_in.ModuleOptIn
import vn.begreat.weatherforcecast.opt_in.StethoModuleOptIn


@InstallIn(ApplicationComponent::class)
@Module
class OptInModule {
    @Provides
    @IntoSet
    fun provideStetho(): ModuleOptIn = StethoModuleOptIn()
}