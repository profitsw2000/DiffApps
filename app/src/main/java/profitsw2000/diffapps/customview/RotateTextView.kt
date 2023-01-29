package profitsw2000.diffapps.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class RotateTextView : AppCompatTextView {
    private val mPaint = paint

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val viewWidth = width
        val viewHeight = height
        canvas.translate((viewWidth / 2).toFloat(), (viewHeight / 2).toFloat())
        for (i in 0..9) {
            val message = "Котик"
            canvas.drawText(message, 30f, 0f, mPaint)
            canvas.rotate(36f)
        }
    }

    private fun init() {
        mPaint.color = Color.BLUE
        mPaint.textSize = 50f
        mPaint.isAntiAlias = true
    }
}