package com.example.presentation.util

import com.example.domain.util.DataError
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

suspend fun RoutingContext.respondWithError(error: DataError) {
    when(error) {
        DataError.Database -> {
            call.respond(
                message = "Database error occurred!",
                status = HttpStatusCode.InternalServerError
            )
        }
        DataError.NotFound -> {

            call.respond(
                message = "Resource not found!",
                status = HttpStatusCode.NotFound
            )
        }
        DataError.Unknown -> {

            call.respond(
                message = "An unknown error occurred!",
                status = HttpStatusCode.NotFound
            )
        }
        DataError.Validation -> {

            call.respond(
                message = "Invalid data provided!",
                status = HttpStatusCode.BadRequest
            )
        }
    }
}