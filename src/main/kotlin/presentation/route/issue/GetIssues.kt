package com.example.presentation.route.issue

import com.example.domain.repository.IssueReportRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.getIssues(
    repository: IssueReportRepo
) {
    get<IssueRoutesPath>() {
        repository.getAllIssueReports()
            .onSuccess { issues ->
                call.respond(
                    message = issues,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}