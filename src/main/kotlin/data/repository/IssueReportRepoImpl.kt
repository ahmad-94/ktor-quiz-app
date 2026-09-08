package com.example.data.repository

import com.example.data.database.entity.IssueReportEntity
import com.example.data.database.mapper.toIssueReport
import com.example.data.database.mapper.toIssueReportEntity
import com.example.data.util.Constants
import com.example.domain.model.IssueReport
import com.example.domain.repository.IssueReportRepo
import com.example.domain.util.DataError
import com.example.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class IssueReportRepoImpl(
    database: MongoDatabase
): IssueReportRepo {

    val issueReportCollection = database
        .getCollection<IssueReportEntity>(Constants.ISSUE_COLLECTION)

    override suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError> {
        return try {
            val reports = issueReportCollection
                .find()
                .map { it.toIssueReport() }
                .toList()
            if (reports.isNotEmpty()) {
                Result.Success(reports)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun insertIssueReport(issueReport: IssueReport): Result<Unit, DataError> {
        return try {
            issueReportCollection
                .insertOne(issueReport.toIssueReportEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteIssueReport(id: String?): Result<Unit, DataError> {
        if (id.isNullOrEmpty()) {
            Result.Failure(DataError.Validation)
        }
        return try {
            val selectedIssue = Filters.eq(
                IssueReportEntity::_id.name, id
            )
            val isDeletedIssue = issueReportCollection
                .deleteOne(selectedIssue)
            if (isDeletedIssue.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}