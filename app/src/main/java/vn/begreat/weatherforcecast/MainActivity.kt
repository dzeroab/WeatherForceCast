package vn.begreat.weatherforcecast

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.aviran.cookiebar2.CookieBar
import vn.begreat.data.exceptions.NoConnectionException
import vn.begreat.weatherforcecast.databinding.ActivityMainBinding
import vn.begreat.weatherforcecast.detail.DetailFragmentDialog
import vn.begreat.weatherforcecast.event.EventObserver

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val adapter: WeatherAdapter by lazy { WeatherAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onGetWeatherClick(false)

//        1100661
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        binding.rvWeather.adapter = adapter
        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.itemAnimator = null

        viewModel.weathersLive.observe(this) {
            adapter.setWeathers(it)
        }


        viewModel.exceptionLive.observe(this, EventObserver { error ->
            exceptionToError(error)?.let {
                showCookieView(it)
            }
        })

        viewModel.action.observe(this, EventObserver { action ->
            onReceivedAction(action)
        })
    }


    private fun showCookieView(error: Int) {
        CookieBar.build(this)
            .setCustomView(R.layout.cookie_bar_view)
            .setAction(R.string.action_close) { CookieBar.dismiss(this) }
            .setTitle(R.string.error_happened)
            .setMessage(error)
            .setCookiePosition(Gravity.BOTTOM)
            .setEnableAutoDismiss(true)
            .setSwipeToDismiss(true)
            .show()
    }


    private fun onReceivedAction(action: MainAction) {
        when (action) {
            is WeatherDetailAction -> DetailFragmentDialog.show(supportFragmentManager, action.id)
        }
    }

    /**
     *
     */
    private fun exceptionToError(e: Throwable?): Int? {
        if (e == null) return null

        return when (e) {
            is NoConnectionException -> R.string.error_no_network
            else -> R.string.error_unexpected
        }
    }
}
