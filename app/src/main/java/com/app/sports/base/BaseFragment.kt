package com.app.sports.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.sports.R
import com.app.sports.data.remote.networkHandling.NetworkResult
import com.app.sports.data.remote.networkHandling.NetworkStatus
import com.app.sports.utils.MyUtils.dialogMessage

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), NetworkStatus {

    private var appContext: Context? = null

    protected val viewModel: V by lazy { ViewModelProvider(this).get(viewModelClass()) }

    protected lateinit var dataBinding: T

    private var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResource(), container, false)

        appContext = context
        dataBinding.lifecycleOwner = this

        baseObserver()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI(savedInstanceState)
        clicks()
        callApis()
        observer()
    }

    override fun onResume() {
        super.onResume()
        appContext = context
        NetworkResult.observeNetworkStatus(this)
    }

    private fun baseObserver() {
        viewModel.loading.observe(viewLifecycleOwner) {
            toggleLoadingDialog(it)
        }

        viewModel.showMassage.observe(viewLifecycleOwner) {
            dialogMessage(it)
        }
    }

    private fun toggleLoadingDialog(show: Boolean) {
        if (dialog == null) {
            dialog = AlertDialog.Builder(requireContext())
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()

            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        if (show) dialog?.show()
        else dialog?.dismiss()
    }

    abstract fun layoutResource(): Int
    abstract fun viewModelClass(): Class<V>
    abstract fun setUI(savedInstanceState: Bundle?)
    abstract fun clicks()
    abstract fun callApis()
    abstract fun observer()

    override fun onServerSideError(exception: String?) {
        (requireActivity() as BaseActivity<*, *>).onServerSideError(exception)
    }

    override fun onConnectionFail(exception: String?) {
        (requireActivity() as BaseActivity<*, *>).onConnectionFail(exception)
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }
}
