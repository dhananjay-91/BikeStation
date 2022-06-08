package com.dhananjay.digikraft.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhananjay.digikraft.data.model.BikeStation
import com.dhananjay.digikraft.data.repository.StationRepo
import com.dhananjay.digikraft.ui.base.BaseViewModel
import com.dhananjay.digikraft.ui.main.adapter.StationAdapter
import com.dhananjay.digikraft.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val stationRepo: StationRepo) : BaseViewModel(),
    StationAdapter.RecyclerItemListener {


    var receivedStationList: MutableLiveData<ArrayList<BikeStation>> = MutableLiveData()
    var stationSelection: MutableLiveData<Event<BikeStation>> = MutableLiveData()
    var showDialog:MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))


    fun fetchStationData() {

        // fetch data from
        showDialog.value = Event(true)
        viewModelScope.launch(Dispatchers.IO) {
            val stations = stationRepo.getStationData()
            receivedStationList.postValue(stations)
        }
        showDialog.value = Event(false)

    }

    override fun onItemSelected(bikeStation: BikeStation) {
        stationSelection.value = Event(bikeStation)
    }


}