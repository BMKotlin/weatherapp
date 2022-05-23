package com.viht.weathermvvm.ui.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.viht.weathermvvm.R
import com.viht.weathermvvm.utils.gone
import com.viht.weathermvvm.utils.visible

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    internal lateinit var binding: VB

    private lateinit var progressBar: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)

        initProgressBar()
        hideProgressBar()
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    //Loading
    private fun initProgressBar() {
        progressBar = CircularProgressIndicator(this)
        progressBar.isIndeterminate = true
        progressBar.setIndicatorColor(ContextCompat.getColor(this, R.color.black))
        progressBar.trackColor = ContextCompat.getColor(this, R.color.gray)
        val relativeLayout = RelativeLayout(this)
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.addView(progressBar)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        this.addContentView(relativeLayout, params)
    }

    internal fun showProgressBar() {
        progressBar.visible()
    }

    internal fun hideProgressBar() {
        progressBar.gone()
    }
}