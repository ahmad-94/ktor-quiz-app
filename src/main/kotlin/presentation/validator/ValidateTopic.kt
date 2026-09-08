package com.example.presentation.validator



import com.example.domain.model.Topic
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateTopic() {
    validate<Topic> {
        when {
            it.name.isEmpty() -> {
                ValidationResult.Invalid( reason = "topic name should not be empty!" )
            }
            it.imageUrl.isEmpty() -> {
                ValidationResult.Invalid( reason = "image URL should not be empty!" )
            }

            it.code < 0 -> {
                ValidationResult.Invalid( reason = "topic code should be greater than 0 and be a whole number!" )
            }
            else -> { ValidationResult.Valid }
        }
    }
}