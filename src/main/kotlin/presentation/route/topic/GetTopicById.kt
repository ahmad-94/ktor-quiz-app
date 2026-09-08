package com.example.presentation.route.topic

import com.example.domain.model.Topic
import com.example.domain.repository.TopicRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.getTopicById(
    repository: TopicRepo
) {
    get<TopicRoutesPath.GetTopic> {getTopicRoute ->
        repository.getTopicById(getTopicRoute.topicId)
            .onSuccess { topic ->
                call.respond(
                    message = topic,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }

    }
}