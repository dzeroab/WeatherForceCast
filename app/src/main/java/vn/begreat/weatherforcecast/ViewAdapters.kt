package vn.begreat.weatherforcecast

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter


///
@BindingAdapter("goneUnless")
fun goneUnless(view: View, isVisible: Boolean?) {
    view.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("showTextIf")
fun setTextIf(view: TextView, id: Int?) {
    if (id != null && id > 0) {
        view.setText(id)
    } else {
        view.text = ""
    }
}