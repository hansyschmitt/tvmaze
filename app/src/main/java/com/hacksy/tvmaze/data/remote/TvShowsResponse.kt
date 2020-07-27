package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.model.TvSeries


data class TvShowsResponse(val status:Int?, val msg:String?, val data:List<TvSeries>?){
    fun isSuccess():Boolean= (status==200)
}