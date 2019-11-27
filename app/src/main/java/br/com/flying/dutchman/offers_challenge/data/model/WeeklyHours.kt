package br.com.flying.dutchman.offers_challenge.data.model

import com.google.gson.annotations.SerializedName

data class WeeklyHours(
    @SerializedName("fri")
    val fri: List<Any>,
    @SerializedName("mon")
    val mon: List<Mon>,
    @SerializedName("sat")
    val sat: List<Any>,
    @SerializedName("sun")
    val sun: List<Sun>,
    @SerializedName("thu")
    val thu: List<Thu>,
    @SerializedName("tue")
    val tue: List<Tue>,
    @SerializedName("wed")
    val wed: List<Wed>
)