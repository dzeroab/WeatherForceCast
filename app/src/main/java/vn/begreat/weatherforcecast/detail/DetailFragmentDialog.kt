package vn.begreat.weatherforcecast.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vn.begreat.weatherforcecast.MainViewModel
import vn.begreat.weatherforcecast.R
import vn.begreat.weatherforcecast.databinding.DetailFragmentBinding


@AndroidEntryPoint
class DetailFragmentDialog : AppCompatDialogFragment() {

    companion object {
        private const val TAG = "DetailFragmentDialog"
        private const val ARG_ID = "weather_id"

        fun show(fm: FragmentManager, id: Long) {
            val dialog =
                (fm.findFragmentByTag(TAG) as? DetailFragmentDialog) ?: DetailFragmentDialog()
            dialog.arguments = Bundle().apply {
                putLong(ARG_ID, id)
            }
            if (!dialog.isAdded) {
                dialog.show(fm, TAG)
            }

        }
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_NoTitle);
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getLong(ARG_ID)?.let {
            viewModel.getWeatherDetail(it)
        }
    }
}