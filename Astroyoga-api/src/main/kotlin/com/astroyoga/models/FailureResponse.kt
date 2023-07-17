package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Boolean, override val message: String) : Response