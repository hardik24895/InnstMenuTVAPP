package com.tv.instamenu.data.modal

import com.google.gson.annotations.SerializedName

data class MediaListModal(

	@field:SerializedName("data")
	val data: MutableList<MediaItem> = mutableListOf(),

	@field:SerializedName("status")
	val status: Int? = null
)

data class MediaItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
