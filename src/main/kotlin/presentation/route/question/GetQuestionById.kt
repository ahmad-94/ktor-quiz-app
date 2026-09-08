package com.example.presentation.route.question

import com.example.domain.repository.QuestionRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.get
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getQuestionById(repository: QuestionRepo) {
    get<QuestionRoutesPath.ById> { routePath ->

        repository.getQuestionById(routePath.questionId)
            .onSuccess { question ->
                call.respond(
                    status = HttpStatusCode.OK,
                    message = question

                )
            }
            .onFailure {
                respondWithError(it)
            }
    }

}