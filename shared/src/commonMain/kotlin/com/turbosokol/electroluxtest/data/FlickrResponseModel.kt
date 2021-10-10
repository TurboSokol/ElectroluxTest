package com.turbosokol.electroluxtest.data

import kotlinx.serialization.Serializable

@Serializable
data class FlickrResponseModel(
	val stat: String? = null,
	val photos: Photos? = null
)

@Serializable
data class PhotoItem(
	val owner: String? = null,
	val server: String? = null,
	val ispublic: Int? = null,
	val isfriend: Int? = null,
	val farm: Int? = null,
	val id: String? = null,
	val secret: String? = null,
	val url_m: String? = null,
	val title: String? = null,
	val height_m: Int? = null,
	val width_m: Int? = null,
	val isfamily: Int? = null
)

@Serializable
data class Photos(
	val perpage: Int? = null,
	val total: Int? = null,
	val pages: Int? = null,
	val photo: List<PhotoItem?>? = null,
	val page: Int? = null
)
