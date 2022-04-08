package com.thefear.nasadesign.utils.layoutfragmentutils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import hide
import kotlin.math.abs

class ButtonBehavior (context: Context, attr: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ) = dependency is AppBarLayout


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        button : View,
        dependency: View
    ): Boolean {
        val bar = dependency as AppBarLayout
        val barHeight = bar.height.toFloat()
        val barY = bar.y

        if (abs(barY)>(barHeight*0.6)) {
            button.hide()
        }

        return super.onDependentViewChanged(parent, button, dependency)
    }
}