package com.ct.signaturepad2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class SignaturePad : View {
    private var mBitmap: Bitmap? = null
    private var mCanvas: Canvas? = null
    private var mPath: Path? = null
    private var mBitmapPaint: Paint? = null
    internal  var context: Context
    private var circlePaint: Paint? = null
    private var circlePath: Path? = null
    private var mPaint: Paint? = null
    private var mX: Float = 0.toFloat()
    private var mY: Float = 0.toFloat()


    constructor(context: Context) : super(context) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.color = Color.GREEN
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeWidth = 5f
        this.context = context
        mPath = Path()
        mBitmapPaint = Paint(Paint.DITHER_FLAG)
        circlePaint = Paint()
        circlePath = Path()
        circlePaint!!.isAntiAlias = true
        circlePaint!!.color = Color.BLUE
        circlePaint!!.style = Paint.Style.STROKE
        circlePaint!!.strokeJoin = Paint.Join.MITER
        circlePaint!!.strokeWidth = 4f

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.color = Color.BLACK
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeWidth = 5f
        this.context = context
        mPath = Path()
        mBitmapPaint = Paint(Paint.DITHER_FLAG)
        circlePaint = Paint()
        circlePath = Path()
        circlePaint!!.isAntiAlias = true
        circlePaint!!.color = Color.BLUE
        circlePaint!!.style = Paint.Style.STROKE
        circlePaint!!.strokeJoin = Paint.Join.MITER
        circlePaint!!.strokeWidth = 4f
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.color = Color.GREEN
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeWidth = 5f
        this.context = context
        mPath = Path()
        mBitmapPaint = Paint(Paint.DITHER_FLAG)
        circlePaint = Paint()
        circlePath = Path()
        circlePaint!!.isAntiAlias = true
        circlePaint!!.color = Color.BLUE
        circlePaint!!.style = Paint.Style.STROKE
        circlePaint!!.strokeJoin = Paint.Join.MITER
        circlePaint!!.strokeWidth = 4f
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        this.context = context
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignaturePad, defStyleAttr, 0)

        typedArray?.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBitmap!!, 0f, 0f, mBitmapPaint)
        canvas.drawPath(mPath, mPaint)
        canvas.drawPath(circlePath, circlePaint)
    }

    private fun touchStart(x: Float, y: Float) {
        mPath!!.reset()
        mPath!!.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            mPath!!.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y

            circlePath!!.reset()
            circlePath!!.addCircle(mX, mY, 30f, Path.Direction.CW)
        }
    }

    private fun touchUp() {
        mPath!!.lineTo(mX, mY)
        circlePath!!.reset()
        // commit the path to our offscreen
        mCanvas!!.drawPath(mPath, mPaint)
        // kill this so we don't double draw
        mPath!!.reset()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    companion object {
        private const val touchTolerance = 4f
    }
}
