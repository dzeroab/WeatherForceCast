package vn.begreat.weatherforcecast.opt_in

import android.content.Context
import com.facebook.stetho.Stetho
import vn.begreat.weatherforcecast.BuildConfig

///
class StethoModuleOptIn : ModuleOptIn {
    override fun init(context: Context) {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(context)
        }
    }
}