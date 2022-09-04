package com.example.officedirectory.data.model

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("id")
    val id: String,
    @SerializedName("createdAt")
    val createdDate: String,
    @SerializedName("avatar")
    val avtarUrl: String,
    @SerializedName("jobtitle")
    val jobTitle: String,
    @SerializedName("favouriteColor")
    val favouriteColor: String,
    @SerializedName("email")
    val emailId: String,
    @SerializedName("firstName")
    val firstname: String,
    @SerializedName("lastName")
    val lastname: String
)
