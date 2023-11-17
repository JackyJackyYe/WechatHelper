package site.jackye.wechathelper

import android.app.Application
import android.view.accessibility.AccessibilityEvent
import site.jackye.wechat_helper_accessibility.FastAccessibilityService

/**
 * Author: JackyYe
 * Date: 2023-11-15
 * Desc: 自定义Application类
 */
class MyApp : Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FastAccessibilityService.init(
            instance, MyAccessibilityService::class.java, arrayListOf(
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED,
                //AccessibilityEvent.TYPE_VIEW_CLICKED,
                AccessibilityEvent.TYPE_VIEW_SCROLLED,

            )
        )
    }
}