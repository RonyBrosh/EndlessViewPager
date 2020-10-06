package com.ronybrosh.endlessviewpager.pagetransformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class CardAlphaPageTransformer : ViewPager2.PageTransformer {
    private val alphaFactor = 0.5f

    override fun transformPage(page: View, position: Float) {
        page.alpha = 1 - abs(alphaFactor * position)
    }
}