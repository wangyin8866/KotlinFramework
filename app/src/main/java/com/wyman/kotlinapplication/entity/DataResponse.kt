package com.wyman.kotlinapplication.entity

import com.squareup.moshi.Json

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
//{"data":[{"desc":"一起来做个App吧"
// ,"id":10
// ,"imagePath":"http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"
// ,"isVisible":1
// ,"order":2
// ,"title":"一起来做个App吧"
// ,"type":0
// ,"url":"http://www.wanandroid.com/blog/show/2"}

data class HttpResult<T>(
        @Json(name = "data") val data: T,
        @Json(name = "errorCode") val errorCode: Int,
        @Json(name = "errorMsg") val errorMsg: String
)

data class Banner(
        @Json(name = "desc") val desc: String,
        @Json(name = "id") val id: Int,
        @Json(name = "imagePath") val imagePath: String,
        @Json(name = "isVisible") val isVisible: Int,
        @Json(name = "order") val order: Int,
        @Json(name = "title") val title: String,
        @Json(name = "type") val type: Int,
        @Json(name = "url") val url: String
)