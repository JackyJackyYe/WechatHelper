package site.jackye.wechathelper

import android.app.Application
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import site.jackye.wechat_helper_accessibility.FastAccessibilityService
import site.jackye.wechat_helper_accessibility.isAccessibilityEnable
import site.jackye.wechathelper.MyState

/**
 * Author: JackyYe
 * Date: 2023-11-15
 * Desc: 自定义Application类
 */
class MyApp : Application() {
    companion object {
        lateinit var instance: Application
        var serviceState=false
        var debugState=false
        var WXLoginState=false
        var ADJumpState=false

    }

    override fun onCreate() {
        super.onCreate()
        MyState().getData(this)
        if (!isAccessibilityEnable) {
            MyState().writeStateData(this,"serviceState",false)
            serviceState=false
        }
        Log.d(MyAccessibilityService.TAG, "$isAccessibilityEnable|$serviceState|$debugState|$WXLoginState|$ADJumpState")
        instance = this
        FastAccessibilityService.init(
            instance, MyAccessibilityService::class.java, arrayListOf(
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED,
                //AccessibilityEvent.TYPE_VIEW_CLICKED,
                AccessibilityEvent.TYPE_VIEW_SCROLLED,

            ), serviceState
        )
    }
}