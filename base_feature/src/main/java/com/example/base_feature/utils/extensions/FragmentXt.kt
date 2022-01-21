package com.example.base_feature.utils.extensions

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.R

fun Fragment.hideActionBar() = (activity as? AppCompatActivity)?.supportActionBar?.hide()

fun Fragment.showActionBar() = (activity as? AppCompatActivity)?.supportActionBar?.show()

fun Fragment.showSoftKeyboard(view: View, forceOpen: Boolean = false) {
    val inputMethodManager =
        requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    view.requestFocus()

    if (forceOpen) {
        inputMethodManager?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    } else {
        inputMethodManager?.showSoftInput(view, 0)
    }
}

fun Fragment.hideKeyboard(forceClose: Boolean = false) {
    val inputManager =
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
    val currentFocusedView = requireActivity().currentFocus ?: return
    if (forceClose) {
        inputManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    } else {
        inputManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Fragment.toast(message: String): Toast = Toast.makeText(context, message, Toast.LENGTH_LONG)

fun Fragment.setNavigationIcon(id: Int?) {
    (activity as AppCompatActivity?)?.supportActionBar?.apply {
        id?.let { setHomeAsUpIndicator(it) }
        setDisplayHomeAsUpEnabled(id != null)
    }
    showActionBar()
}

fun Fragment.getFont(fontId: Int) = ResourcesCompat.getFont(requireContext(), fontId)

fun Fragment.openWhatsapp(number: String, message: String = "") {
    val packageName = "com.whatsapp"
    if (!isPackageInstalled(packageName)) {
        openPackageInStore(packageName)
        return
    }

    startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse("smsTo:$number/*")).apply {
            data = Uri.parse("http://api.whatsapp.com/send?phone=$number&text=$message")
        }
    )
}

fun Fragment.isPackageInstalled(packageName: String) = try {
    requireContext().packageManager.getPackageInfo(packageName, 0)
    true
} catch (e: PackageManager.NameNotFoundException) {
    false
}

fun Fragment.openPackageInStore(packageName: String) = try {
    val appStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
    appStoreIntent.setPackage("com.android.vending")
    startActivity(appStoreIntent)
} catch (exception: ActivityNotFoundException) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    } catch (e: Exception) {
        Unit
    }
}

fun Fragment.getColor(@ColorRes colorId: Int) = ContextCompat.getColor(requireContext(), colorId)
fun Fragment.getDrawable(@ColorRes drawableId: Int) =
    ContextCompat.getColor(requireContext(), drawableId)

fun Fragment.getDrawableResource(drawableId: Int) =
    ContextCompat.getDrawable(requireContext(), drawableId).apply {
        this?.setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    }

fun Fragment.openShareIntent(message: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
}

fun Fragment.copyToClipboard(text: String, label: String = "clipboard") {
    val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
}

fun Fragment.openDetailInSettings() = Intent().run {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    addCategory(Intent.CATEGORY_DEFAULT)
    data = Uri.parse("package:" + requireContext().packageName)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(this)
}

fun Fragment.softInputAdjustNothing() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun Fragment.softInputAdjustResize() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.softInputAdjustPan() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}

fun Fragment.addFlags(flags: List<Int>) {
    flags.map { requireActivity().window.addFlags(it) }
}

fun Fragment.clearFlags(flags: List<Int>) {
    flags.map { requireActivity().window.clearFlags(it) }
}

fun Fragment.addOnBackPressedCallback(
    owner: LifecycleOwner,
    onBackPressed: (() -> Unit)?
) {
    (requireActivity() as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(
        owner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed?.invoke()
            }
        }
    )
}