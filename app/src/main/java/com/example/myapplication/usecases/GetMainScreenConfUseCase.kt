package com.example.myapplication.usecases

import android.content.Context
import com.example.myapplication.entities.SectionList

interface GetMainScreenConfUseCase {
    suspend fun getMainScreenConfUseCase(context: Context): SectionList?
}