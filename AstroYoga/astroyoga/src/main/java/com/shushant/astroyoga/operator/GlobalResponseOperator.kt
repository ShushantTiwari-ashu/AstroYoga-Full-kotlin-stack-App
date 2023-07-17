package com.shushant.astroyoga.operator

import android.app.Application
import android.widget.Toast
import com.skydoves.sandwich.*
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
    private val application: Application
) : ApiResponseSuspendOperator<T>() {

    // The body is empty, because we will handle the success case manually.
    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) {}

    // handles error cases when the API request gets an error response.
    // e.g., internal server error.
    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())

                // handling error based on status code.
                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else -> toast("$statusCode(${statusCode.code}): ${message()}")
                }

                // map the ApiResponse.Failure.Error to a customized error model using the mapper.
                map(ErrorEnvelopeMapper) {
                    Timber.d("[Code: $code]: $message")
                }
            }
        }
    }

    // handles exceptional cases when the API request gets an exception response.
    // e.g., network connection error, timeout.
    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())
                toast(message())
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}


// Create your custom error model.
data class ErrorEnvelope(
    val code: Int,
    val message: String
)

// An error response mapper.
// Create an instance of your custom model using the `ApiResponse.Failure.Error` in the `map`.
object ErrorEnvelopeMapper : ApiErrorModelMapper<ErrorEnvelope> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorEnvelope {
        return ErrorEnvelope(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}