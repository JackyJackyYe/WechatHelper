package site.jackye.wechathelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import site.jackye.wechat_helper_accessibility.FastAccessibilityService
import site.jackye.wechat_helper_accessibility.isAccessibilityEnable
import site.jackye.wechat_helper_accessibility.requireAccessibility
/**
 * Author: JackyYe
 * Date: 2023-11-15
 * Desc:
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mServiceStatusIv: ImageView
    private lateinit var mServiceStatusTv: TextView

    private lateinit var mDebugMode: Switch
    private lateinit var mWXPCLoginSW: Switch
    private lateinit var mADJumpSW: Switch

    private val mClickListener = View.OnClickListener {
        FastAccessibilityService
        when (it.id) {
            R.id.iv_service_status -> {

                if (isAccessibilityEnable) {
                    MyState().writeStateData(this,"serviceState",!MyApp.serviceState)
                    flushView()
                    //shortToast(getStringRes(R.string.service_is_enable_tips))
                    Log.w(MyAccessibilityService.TAG, "${MyApp.serviceState}|$isAccessibilityEnable")
                }
                else {
                    MyState().writeStateData(this,"serviceState",true)
                    Log.w(MyAccessibilityService.TAG, "${MyApp.serviceState}|$isAccessibilityEnable")
                    requireAccessibility()
                    flushView()
                }
            }
            R.id.Debug_SW ->{
                MyState().writeStateData(this,"debugState",!MyApp.debugState)
                flushView()
            }
            R.id.WXPCLogin_SW ->{
                MyState().writeStateData(this,"WXLoginState",!MyApp.WXLoginState)
                flushView()
            }
            R.id.ADJump_SW ->{
                MyState().writeStateData(this,"ADJumpState",!MyApp.ADJumpState)
                flushView()
            }
            //R.id.bt_open_target_app -> startApp("com.tencent.mm", "com.tencent.mm.ui.LauncherUI", "未安装微信")
        }
    }
    //ui.LauncherUI   com.android.calendar.LaunchActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mServiceStatusIv = findViewById(R.id.iv_service_status)
        mServiceStatusTv = findViewById(R.id.tv_service_status)
        mDebugMode = findViewById(R.id.Debug_SW)
        mWXPCLoginSW = findViewById(R.id.WXPCLogin_SW)
        mADJumpSW = findViewById(R.id.ADJump_SW)

        mServiceStatusIv.setOnClickListener(mClickListener)
        mDebugMode.setOnClickListener(mClickListener)
        mWXPCLoginSW.setOnClickListener(mClickListener)
        mADJumpSW.setOnClickListener(mClickListener)

        flushView()
    }
    private fun flushView() {
        Log.d(MyAccessibilityService.TAG, "flush")

        when(MyApp.serviceState&&isAccessibilityEnable){
            true -> {
                mServiceStatusIv.setImageDrawable(getDrawableRes(R.drawable.ic_service_enable))
                mServiceStatusTv.text = getStringRes(R.string.service_status_enable)

                mWXPCLoginSW.isEnabled=true
                mADJumpSW.isEnabled=true
            }
            else -> {
                mServiceStatusIv.setImageDrawable(getDrawableRes(R.drawable.ic_service_disable))
                mServiceStatusTv.text = getStringRes(R.string.service_status_disable)

                mWXPCLoginSW.isEnabled=false
                mADJumpSW.isEnabled=false

            }
        }
        mDebugMode.isChecked = MyApp.debugState
        mWXPCLoginSW.isChecked = MyApp.WXLoginState
        mADJumpSW.isChecked = MyApp.ADJumpState
        Log.d(MyAccessibilityService.TAG, "$isAccessibilityEnable|${MyApp.serviceState}|${MyApp.debugState}|${MyApp.WXLoginState}|${MyApp.ADJumpState}")
    }

    override fun onResume() {
        super.onResume()
        Log.w(MyAccessibilityService.TAG, "flush")
        flushView()
    }
}