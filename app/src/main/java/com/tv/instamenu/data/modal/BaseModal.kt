package com.tv.instamenu.data.modal

import com.google.gson.annotations.SerializedName

data class BaseModal(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Data(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
