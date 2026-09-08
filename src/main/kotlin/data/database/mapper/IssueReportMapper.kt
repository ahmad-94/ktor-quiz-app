package com.example.data.database.mapper

import com.example.data.database.entity.IssueReportEntity
import com.example.domain.model.IssueReport

fun IssueReportEntity.toIssueReport() = IssueReport(
    id = _id,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timeStamp = timeStamp
)

fun IssueReport.toIssueReportEntity() = IssueReportEntity(
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timeStamp = timeStamp
)