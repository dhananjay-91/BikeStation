package com.dhananjay.digikraft.ui.detail

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dhananjay.digikraft.R
import com.dhananjay.digikraft.databinding.DetailFragmentBinding
import com.dhananjay.digikraft.ui.base.BaseFragment
import com.google.android.gms.maps.SupportMapFragment


class DetailFragment : BaseFragment() {


    private lateinit var viewModel: DetailViewModel
    private lateinit var mBinding: DetailFragmentBinding

    private val args: DetailFragmentArgs by navArgs()
    override fun initializeViewModel() {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun setObservers() {
        viewModel.stationData.observe(viewLifecycleOwner, Observer {
            mBinding.viewModel
        })
        viewModel.createMarkerView.observe(viewLifecycleOwner, Observer {
            val bitmap = createStoreMarker(it)
            if (bitmap != null) {
                viewModel.addMarkerToGivenStationCoordinate(bitmap)
            }
        })
    }

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DetailFragmentBinding.inflate(inflater)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = DetailFragment@ this
        mBinding.viewModel = viewModel

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(viewModel)

        val bikeStation = args.bikeStation

        viewModel.showStationData(bikeStation)


    }


    private fun createStoreMarker(availableBike : String): Bitmap? {
        val markerLayout: View = layoutInflater.inflate(R.layout.marker_layout, null)
        val markerImage: ImageView = markerLayout.findViewById<View>(R.id.imageView) as ImageView
        val markerRating = markerLayout.findViewById<View>(R.id.txtAvailableBike) as TextView
        markerRating.setText(availableBike)
        markerLayout.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        markerLayout.layout(0, 0, markerLayout.measuredWidth, markerLayout.measuredHeight)
        val bitmap = Bitmap.createBitmap(
            markerLayout.measuredWidth,
            markerLayout.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        markerLayout.draw(canvas)
        return bitmap
    }

}