package com.jaiswal.memorygame

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import android.R.attr.start
import android.R
import android.animation.*
import android.content.Context
import android.view.ContextMenu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class GridCell(private val url: String,
               private val imageType: ImageType,
               private val gameEngine: MemoryEngine) {
    private var currentState: CurrentState = CurrentState.HIDDEN
    private var view: View? = null

    fun getImageType(): ImageType{
        return imageType
    }

    fun setCurrentState(state: CurrentState){
        this.currentState = state
    }

    fun onCellClicked(view: View){
        this.view = view
        handleAnim(view)
        gameEngine.cellSelected(this@GridCell)

    }

    private fun handleAnim(view: View){
        if(currentState == CurrentState.HIDDEN){
            val inAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 90f)
            inAnimator.duration = 300
            inAnimator.repeatCount = 0
            inAnimator.interpolator = AccelerateDecelerateInterpolator()
            inAnimator.start()
            inAnimator.addListener(getInAnimatorListener(view))
        }
    }

    fun closeCell(){
        currentState = CurrentState.HIDDEN
        val outAnimator = ObjectAnimator.ofFloat(view!!, "rotationY",180f, 90f)
        outAnimator.duration = 300
        //animation.repeatCount = ObjectAnimator.INFINITE
        outAnimator.repeatCount = 0
        outAnimator.interpolator = AccelerateDecelerateInterpolator()
        outAnimator.start()
        outAnimator.addListener(getOutAnimatorListener(view!!))
    }

    fun finalizeCell(){
        //todo handle image change on correct image pair selection
        /*val image = view?.findViewById(com.jaiswal.memorygame.R.id.os_images) as ImageView
        image.setImageResource(com.jaiswal.memorygame.R.drawable.baseline_check_circle_outline_black_48)*/
        currentState = CurrentState.DONE
    }

    private fun getInAnimatorListener(view: View): Animator.AnimatorListener{
        val outAnimator = ObjectAnimator.ofFloat(view, "rotationY", 90f, 180f)
        return object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                val image = view.findViewById(com.jaiswal.memorygame.R.id.os_images) as ImageView
                Glide.with(view.context)
                    .load(url)
                    .placeholder(com.jaiswal.memorygame.R.drawable.baseline_alarm_black_48)
                    //.error(R.drawable.errorImage)
                    .into(image)
                outAnimator.duration = 300
                outAnimator.repeatCount = 0
                outAnimator.interpolator = AccelerateDecelerateInterpolator()
                outAnimator.start()

            }
            override fun onAnimationCancel(animation: Animator?) {
            }
        }
    }

    private fun getOutAnimatorListener(view: View): Animator.AnimatorListener{
        val outAnimator = ObjectAnimator.ofFloat(view, "rotationY",90f, 0f)
        return object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                val image = view.findViewById(com.jaiswal.memorygame.R.id.os_images) as ImageView
                Glide.with(view.context)
                    .load(url)
                    .placeholder(com.jaiswal.memorygame.R.mipmap.ic_launcher_round)
                    //.error(R.drawable.errorImage)
                    .into(image)
                outAnimator.duration = 300
                outAnimator.repeatCount = 0
                outAnimator.interpolator = AccelerateDecelerateInterpolator()
                outAnimator.start()
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
        }
    }
}