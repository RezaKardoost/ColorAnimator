package io.github.rezakardoost.library

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Handler

class ColorAnimator private constructor(val colors:Array<Int>) {

    private var animationDuration:Long = 5000 //milli Second
    private var delayDuration:Long = 15000 //milli Second
    private var repeatMode = false
    private var currentColorIndex=0
    private var colorAnimatorUpdateListener:ColorAnimatorUpdateListener? = null
    private val valueAnimator = ValueAnimator()
    private var onPauseState = false

    private val handler = Handler()

    val runnable =  object : Runnable {
        override fun run() {

            if (!repeatMode && currentColorIndex+1 == colors.size){
                return
            }

            valueAnimator.setIntValues(colors[currentColorIndex],colors[currentColorIndex+1])
            valueAnimator.setEvaluator(ArgbEvaluator())
            valueAnimator.duration = animationDuration
            valueAnimator.addUpdateListener {
                colorAnimatorUpdateListener?.onColorUpdate(it.animatedValue as Int)
            }
            valueAnimator.start()

            currentColorIndex++

            handler.postDelayed(this,delayDuration)
        }

    }

    fun start(){
        colorAnimatorUpdateListener?.onColorUpdate(colors[0])
        handler.postDelayed(runnable,delayDuration)
    }

    fun pause(){
        handler.removeCallbacks(runnable)
        valueAnimator.cancel()
        valueAnimator.removeAllUpdateListeners()
        onPauseState = true
    }

    fun resume(){
        if(onPauseState){
            colorAnimatorUpdateListener?.onColorUpdate(colors[currentColorIndex])
            handler.postDelayed(runnable,delayDuration)
        }
    }

    class Builder(colors:Array<Int>){
        var colorAnimator = ColorAnimator(colors)

        fun setAnimationDuration(animationDuration:Long):Builder{
            colorAnimator.animationDuration = animationDuration
            return this
        }

        fun setRepeatMode(repeatMode:Boolean):Builder{
            colorAnimator.repeatMode = repeatMode
            return this
        }

        fun setDelayDuration(delayDuration:Long):Builder{
            colorAnimator.delayDuration = delayDuration
            return this
        }

        fun addColorUpdateListener(colorAnimatorUpdateListener:ColorAnimatorUpdateListener):Builder{
            colorAnimator.colorAnimatorUpdateListener = colorAnimatorUpdateListener
            return this
        }

        fun build():ColorAnimator{

            return colorAnimator

        }
    }

    public interface ColorAnimatorUpdateListener{
        fun onColorUpdate(color:Int)
    }
}