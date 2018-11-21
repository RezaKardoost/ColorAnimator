package io.github.rezakardoost.coloranimator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import io.github.rezakardoost.library.ColorAnimator

class MainActivity : AppCompatActivity() {

    var ca: ColorAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colors = arrayOf(Color.parseColor("#8adcd3")
                ,Color.parseColor("#74b9ff")
                ,Color.parseColor("#a29bfe")
                ,Color.parseColor("#e17055")
                ,Color.parseColor("#e84393")
                ,Color.parseColor("#636e72"))


        ca = ColorAnimator.Builder(colors)
                .setAnimationDuration(5000)
                .setDelayDuration(10000)
                .setRepeatMode(true)
                .addColorUpdateListener(object : ColorAnimator.ColorAnimatorUpdateListener{
                    override fun onColorUpdate(color: Int) {
                        findViewById<ConstraintLayout>(R.id.container).setBackgroundColor(color)
                    }

                })
                .build()
        ca?.start()
    }

    override fun onPause() {
        super.onPause()
        ca?.pause()
    }

    override fun onResume() {
        super.onResume()
        ca?.resume()
    }
}
