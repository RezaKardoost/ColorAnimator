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
    private var onDelayState = false

    private val handler = Handler()

    val runnable =  object : Runnable {
        override fun run() {

            val destinationColorIndex:Int

            onDelayState = false


            destinationColorIndex = if (currentColorIndex+1 == colors.size){
                if(!repeatMode){
                    return
                }else{
                    0
                }
            }else{
                currentColorIndex+1
            }

            valueAnimator.setIntValues(colors[currentColorIndex],colors[destinationColorIndex])
            valueAnimator.setEvaluator(ArgbEvaluator())
            valueAnimator.duration = animationDuration
            valueAnimator.addUpdateListener {
                colorAnimatorUpdateListener?.onColorUpdate(it.animatedValue as Int)

                if (!onDelayState && colors[destinationColorIndex] == it.animatedValue as Int){
                    onDelayState = true
                    valueAnimator.removeAllUpdateListeners()

                    currentColorIndex = if (currentColorIndex == colors.size-1){
                        0
                    }else{
                        currentColorIndex+1
                    }
                    handler.postDelayed(this,delayDuration)
                }
            }
            valueAnimator.start()
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

    interface ColorAnimatorUpdateListener{
        fun onColorUpdate(color:Int)
    }
}