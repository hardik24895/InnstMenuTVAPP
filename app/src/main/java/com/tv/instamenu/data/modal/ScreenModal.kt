package com.tv.instamenu.data.modal

import com.google.gson.annotations.SerializedName

data class ScreenModal(

	@field:SerializedName("data")
	val data: MutableList<ScreenItem> = mutableListOf(),

	@field:SerializedName("status")
	val status: Int? = null
)

data class ScreenItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("img")
	val img: String? = null
)
