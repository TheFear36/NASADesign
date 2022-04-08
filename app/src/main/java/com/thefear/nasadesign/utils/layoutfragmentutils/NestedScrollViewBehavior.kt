package com.thefear.nasadesign.utils.layoutfragmentutils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class NestedScrollViewBehavior(context: Context, attr: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ) = dependency is AppBarLayout


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        nestedScrollView : View,
        dependency: View
    ): Boolean {
        val bar = dependency as AppBarLayout
        nestedScrollView.y = bar.height.toFloat()+bar.y

        return super.onDependentViewChanged(parent, nestedScrollView, dependency)
    }
}