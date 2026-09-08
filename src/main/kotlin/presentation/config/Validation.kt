package com.example.presentation.config

import com.example.presentation.validator.validateIssue
import com.example.presentation.validator.validateQuestion
import com.example.presentation.validator.validateTopic
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuestion()
        validateTopic()
        validateIssue()
    }
}