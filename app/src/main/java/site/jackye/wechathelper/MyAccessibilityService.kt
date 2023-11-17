package site.jackye.wechathelper

import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import site.jackye.wechat_helper_accessibility.*

/**
 * Author: JackyYe
 * Date: 2023-11-15
 * Desc:
 */
class MyAccessibilityService : FastAccessibilityService() {
    companion object {
        private const val TAG = "WhFastAccessibility"
    }

    override val enableListenApp = true

    override fun analyzeCallBack(wrapper: EventWrapper?, result: AnalyzeSourceResult) {
        Log.d(TAG, "analyzeCallBack:$wrapper")
        //result.findNodeById("com.tencent.mm:id/pjb").click()
        //||wrapper?.className=="com.tencent.mm.ui.LauncherUI"
        if(wrapper?.className=="com.tencent.mm.plugin.webwx.ui.ExtDeviceWXLoginUI"){

            Thread.sleep(200)
            Log.d(TAG,"click")
            //result.findNodeById("com.tencent.mm:id/pjb").click()
            result.findNodeByText("登录",textAllMatch = true).click()
        }

        result.findAllTextNode(true).nodes.forEach { Log.e(TAG, "$wrapper | $it ") }
    }
}