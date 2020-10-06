package com.ronybrosh.endlessviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
                CardInfo(last4Digits = "5555"),

                CardInfo(last4Digits = "1111"),
                CardInfo(last4Digits = "2222"),
                CardInfo(last4Digits = "3333"),
                CardInfo(last4Digits = "4444"),
                CardInfo(last4Digits = "5555"),

                CardInfo(last4Digits = "1111")
        )

        with(viewPager2) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == 0)
                            setCurrentItem(data.size - 2, false)
                        else if (currentItem == data.size -1)
                            setCurrentItem(1, false)
                    }
                }
            })
            adapter = EndlessViewPagerAdapter(data)
            setCurrentItem(1, false)
        }
    }
}