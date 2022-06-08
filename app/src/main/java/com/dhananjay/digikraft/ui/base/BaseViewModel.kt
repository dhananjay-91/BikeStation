package com.dhananjay.digikraft.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhananjay.digikraft.utils.Event


abstract class BaseViewModel : ViewModel() {

    // to display string message
    protected val mShowToastMsg: MutableLiveData<Event<String>> = MutableLiveData()
    val toastMsg: LiveData<Event<String>> get() = mShowToastMsg

    // to display message from string resource id
    protected val mShowToastMsgResId: MutableLiveData<Event<Int>> = MutableLiveData()
    val toastMsgResId: LiveData<Event<Int>> get() = mShowToastMsgResId
}