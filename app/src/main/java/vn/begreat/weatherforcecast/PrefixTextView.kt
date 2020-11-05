package vn.begreat.weatherforcecast

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class PrefixTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        initialize(context, attrs)
    }

    private var prefix: String? = null


    private fun initialize(context: Context, attrs: AttributeSet? = null) {
        // ignore
        attrs ?: return

        context.obtainStyledAttributes(attrs, R.styleable.PrefixTextView, 0, 0).run {
            prefix = getString(R.styleable.PrefixTextView_tvPrefix)
            recycle()
        }

        setText(text)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val newText = prefix?.let {
            if (text != null) "$it $text" else it
        } ?: text

        super.setText(newText, type)
    }
}