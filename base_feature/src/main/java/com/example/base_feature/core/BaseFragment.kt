package com.example.base_feature.core

import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding : ViewBinding> : Fragment(), ViewStateListener, KoinComponent {

}