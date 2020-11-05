package vn.begreat.weatherforcecast.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import vn.begreat.weatherforcecast.opt_in.ModuleOptIn
import javax.inject.Inject

///
@HiltAndroidApp
class ForceCastApp : Application() {

    @Inject
    lateinit var optIns: MutableSet<ModuleOptIn>

    override fun onCreate() {
        super.onCreate()
        optIns.forEach {
            it.init(this)
        }
    }
}