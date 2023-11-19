package site.jackye.wechathelper

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import site.jackye.wechat_helper_accessibility.*
import site.jackye.wechathelper.actiondata.*
/**
 * Author: JackyYe
 * Date: 2023-11-18
 * Desc:
 */
 class MyEventToDO  {
     fun actionTypeWindowStateChanged(wrapper: EventWrapper?, result: AnalyzeSourceResult){
         Log.d(MyAccessibilityService.TAG, "actionWindowStateChanged:${wrapper?.className}")
         when(wrapper?.className) {
            "com.tencent.mm.plugin.webwx.ui.ExtDeviceWXLoginUI" ->
                ComTencentMmWXPCLogin().startPoint(wrapper, result)
            "com.tencent.mm.plugin.sns.ui.SnsTimeLineUI",
            "com.tencent.mm.plugin.sns.ui.improve.ImproveSnsTimelineUI"->
                ComTencentMmWXADClose().statTrue(wrapper, result)
             "android.widget.FrameLayout" ->
                 ComTencentMmWXADClose().step2(wrapper, result)
            else->{
                ComTencentMmWXADClose().statFalse(wrapper, result)
            }

        }
    }
     fun actionTypeWindowContentChanged(wrapper: EventWrapper?, result: AnalyzeSourceResult){
         when (wrapper?.className) {
             "android.widget.FrameLayout" -> {
                 ComTencentMmWXADClose().step3(wrapper, result)

             }
         }
     }
     fun actionTypeViewScrolled(wrapper: EventWrapper?, result: AnalyzeSourceResult) {
         when(wrapper?.className) {
             "android.widget.ListView" ->{
                 ComTencentMmWXADClose().step1(wrapper, result)

             }


         }
     }

}