package com.example.presentation.route.question

import com.example.domain.repository.QuestionRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.get
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getAllQuizQuestions(repository: QuestionRepo){
    get<QuestionRoutesPath> { routePath ->
        repository.getAllQuestions(routePath.topicCode, routePath.limit)
            .onSuccess { questions ->
                call.respond(
                    message = questions,
                    status = HttpStatusCode.OK
                )
            }

            .onFailure {
                respondWithError(it)
            }

    }
}