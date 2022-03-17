package com.example.assignment_5_6.custom

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_5_6.R
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.floor
import kotlin.math.sin

class CustomArcLayoutManager(private val context: Context, private val scrollFlag: Boolean) : RecyclerView.LayoutManager() {

    private var horizontalOffset = 0
    private val viewWidth = context.resources.getDimensionPixelSize(R.dimen.item_width).toFloat()

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams = RecyclerView.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    override fun canScrollHorizontally(): Boolean = scrollFlag

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        horizontalOffset += dx
        fill(recycler)
        return dx
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        fill(recycler)
    }

    private fun fill(recycler: RecyclerView.Recycler?) {
        detachAndScrapAttachedViews(recycler ?: return)

        val screenWidth = context.resources.displayMetrics.widthPixels

        if(scrollFlag) {
            val firstVisiblePosition = floor(horizontalOffset.toDouble() / viewWidth.toDouble()).toInt()
            val lastVisiblePosition = (horizontalOffset + screenWidth) / viewWidth

            for (itemIndex in firstVisiblePosition..lastVisiblePosition.toInt()) {
                var recyclerIndex = itemIndex % itemCount
                if (recyclerIndex < 0) {
                    recyclerIndex += itemCount
                }
                val view = recycler.getViewForPosition(recyclerIndex)
                addView(view)

                layoutChildView(itemIndex, view)
            }
        } else {
            for (itemIndex in 0 until itemCount) {
                val view = recycler.getViewForPosition(itemIndex)
                addView(view)

                layoutChildView(itemIndex, view)
            }
        }

        recycler.scrapList.toList().forEach {
            recycler.recycleView(it.itemView)
        }
    }

    private fun layoutChildView(itemIndex: Int, view: View) {
        val left = (width - itemCount * viewWidth) / 2 + ((itemIndex) * viewWidth) - horizontalOffset
        val right = left + viewWidth
        val top = computeYComponent((left + right) / 2, viewWidth)
        val bottom = top.first + viewWidth

        val alpha = top.second
        view.rotation = (alpha * (180 / PI)).toFloat() - 90f

        measureChildWithMargins(view, viewWidth.toInt(), viewWidth.toInt())
        layoutDecoratedWithMargins(view, left.toInt(), top.first, right.toInt(), bottom.toInt())
    }

    private fun computeYComponent(viewCenterX: Float, h: Float): Pair<Int, Double> {
        val screenWidth = context.resources.displayMetrics.widthPixels
        val s = screenWidth.toDouble()/2
        val radius = ((h * h + s * s) / (h * 2)) * 1.7

        val xScreenFraction = viewCenterX.toDouble() / screenWidth.toDouble()
        val beta = acos(s / radius)

        val alpha = beta + (xScreenFraction * (Math.PI - (2 * beta)))
        val yComponent = radius - (radius * sin(alpha))

        return Pair(yComponent.toInt(), alpha)
    }
}