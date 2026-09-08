package com.example.di

import data.database.Database
import com.example.data.repository.IssueReportRepoImpl
import com.example.data.repository.QuestionRepoImpl
import com.example.data.repository.TopicRepoImpl
import com.example.domain.repository.IssueReportRepo
import com.example.domain.repository.QuestionRepo
import com.example.domain.repository.TopicRepo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    single { Database.create() }
    singleOf(::QuestionRepoImpl).bind<QuestionRepo>()
    singleOf(::TopicRepoImpl).bind<TopicRepo>()
    singleOf(::IssueReportRepoImpl).bind<IssueReportRepo>()
}