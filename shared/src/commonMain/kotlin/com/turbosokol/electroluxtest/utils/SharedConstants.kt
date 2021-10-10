package com.turbosokol.electroluxtest.utils

//Base flickr url with token for simplify
const val BASE_URL = "https://api.flickr.com/services/rest?api_key=9100cda86ae5bbfe4e784301265565cd&method=flickr.photos.search&tags="
//Default search tag for launch app
const val ELECTROLUX_TAG = "electrolux"
//Arguments of flickr query
const val DEFAULT_FLICKR_QUERY_ARGUMENTS = "&format=json&nojsoncallback=true&extras=media&extras=url_sq&extras=url_m"