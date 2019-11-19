package com.example.marvel.rules

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvel.core.di.BASE_URL
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

class LocalTestRule(val koinModules: List<Module> = listOf()) : TestRule, KoinTest {

    val serverRule = MockWebServer()

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain
            .outerRule(InstantTaskExecutorRule())
            .around(RxSchedulerRule())
            .around(serverRule)
            .around(StartKoinRule())
            .apply(base, description)
    }

    inner class StartKoinRule : ExternalResource() {

        override fun before() {
            super.before()

            startKoin {
                modules(koinModules)
                properties(
                    mapOf(
                        BASE_URL to serverRule.url("/").toString()
                    )
                )
            }
        }

        override fun after() {
            super.after()
            serverRule.shutdown()
            stopKoin()
        }
    }

}