package com.example.sodexo.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sodexo.R
import java.lang.ref.WeakReference

class LoadingView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    private var mContainer: WeakReference<RelativeLayout>? = null
    private var mTopLabel: WeakReference<TextView>? = null
    private var mProgressBar: WeakReference<ProgressBar>? = null
    private var mBottomLabel: WeakReference<TextView>? = null

    private var mTopLabelText: String? = null
    private var mBottomLabelText: String? = null
    private var mVisible: Boolean = false
    private var mAnchorTop: Boolean = false

    init {
        loadAttributes(attrs)
        loadViews()
        loadValues()
    }

    private fun loadAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0)
        mTopLabelText = attributes.getString(R.styleable.LoadingView_loadingViewTopLabel)
        mBottomLabelText = attributes.getString(R.styleable.LoadingView_loadingViewBottomLabel)
        mVisible = attributes.getBoolean(R.styleable.LoadingView_loadingViewVisibility, false)
        mAnchorTop = attributes.getBoolean(R.styleable.LoadingView_loadingViewAnchorTop, false)
        attributes.recycle()
    }

    private fun loadViews() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.loading_view, this, true)
        mContainer = WeakReference(rootView.findViewById(R.id.view_loading_container))
        mTopLabel = WeakReference(rootView.findViewById(R.id.view_loading_label_top))
        mProgressBar = WeakReference(rootView.findViewById(R.id.view_loading_progress_bar))
        mBottomLabel = WeakReference(rootView.findViewById(R.id.view_loading_label_bottom))
        if (mAnchorTop) mContainer?.get()?.gravity = Gravity.CENTER_HORIZONTAL
    }

    private fun loadValues() {
        if (mVisible) show() else hide()
        setTopLabel(mTopLabelText ?: context?.getString(R.string.loading_default_title))
        setBottomLabel(mBottomLabelText ?: context?.getString(R.string.loading_default_subtitle))
    }

    fun isVisible(): Boolean? {
        return mContainer?.get()?.visibility == VISIBLE
    }

    fun anchorTop() {
        mAnchorTop = true
        mContainer?.get()?.gravity = Gravity.CENTER_HORIZONTAL
    }

    fun show() {
        mContainer?.get()?.visibility = VISIBLE
    }

    fun hide() {
        mContainer?.get()?.visibility = GONE
    }

    fun setTopLabel(text: String?) {
        mTopLabel?.get()?.text = text
    }

    fun setBottomLabel(text: String?) {
        mBottomLabel?.get()?.text = text
    }

}