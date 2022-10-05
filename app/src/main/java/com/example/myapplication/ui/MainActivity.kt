package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
import kotlin.random.Random.Default.nextInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater, null, false) }
    private val adapterMainGrid = MainScreenGridAdapter()
    private val adapterMainScroll = MainScreenHorizontalScrollAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainActivityViewModel = viewModel()
            val colors = listOf(Color.Gray, Color.Red, Color.Green, Color.Yellow, Color.Magenta)
            val items = mutableListOf<ItemViewState>()
            for (i in 0..21) {
                val colorId = nextInt(0, 5)
                items.add(ItemViewState("title $i", background = colors.get(colorId)))
            }

            val gridViewState =
                GridViewState(items, columnsCount = 4, gridBackground = Color.Cyan, itemsHeight = ItemsHeight.Dynamic(20))

            FlexibleGrid(
                gridViewState = gridViewState,
                gridWidth = 300,
                paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            )
        }
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
