package com.jaiswal.memorygame.models

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide


open class GridCell(private val url: String,
               private val imageType: ImageType,
               private val engineInteractor: Interactor,
               private var currentState: ObservableField<CurrentState> = ObservableField(CurrentState.HIDDEN)
) {
    private var doCloseCell: ObservableBoolean = ObservableBoolean(false)
    private var view: View? = null

    fun getDoCloseCell():ObservableBoolean{
        return doCloseCell
    }

    fun setDoCloseCell(doClose: Boolean){
        doCloseCell.set(doClose)
    }

    fun getImageType(): ImageType {
        return imageType
    }

    fun getCurrentState(): ObservableField<CurrentState>{
        return currentState
    }

    fun setCurrentState(state: CurrentState){
        this.currentState.set(state)
    }

    fun handleAnim(view: View){
        if(currentState.get() == CurrentState.HIDDEN){
            doCloseCell.set(false)
            val inAnimator = getAnimator(view)
            inAnimator.duration = 300
            inAnimator.repeatCount = 0
            inAnimator.interpolator = AccelerateDecelerateInterpolator()
            inAnimator.start()
            inAnimator.addListener(getInAnimatorListener(view))
        }
    }

    fun getAnimator(view: View) = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 90f)

    fun finalizeCell(gameWon: Boolean){
        //todo handle image change on correct image pair selection
        /*val image = view?.findViewById(com.jaiswal.memorygame.R.id.os_images) as ImageView
        image.setImageResource(com.jaiswal.memorygame.R.drawable.baseline_check_circle_outline_black_48)*/
        currentState.set(CurrentState.DONE)
        if(gameWon) engineInteractor.onGameWon()
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
                engineInteractor.onCellClicked(this@GridCell, view)
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
        }
    }

    fun onViewClicked(view: View) {
        this.view = view
        handleAnim(view)
    }

    companion object {

        @BindingAdapter("closeState")
        @JvmStatic
        fun cellAction(view: ImageView,
                       doClose: Boolean) {
            if(doClose){
                animateCloseCell(view)
            }
        }

        private fun animateCloseCell(view: View){
            val outAnimator = ObjectAnimator.ofFloat(view, "rotationY",180f, 90f)
            outAnimator.duration = 300
            outAnimator.repeatCount = 0
            outAnimator.interpolator = AccelerateDecelerateInterpolator()
            outAnimator.start()
            outAnimator.addListener(getOutAnimatorListener(view))
        }

        private fun getOutAnimatorListener(view: View): Animator.AnimatorListener{
            val outAnimator = ObjectAnimator.ofFloat(view, "rotationY",90f, 0f)
            return object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                }
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    //val image = view.findViewById(com.jaiswal.memorygame.R.id.os_images) as ImageView
                    /*Glide.with(view.context)
                        .load(url)
                        .placeholder(com.jaiswal.memorygame.R.mipmap.ic_launcher_round)
                        //.error(R.drawable.errorImage)
                        .into(view as ImageView)*/
                    (view as ImageView).setImageResource(com.jaiswal.memorygame.R.mipmap.ic_launcher_round)
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
}