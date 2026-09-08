package com.example.presentation.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.respond(
                message = cause.reasons.joinToString(),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}