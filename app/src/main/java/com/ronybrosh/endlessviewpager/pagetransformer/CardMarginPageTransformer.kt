package com.ronybrosh.endlessviewpager.pagetransformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ronybrosh.endlessviewpager.R

class CardMarginPageTransformer : ViewPager2.PageTransformer {
    private var offset = 0f

    override fun transformPage(page: View, position: Float) {
        if (offset == 0f) {
            val cardWidth = page.findViewById<View>(R.id.cardImage).width
            offset = -page.width / 2f + (cardWidth * 0.33f)
        }

        page.translationX = offset * position
    }
}