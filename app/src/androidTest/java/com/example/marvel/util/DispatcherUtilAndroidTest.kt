package com.example.marvel.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

fun generateDispatchAndroidTest(
    apiPath: String,
    successFilePath: String,
    successResponseCode: Int = 200,
    failureJsonBody: String = "{}",
    failureResponseCode: Int = 404
) = object : Dispatcher() {

    val readFile = ReadFileAndroidTest()

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            apiPath -> MockResponse().setResponseCode(successResponseCode).setBody(
                readFile(successFilePath)!!
            )
            else -> MockResponse().setResponseCode(failureResponseCode).setBody(failureJsonBody)
        }
    }

}