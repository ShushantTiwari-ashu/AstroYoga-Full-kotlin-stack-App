package com.shushant.common.compose.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ActivityProvider(application: Application) {

    var activeActivity: ComponentActivity? = null
    var scope: CoroutineScope? = null
    val activityAliveListenerList = arrayListOf<ActivityAliveListener>()
    private val _currentActivity = MutableStateFlow<ComponentActivity?>(null)
    val currentActivity: Flow<ComponentActivity?>
        get() = _currentActivity

    init {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {
                    _currentActivity.value = null
                    activeActivity = null
                    scope = null
                }

                override fun onActivityResumed(activity: Activity) {
                    activeActivity = activity as? ComponentActivity
                    _currentActivity.value = activeActivity
                    scope = activeActivity?.lifecycleScope
                    activityAliveListenerList.forEach {
                        it.onAlive(activeActivity)
                    }
                    activityAliveListenerList.clear()
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }
            }
        )
    }

    fun addListener(activityAliveListener: ActivityAliveListener) {
        activityAliveListenerList.add(activityAliveListener)
    }

    fun safeUseScope(block: CoroutineScope.() -> Unit) {
        scope?.let {
            block.invoke(it)
        } ?: kotlin.run {
            CoroutineScope(SupervisorJob() + Dispatchers.Default).launch {
                currentActivity.collect { curActivity ->
                    curActivity?.lifecycleScope?.let(block)
                }
            }
        }
    }
}

interface ActivityAliveListener {
    fun onAlive(activeActivity: ComponentActivity?)
}
