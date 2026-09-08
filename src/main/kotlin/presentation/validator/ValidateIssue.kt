package com.example.presentation.validator





import com.example.domain.model.IssueReport
import io.ktor.server.plugins.requestvalidation.*

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(email)
}

fun RequestValidationConfig.validateIssue() {
    validate<IssueReport> {
        when {
            it.issueType.isEmpty() -> {
                ValidationResult.Invalid( reason = "issue type not be empty!" )
            }
            it.timeStamp.isEmpty() -> {
                ValidationResult.Invalid( reason = "the time stamp should not be empty!" )
            }
            it.userEmail != null && !isValidEmail(it.userEmail)-> {
                ValidationResult.Invalid( reason = "invalid email format!" )
            }
            it.additionalComment != null && it.additionalComment.length < 5 -> {
                ValidationResult.Invalid( reason = "additional comment should be at leat 5 characters long!" )
            }
            else -> { ValidationResult.Valid }
        }
    }
}