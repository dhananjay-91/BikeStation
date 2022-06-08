package com.dhananjay.digikraft.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dhananjay.digikraft.R


abstract class BaseFragment : Fragment() {


    private lateinit var mProgressDialog: ProgressDialog

    /**
     * Initialize view Model, it will be called as first method in onViewCreated method
     */
    protected abstract fun initializeViewModel()

    /**
     * set Observers to observers for LiveData, will be called after initializing View Model
     */
    protected abstract fun setObservers()

    /**
     * Return the view from binding
     */
    protected abstract fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setView(inflater,container,savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        setObservers()
    }


    /**
     * Show progress Dialog while calling web service
     */
    fun showProgressBar() {

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setMessage(getString(R.string.please_wait))
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
    }

    /**
     * Dismiss progress bar
     */
    fun dismissProgressBar() {
        if (::mProgressDialog.isInitialized) {
            mProgressDialog.let { if (mProgressDialog.isShowing) mProgressDialog.dismiss() }
        }
    }
}