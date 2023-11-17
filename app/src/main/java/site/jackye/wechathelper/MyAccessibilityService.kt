package site.jackye.wechathelper

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import site.jackye.wechat_helper_accessibility.*
import site.jackye.wechathelper.*

/**
 * Author: JackyYe
 * Date: 2023-11-15
 * Desc:
 */
class MyAccessibilityService : FastAccessibilityService() {
    companion object {
        const val TAG = "WhFastAccessibility"
    }

    override val enableListenApp = true

    override fun analyzeCallBack(wrapper: EventWrapper?, result: AnalyzeSourceResult) {
        Log.w(TAG, "analyzeCallBack:$wrapper${Thread.currentThread()}")
        //result.findNodeById("com.tencent.mm:id/pjb").click()
        //||wrapper?.className=="com.tencent.mm.ui.LauncherUI"

        when(wrapper?.eventType){
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED->MyEventToDO().actionTypeWindowStateChanged(wrapper,result)
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED->MyEventToDO().actionTypeWindowContentChanged(wrapper,result)

            AccessibilityEvent.TYPE_VIEW_SCROLLED->MyEventToDO().actionTypeViewScrolled(wrapper,result)
        }
//        if(wrapper?.className=="com.tencent.mm.plugin.webwx.ui.ExtDeviceWXLoginUI"){
//
//            Thread.sleep(200)
//            Log.d(TAG,"click")
//            //result.findNodeById("com.tencent.mm:id/pjb").click()
//            result.findNodeByText("登录",textAllMatch = true).click()
//        }

        //result.findAllTextNode(true).nodes.forEach { Log.d(TAG, "$wrapper | $it ") }
    }



}