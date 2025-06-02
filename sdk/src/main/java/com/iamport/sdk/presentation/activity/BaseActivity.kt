package com.iamport.sdk.presentation.activity

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.iamport.sdk.domain.utils.BaseCoroutineScope
import com.iamport.sdk.domain.utils.UICoroutineScope
import com.iamport.sdk.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<R : BaseViewModel>
@JvmOverloads constructor(scope: BaseCoroutineScope = UICoroutineScope()) :
    AppCompatActivity(), BaseMain, BaseCoroutineScope by scope {

    abstract val viewModel: R
    abstract val layoutResourceId: Int

    abstract fun initStart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )

        setContentView(layoutResourceId)
        initStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseCoroutine()
    }

}