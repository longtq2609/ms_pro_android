package com.example.ms_pro.base.viewcomponent

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ms_pro.R
import com.example.ms_pro.base.event.EventBusUtil
import com.example.ms_pro.base.event.ToastMessageEvent
import com.example.ms_pro.base.util.CLog
import com.example.ms_pro.base.util.ToastUtil
import com.example.ms_pro.base.widget.dialog.ProgressLoading
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.example.ms_pro.base.util.AppHelper

/**
 * Created by longtq on 21/06/21.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBusUtil.register(this)
        CLog.d("Activity name: " + this.javaClass.simpleName)
        setStatusBarColor(Color.parseColor("#ffffff"))
        progressLoading = ProgressLoading(this)
    }

    override fun onDestroy() {
        AppHelper.hideKeyboard(this)
        EventBusUtil.unregister(this)
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        try {
            overridePendingTransition(
                R.anim.activity_slide_from_left,
                R.anim.activity_slide_to_right
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }

    fun openNewActivity(c: Class<*>?) {
        try {
            val intent = Intent(this, c)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openNewActivityAndClearStack(c: Class<*>?) {
        try {
            val intent = Intent(this, c)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openNewActivity(bundle: Bundle?, c: Class<*>?) {
        try {
            val intent = Intent(this, c)
            intent.putExtra(BUNDLE_KEY, bundle)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openNewActivity(intent: Intent, bundle: Bundle?) {
        try {
            intent.putExtra(BUNDLE_KEY, bundle)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openNewActivityForResult(bundle: Bundle?, c: Class<*>?) {
        try {
            val intent = Intent(this, c)
            intent.putExtra(BUNDLE_KEY, bundle)
            startActivityForResult(intent, BASE_RESULT_CODE)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openNewActivityForResult(bundle: Bundle?, c: Class<*>?, requestcode: Int) {
        try {
            val intent = Intent(this, c)
            intent.putExtra(BUNDLE_KEY, bundle)
            startActivityForResult(intent, requestcode)
            overridePendingTransition(R.anim.activity_come_in, R.anim.activity_come_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val intentBundle: Bundle?
        get() = try {
            intent.getBundleExtra(BUNDLE_KEY)
        } catch (e: Exception) {
            null
        }

    fun setBackButtonToolbar() {
        val mActionBarToolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mActionBarToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.mipmap.ic_back)
    }

    fun setTitleToolbar(title: String?) {
        val mActionBarToolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mActionBarToolbar)
        mActionBarToolbar.title = title
        mActionBarToolbar.setTitleTextAppearance(this, R.style.toolbar_title)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: ToastMessageEvent) {
        ToastUtil.toastNormal(this, event.message)
    }

    companion object {
        const val BASE_RESULT_CODE = 8080
        const val RQ_LIFE_CARD = 8090
        const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}