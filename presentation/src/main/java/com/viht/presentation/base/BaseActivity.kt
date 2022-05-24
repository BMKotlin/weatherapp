package com.viht.presentation.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.viht.presentation.R
import com.viht.presentation.utils.gone
import com.viht.presentation.utils.visible

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

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

    protected fun showProgressBar() {
        progressBar.visible()
    }

    protected fun hideProgressBar() {
        progressBar.gone()
    }

    protected fun shortShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    protected fun longShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}