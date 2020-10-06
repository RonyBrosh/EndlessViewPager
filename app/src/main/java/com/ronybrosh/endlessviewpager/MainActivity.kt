package com.ronybrosh.endlessviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ronybrosh.endlessviewpager.adaper.EndlessViewPagerAdapter
import com.ronybrosh.endlessviewpager.model.CardInfo
import com.ronybrosh.endlessviewpager.model.CardStatus
import com.ronybrosh.endlessviewpager.pagetransformer.CardAlphaPageTransformer
import com.ronybrosh.endlessviewpager.pagetransformer.CardDropPageTransformer
import com.ronybrosh.endlessviewpager.pagetransformer.CardMarginPageTransformer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
                CardInfo(last4Digits = "4444", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "5555", status = CardStatus.ACTIVE),

                CardInfo(last4Digits = "1111", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "2222", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "3333", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "4444", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "5555", status = CardStatus.ACTIVE),

                CardInfo(last4Digits = "1111", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "2222", status = CardStatus.FROZEN)
        )

        with(viewPager2) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == 1)
                            setCurrentItem(data.size - 3, false)
                        else if (currentItem == data.size - 2)
                            setCurrentItem(2, false)
                    }
                }
            })
            with(CompositePageTransformer()) {
                addTransformer(CardMarginPageTransformer())
                addTransformer(CardDropPageTransformer())
                addTransformer(CardAlphaPageTransformer())
                setPageTransformer(this)
            }
            adapter = EndlessViewPagerAdapter(resources, data)
            offscreenPageLimit = 2
            setCurrentItem(2, false)
        }
    }
}