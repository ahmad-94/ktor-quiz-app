package com.example.presentation.route.question

import com.example.domain.model.Question
import com.example.domain.repository.QuestionRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.insertAllQuestions(repository: QuestionRepo) {
    post<QuestionRoutesPath.InsertAllQuestions> {
        val questions = call.receive<List<Question>>()
        repository.insertAllQuestions(questions)
            .onSuccess {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = "Questions successfully inserted"
                )
            }
    }
}