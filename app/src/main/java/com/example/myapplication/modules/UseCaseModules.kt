package com.example.myapplication.modules

import com.example.myapplication.usecases.GetMainScreenConfUseCase
import com.example.myapplication.usecases.GetMainScreenConfUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModules {

    @Singleton
    @Provides
    fun provideMainScreenUseCase(): GetMainScreenConfUseCase = GetMainScreenConfUseCaseImpl()
}