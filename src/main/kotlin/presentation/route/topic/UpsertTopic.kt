package com.example.presentation.route.topic

import com.example.domain.model.Topic
import com.example.domain.repository.TopicRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.upsertQuestionTopic(
    repository: TopicRepo
) {
    post<TopicRoutesPath.CreateTopic> {
        val topic = call.receive<Topic>()
        repository.upsertTopic(topic)
            .onSuccess {
                call.respond(
                    message = "Topic created successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}