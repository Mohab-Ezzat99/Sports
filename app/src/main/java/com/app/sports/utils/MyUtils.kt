package com.app.sports.utils

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.app.sports.R
import com.app.sports.databinding.DialogConfirmBinding
import com.squareup.picasso.Picasso

object MyUtils {

    fun Activity.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Fragment.showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun Activity.dialogMessage(
        message: String,
        title: String = "",
        hasCancel: Boolean = false,
        action: () -> Unit = {}
    ) {
        val view = DialogConfirmBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this, R.style.DialogStyle)
            .setView(view.root)
            .setCancelable(false)
            .show()

        view.apply {
            txtTitle.isVisible = title.isNotEmpty()
            btnCancel.isVisible = hasCancel

            txtTitle.text = title
            txtMassage.text = message

            btnCancel.setOnClickListener {
                if (dialog?.isShowing == true) dialog.dismiss()
            }
            btnOk.setOnClickListener {
                if (dialog?.isShowing == true) dialog.dismiss()
                action()
            }
        }
    }

    fun Fragment.dialogMessage(
        message: String,
        title: String = "",
        hasCancel: Boolean = false,
        action: () -> Unit = {}
    ) {
        val view = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = AlertDialog.Builder(requireContext(), R.style.DialogStyle)
            .setView(view.root)
            .setCancelable(false)
            .show()

        view.apply {
            txtTitle.isVisible = title.isNotEmpty()
            btnCancel.isVisible = hasCancel

            txtTitle.text = title
            txtMassage.text = message

            btnCancel.setOnClickListener {
                if (dialog?.isShowing == true) dialog.dismiss()
            }
            btnOk.setOnClickListener {
                if (dialog?.isShowing == true) dialog.dismiss()
                action()
            }
        }
    }

    fun Activity.hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun ImageView.loadImg(url: String?) {
        if (url.isNullOrEmpty()) setImageResource(R.drawable.ic_launcher_foreground)
        else Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(this)
    }
}
