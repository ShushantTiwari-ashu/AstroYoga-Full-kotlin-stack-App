package com.shushant.resource

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat

class ResourceProvider constructor(private val context: Context) {

    companion object {
        const val RES_TYPE_DRAWABLE = "drawable"
    }

    fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    fun getString(@StringRes id: Int, vararg args: Any?): String {
        return context.getString(id, *args)
    }

    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg args: Any?): String {
        return context.resources.getQuantityString(id, quantity, *args)
    }

    fun getDimens(@DimenRes dimenRes: Int): Float {
        return context.resources.getDimension(dimenRes)
    }

    fun getColor(@ColorRes colorId: Int): Int {
        return requireNotNull(ContextCompat.getColor(context, colorId))
    }

    fun getDrawable(@DrawableRes drawableId: Int): Drawable {
        return requireNotNull(ContextCompat.getDrawable(context, drawableId))
    }

    @SuppressLint("DiscouragedApi")
    fun getIcon(drawableName: String, defType: String):  Int {
        return context.resources.getIdentifier(
            drawableName.lowercase(),
            defType,
            context.packageName
        )
    }

}
