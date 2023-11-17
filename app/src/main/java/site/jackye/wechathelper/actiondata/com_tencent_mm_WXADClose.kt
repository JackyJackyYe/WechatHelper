package site.jackye.wechathelper.actiondata

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import site.jackye.wechat_helper_accessibility.*
import site.jackye.wechathelper.*

class ComTencentMmWXADClose {
    companion object {
        var stat = false
        var step1 = false
        var step2 = false
    }
    fun statTrue(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        reset()
        stat=true
        Log.w(MyAccessibilityService.TAG, "actionstatTrue:${stat}${Thread.currentThread()}")
    }
    fun statFalse(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        stat=false
        Log.w(MyAccessibilityService.TAG, "actionstatFalse:${stat}${Thread.currentThread()}")
    }
    fun step1(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        Log.w(MyAccessibilityService.TAG, "actionstatTrue:${stat}|ADstep1:${step1}")
        //reset()
        if(stat) {
            result.findAllIdNode().findNodeByText("广告", textAllMatch = true)
                .click(gestureClick = false)
            step1 = true
        }
        Log.w(MyAccessibilityService.TAG, "actionstatTrue:${stat}|ADstep1:${step1}${Thread.currentThread()}")
    }
    fun step2(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        Log.w(MyAccessibilityService.TAG, "ADstep2:${step2}")
        if(stat&&step1) {
            result.findAllIdNode().findNodeByText("关闭该广告", textAllMatch = true)
                .click(gestureClick = false)
            step2 = true
        }
        Log.w(MyAccessibilityService.TAG, "ADstep2:${step2}${Thread.currentThread()}")
    }
    fun step3(wrapper: EventWrapper?, result: AnalyzeSourceResult){
        Log.w(MyAccessibilityService.TAG, "actionstatTrue:${stat}|ADstep1:${step1}|ADstep2:${step2}")
        if(stat&&step1&&step2) {
            //result.findAllIdNode().findNodeByText("关闭该广告", textAllMatch = true)
            //    .click(gestureClick = false)
            //result.findAllIdNode().findNodeByText("直接关闭", textAllMatch = true)
            //    .click(gestureClick = false)
            reset()
        }
        Log.w(MyAccessibilityService.TAG, "actionstatTrue:${stat}|ADstep1:${step1}|ADstep2:${step2}${Thread.currentThread()}")
    }
    fun reset(){
        step1=false
        step2=false
        Log.w(MyAccessibilityService.TAG, "reset${Thread.currentThread()}")
    }

}