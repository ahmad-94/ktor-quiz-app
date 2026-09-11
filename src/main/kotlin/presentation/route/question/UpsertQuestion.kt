package com.example.presentation.route.question

import com.example.domain.model.Question
import com.example.domain.repository.QuestionRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.upsertQuestion(repository: QuestionRepo) {
    post<QuestionRoutesPath.Upsert> {
        val question = call.receive<Question>()
        repository.upsertQuestion(question)
            .onSuccess {
                call.respond(
                    message =  "Question added successfully!",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure {
                respondWithError(it)
            }

    }
}