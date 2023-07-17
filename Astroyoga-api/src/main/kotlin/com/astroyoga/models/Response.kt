package com.astroyoga.models

/*
data class Response<T>(val status: Int, val message: String, val data: T?)

fun <T> Route.response(status: Int, message: String, data: T?) {
    call.respond(Response(status, message, data))
}

fun <T> Route.success(message: String = "Success", data: T? = null) = response(200, message, data)

fun <T> Route.badRequest(message: String = "Bad Request", data: T? = null) = response(400, message, data)

fun <T> Route.notFound(message: String = "Not Found", data: T? = null) = response(404, message, data)

fun <T> Route.serverError(message: String = "Server Error", data: T? = null) = response(500, message, data)*/





/**
 * Response model to expose in API response
 */
interface Response {
    val status: Boolean
    val message: String
}

/**
 * HTTP Response Status. Used for evaluation of [HttpResponse] type.
 */
enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}