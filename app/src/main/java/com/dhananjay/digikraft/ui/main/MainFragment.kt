package com.dhananjay.digikraft.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhananjay.digikraft.R
import com.dhananjay.digikraft.data.remote.api.ApiHelper
import com.dhananjay.digikraft.data.remote.api.RetrofitBuilder
import com.dhananjay.digikraft.data.repository.StationRepo
import com.dhananjay.digikraft.databinding.AdapterItemBinding
import com.dhananjay.digikraft.databinding.MainFragmentBinding
import com.dhananjay.digikraft.ui.base.BaseFragment
import com.dhananjay.digikraft.ui.main.adapter.StationAdapter

class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mBinding: MainFragmentBinding

    private lateinit var mStationAdapter: StationAdapter

    override fun initializeViewModel() {
        val factory = MainViewModelFactory(StationRepo(ApiHelper(RetrofitBuilder.apiService)))
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun setObservers() {

        viewModel.receivedStationList.observe(viewLifecycleOwner, Observer {
            mStationAdapter.setData(it)
        })
        viewModel.stationSelection.observe(viewLifecycleOwner, Observer { eventIt ->
            eventIt?.getContentIfNotHandled()?.let {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        })
        viewModel.showDialog.observe(viewLifecycleOwner, Observer { it ->
            it?.getContentIfNotHandled()
                ?.let { if (it) showProgressBar() else dismissProgressBar() }
        })

    }

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MainFragmentBinding.inflate(inflater)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mStationAdapter = StationAdapter(viewModel)

        val manager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.rvStations.layoutManager = manager
        mBinding.rvStations.adapter = mStationAdapter

        viewModel.fetchStationData()

    }
}