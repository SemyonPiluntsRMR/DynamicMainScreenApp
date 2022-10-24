package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapters.MainScreenGridAdapter
import com.example.myapplication.ui.adapters.MainScreenHorizontalScrollAdapter
import com.example.myapplication.ui.widgetItem.WidgetItemLayout
import com.example.myapplication.ui.widgetItem.WidgetItemSubTitleViewState
import com.example.myapplication.ui.widgetItem.WidgetItemTitleViewState
import com.example.myapplication.ui.widgetItem.WidgetItemViewState
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
//            val mainViewModel: MainActivityViewModel = viewModel()
//            val colors = listOf(Color.Gray, Color.Red, Color.Green, Color.Yellow, Color.Magenta)
//            val items = mutableListOf<ItemViewState>()
//            for (i in 0..21) {
//                val colorId = nextInt(0, 5)
//                items.add(ItemViewState("title $i", background = colors.get(colorId)))
//            }
//
//            val layoutViewState =
//                LayoutViewState(items, columnsCount = 2, layoutBackground = Color.Cyan, itemsHeight = ItemsHeight.Dynamic(20))
//
//            FlexibleGrid(
//                layoutViewState = layoutViewState,
//                gridWidth = 300,
//                paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
//                verticalArrangement = Arrangement.spacedBy(15.dp),
//                horizontalArrangement = Arrangement.spacedBy(15.dp)
//            )
//
//            HorizontalScroll(
//                layoutViewState = layoutViewState,
//                layoutWidth = 300,
//                paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
//                horizontalArrangement = Arrangement.spacedBy(15.dp)
//            )

            val viewState = WidgetItemViewState(
                layoutWidth = 300.dp,
                layoutHeight = 200.dp,
                paddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                alignmentImg = Alignment.CenterEnd
            )
            val titleViewState = WidgetItemTitleViewState(
                title = "MyTitle",
                alignment = Alignment.TopStart,
                fontSize = 28.sp,
                color = Color.Yellow
            )

            val subTitleViewState = WidgetItemSubTitleViewState(
                subTitle = "MySubTitle",
                alignment = Alignment.BottomStart,
                fontSize = 18.sp,
                color = Color.Black
            )

            WidgetItemLayout(
                viewState = viewState,
                titleViewState = titleViewState,
                subTitleViewState = subTitleViewState,
                onClick = {})
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
