package com.example.presentation.route.issue

import com.example.domain.repository.IssueReportRepo
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteIssueById(
    repository: IssueReportRepo
) {
    post<IssueRoutesPath.DeleteIssueById> {deleteIssueRoute ->
        repository.deleteIssueReport(deleteIssueRoute.issueId)
            .onSuccess {
                call.respond(
                    message = "Issue with id ${deleteIssueRoute.issueId} was deleted!",
                    status = HttpStatusCode.Accepted
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}