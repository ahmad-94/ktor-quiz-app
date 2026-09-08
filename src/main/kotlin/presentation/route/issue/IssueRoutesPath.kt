package com.example.presentation.route.issue

import io.ktor.resources.Resource

@Resource("/quiz/issues")
class IssueRoutesPath {

    @Resource("/reportIssue")
    data class InsertIssue(
        val parent: IssueRoutesPath = IssueRoutesPath()
    )

    @Resource("/deleteIssue/{issueId?}")
    data class DeleteIssueById(
        val parent: IssueRoutesPath = IssueRoutesPath(),
        val issueId: String? = null
    )
}