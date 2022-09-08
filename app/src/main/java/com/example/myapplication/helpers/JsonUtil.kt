package com.example.myapplication.helpers

import android.content.Context
import com.example.myapplication.entities.SectionList
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object JsonUtil {

    fun getAssetPodcasts(context: Context): SectionList? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val adapter: JsonAdapter<SectionList> = moshi.adapter(SectionList::class.java)
        val file = "mainScreen.json"
        val myjson = context.assets.open(file).bufferedReader().use{ it.readText()}

        return adapter.fromJson(myjson)
    }

}