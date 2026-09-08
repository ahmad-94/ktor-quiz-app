package com.example.presentation.route.question

import io.ktor.resources.Resource

@Resource(path = "/quiz/questions")
class QuestionRoutesPath(
    val topicCode: Int? = null,
    val limit: Int? = null
) {
    @Resource("/{questionId?}")
    data class ById(
        val parent: QuestionRoutesPath = QuestionRoutesPath(),
        val questionId: String? = null
    )

    @Resource("/create&update")
    data class Upsert(
        val parent: QuestionRoutesPath = QuestionRoutesPath(),
    )
}