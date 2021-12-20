package com.example.base_feature.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import org.koin.core.KoinComponent
import com.example.base_feature.dialog.*
import com.example.base_feature.dialog.LoadingDialog
import com.example.base_feature.utils.extensions.hideKeyboard
import com.example.base_feature.utils.watchers.KeyboardEventListener


abstract class BaseFragment<Binding : ViewBinding> : Fragment(), ViewStateListener, KoinComponent {

    private var _binding: Binding? = null
        get() {
            if (field == null)
                field = onCreateViewBinding(layoutInflater)
            return field
        }

    private var loadingDialogFragment: LoadingDialog? = null
        get() {
            if (field == null)
                field = LoadingDialog()

            return field
        }

    protected val binding: Binding get() = _binding!!

    private var keyboardEventListener: KeyboardEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        addObservers(viewLifecycleOwner)
    }

    override fun onResume() {
        super.onResume()
        if (keyboardEventListener == null) {
            keyboardEventListener = KeyboardEventListener(activity as AppCompatActivity) { isOpen ->
                onKeyboardChange(isOpen)
            }
        }
    }
    abstract fun onCreateViewBinding(inflater: LayoutInflater): Binding

    override fun onStop() {
        super.onStop()
        keyboardEventListener?.onLifecyclePause()
        hideKeyboard()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> try {
                findNavController().popBackStack()
            } catch (e: Exception) {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onStateError(error: Throwable) {
        hideLoading()
    }

    override fun onStateLoading() {
        childFragmentManager.let {
            it.executePendingTransactions()
            if (loadingDialogFragment?.isAdded?.not() == true) {
                loadingDialogFragment?.show(this)
            }
        }
    }

    override fun hideLoading() {
        loadingDialogFragment?.dismissAllowingStateLoss()
        loadingDialogFragment = null
    }


    open fun showGenericDialog(
        @DrawableRes drawable: Int? = null,
        title: String = "",
        description: CharSequence? = null,
        buttonText: String? = null,
        action: (() -> Unit)? = null,
        closeButtonIsVisible: Boolean = true
    ) {
        GenericBottomSheet.newInstance(
            drawable = drawable,
            title = title,
            description = description ?: "",
            buttonText = buttonText ?: getString(UikitR.string.understood),
            onPressed = {
                action?.invoke()
            },
            closeButtonIsVisible = closeButtonIsVisible
        ).showBottomSheet(this@BaseFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        keyboardEventListener = null
        loadingDialogFragment = null
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun setupView() = Unit

    open fun onKeyboardChange(isOpen: Boolean) = Unit

}

