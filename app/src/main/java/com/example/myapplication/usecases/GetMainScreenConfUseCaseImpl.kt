package com.example.myapplication.usecases

import android.content.Context
import com.example.myapplication.helpers.JsonUtil

class GetMainScreenConfUseCaseImpl() : GetMainScreenConfUseCase {
    override suspend fun getMainScreenConfUseCase(context: Context) =
        JsonUtil.getAssetPodcasts(context)
}