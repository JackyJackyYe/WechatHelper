package site.jackye.wechathelper

import android.content.Context
import site.jackye.wechat_helper_accessibility.FastAccessibilityService
import site.jackye.wechat_helper_accessibility.*
/**
 * Author: JackyYe
 * Date: 2023-11-18
 * Desc:
 */
class MyState {
    fun readStateData(context: Context,key:String): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("StateInfo", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

    fun writeStateData(context: Context, key: String, state: Boolean): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("StateInfo", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, state)
        val result=editor.commit()
        getData(context)
        return result
    }
    fun getData(context: Context){
        MyApp.serviceState=readStateData(context,"serviceState")
        FastAccessibilityService.serviceState=MyApp.serviceState
        MyApp.debugState=readStateData(context,"debugState")
        MyApp.WXLoginState=readStateData(context,"WXLoginState")
        MyApp.ADJumpState=readStateData(context,"ADJumpState")
    }

}