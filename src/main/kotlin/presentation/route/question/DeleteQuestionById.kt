package com.example.presentation.route.question

import com.example.domain.repository.QuestionRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.delete
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.quizQuestionById(repository: QuestionRepo) {
    delete<QuestionRoutesPath.ById> { routePath ->
         repository.deleteQuestionById(routePath.questionId)
            .onSuccess {
                call.respond(
                    message = "question with ${routePath.questionId} deleted successfully!",
                    status = HttpStatusCode.Accepted
                )
            }
            .onFailure {
                respondWithError(it)
            }

    }

}