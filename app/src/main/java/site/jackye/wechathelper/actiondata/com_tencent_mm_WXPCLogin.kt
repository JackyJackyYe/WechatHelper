package site.jackye.wechathelper.actiondata

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import site.jackye.wechat_helper_accessibility.*
import site.jackye.wechathelper.*

class ComTencentMmWXPCLogin {
    fun startPoint(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        if(MyApp.WXLoginState) {
            Thread.sleep(200)
            Log.d(MyAccessibilityService.TAG, "PC_click")
            //result.findNodeById("com.tencent.mm:id/pjb").click()
            if(!MyApp.debugState){
                result.findNodeByText("登录", textAllMatch = true).click()
            }
            else {
                Log.d(MyAccessibilityService.TAG, "PC_click:FINISHED(debug)")
            }
        }
    }
}