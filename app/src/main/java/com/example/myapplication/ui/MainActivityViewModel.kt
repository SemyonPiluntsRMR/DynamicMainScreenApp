package com.example.myapplication.ui

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.entities.Category
import com.example.myapplication.usecases.GetMainScreenConfUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCaseMainScreenConfig: GetMainScreenConfUseCase) :
    ViewModel() {

    private val _girdParams = MutableSharedFlow<GirdParams>()
    val girdParams: SharedFlow<GirdParams> get() = _girdParams

    private val _horizontalScrollParams = MutableSharedFlow<HorizontalScrollParams>()
    val horizontalScrollParams: SharedFlow<HorizontalScrollParams> get() = _horizontalScrollParams

    public fun getMainScreenConfig(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val merchandiseList = useCaseMainScreenConfig.getMainScreenConfUseCase(context)
            merchandiseList?.let {
                val wm =
                    context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val displayMetrics = DisplayMetrics()
                wm.defaultDisplay.getMetrics(displayMetrics)
                val height = displayMetrics.heightPixels
                val width = displayMetrics.widthPixels
                val margin =
                    context.resources.getDimension(R.dimen.item_grid_margin).toDouble()
                it.sections.forEach {
                    val section = it
                    val type = it.type
                    when (type.name) {
                        ContainerTypes.GRID.type -> {
                            val marginWidth = (Math.ceil(margin).toInt()) * 2
                            _girdParams.emit(
                                GirdParams(
                                    gridCategories = section.categories,
                                    couloumnWidth = width / type.columns - marginWidth
                                )
                            )
                        }
                        ContainerTypes.SCROLL.type -> {
                            _horizontalScrollParams.emit(
                                HorizontalScrollParams(
                                    scrollCategories = section.categories,
                                    couloumnWidth = (width / (type.columns + 0.5)).toInt() - (Math.ceil(margin)
                                        .toInt()) * 2
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


enum class ContainerTypes(val type: String) {
    GRID("grid"),
    SCROLL("scroll")
}

data class GirdParams(
    val gridCategories: List<Category>,
    val couloumnWidth: Int,
)

data class HorizontalScrollParams(
    val scrollCategories: List<Category>,
    val couloumnWidth: Int,
)