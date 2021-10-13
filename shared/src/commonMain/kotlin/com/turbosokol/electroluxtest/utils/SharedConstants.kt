package com.turbosokol.electroluxtest.utils

//Base flickr url with token for simplify (in real project key must be saved in local.properties)
const val BASE_URL = "https://api.flickr.com/services/rest?api_key=9100cda86ae5bbfe4e784301265565cd&method=flickr.photos.search&tags="
//Default search tag for launch app
const val ELECTROLUX_TAG = "Electrolux"
//Arguments of flickr query - request Json with 21 objects
const val DEFAULT_FLICKR_QUERY_ARGUMENTS = "&format=json&nojsoncallback=true&extras=media&extras=url_sq&extras=url_m&per_page=21&page=1"