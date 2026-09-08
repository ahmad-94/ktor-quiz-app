package com.example.presentation.validator

import com.example.domain.model.Question
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateQuestion() {
    validate<Question> {
        when {
            it.question.isEmpty() -> {
                ValidationResult.Invalid( reason = "question should not be empty!" )
            }
            it.correctAnswer.isEmpty() -> {
                ValidationResult.Invalid( reason = "the correct answer should not be empty!" )
            }
            it.incorrectAnswers.isEmpty() -> {
                ValidationResult.Invalid( reason = "the incorrect answers should not be empty!" )
            }
            it.explanation.isEmpty() -> {
                ValidationResult.Invalid( reason = "explanation should not be empty!" )
            }
            it.topicCode <= 0 -> {
                ValidationResult.Invalid( reason = "topic code should be greater than 0 and be a positive integer!" )
            }
            else -> { ValidationResult.Valid }
        }
    }
}