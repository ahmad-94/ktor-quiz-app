package com.example.presentation.route.topic

import com.example.domain.repository.TopicRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.resources.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.getAllTopics(
    repository: TopicRepo
) {
    get<TopicRoutesPath> {
        repository.getAllTopics()
            .onSuccess {
                call.respond(
                    message = it,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}