package com.example.presentation.route.issue

import com.example.domain.model.IssueReport
import com.example.domain.repository.IssueReportRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.insertIssue(
    repository: IssueReportRepo
) {
    post<IssueRoutesPath.InsertIssue> {
        val issue = call.receive<IssueReport>()
        repository.insertIssueReport(issueReport = issue)
            .onSuccess {
                call.respond(
                    message = "Issue reported successfully!",
                    status = HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}





