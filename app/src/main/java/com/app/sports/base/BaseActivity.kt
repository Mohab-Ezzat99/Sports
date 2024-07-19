package com.app.sports.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.app.sports.R
import com.app.sports.data.remote.networkHandling.NetworkResult
import com.app.sports.data.remote.networkHandling.NetworkStatus
import com.app.sports.utils.MyUtils.dialogMessage

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> :
    AppCompatActivity(),
    NetworkStatus {

    protected lateinit var dataBinding: T

    protected val viewModel: V by lazy { ViewModelProvider(this).get(viewModelClass()) }

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        baseObserver()
        setUI(savedInstanceState)
        clicks()
        callApis()
        observer()
    }

    override fun onResume() {
        super.onResume()
        NetworkResult.observeNetworkStatus(this)
    }

    private fun init() {
        dataBinding = DataBindingUtil.setContentView(this, resourceId())
        dataBinding.lifecycleOwner = this
    }

    private fun baseObserver() {
        viewModel.loading.observe(this) {
            toggleLoadingDialog(it)
        }

        viewModel.showMassage.observe(this) {
            dialogMessage(it)
        }
    }

    private fun toggleLoadingDialog(show: Boolean) {
        if (dialog == null) {
            dialog = AlertDialog.Builder(this)
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()
            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        if (show) dialog?.show()
        else dialog?.dismiss()
    }

    abstract fun resourceId(): Int
    abstract fun viewModelClass(): Class<V>
    abstract fun setUI(savedInstanceState: Bundle?)
    abstract fun clicks()
    abstract fun callApis()
    abstract fun observer()

    override fun onConnectionFail(exception: String?) {
        exception?.let { dialogMessage(getString(R.string.connectionIsFailed)) }
    }

    override fun onServerSideError(exception: String?) {
        exception?.let { dialogMessage(it) }
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down)
    }
}
