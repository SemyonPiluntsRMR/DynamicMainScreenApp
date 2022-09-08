package com.example.myapplication.ui

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapters.MainScreenGridAdapter
import com.example.myapplication.ui.adapters.MainScreenHorizontalScrollAdapter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater, null, false) }
    private val adapterMainGrid = MainScreenGridAdapter()
    private val adapterMainScroll = MainScreenHorizontalScrollAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getMainScreenConfig(this)
        initObserverse()
        initGrid()
    }

    private fun initObserverse() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.girdParams.collect {
                        adapterMainGrid.setData(
                            it.gridCategories,
                            coloumnWidth = it.couloumnWidth
                        )
                    }
                }
                launch {
                    viewModel.horizontalScrollParams.collect {
                        adapterMainScroll.setData(
                            it.scrollCategories,
                            coloumnWidth = it.couloumnWidth
                        )
                    }
                }
            }
        }
    }

    private fun initGrid() {

        val flexboxLayoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        binding.activityMainGridRv.apply {
            isNestedScrollingEnabled = true
            layoutManager = flexboxLayoutManager
            adapter = adapterMainGrid
        }

        binding.activityMainHorizontalRv.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = true
            adapter = adapterMainScroll
        }
    }
}
