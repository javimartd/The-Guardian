package com.javimartd.theguardian.data.common

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File

class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "api/example1" -> MockResponse().setResponseCode(200).setBody(getJson(""))
                "api/example2" -> MockResponse().setResponseCode(200).setBody(getJson(""))
                else -> MockResponse().setResponseCode(404)
            }
        }

        /**
         * Helper function which will load JSON from
         * the path specified
         *
         * @param path : Path of JSON file
         * @return json : JSON from file at given path
         */
        private fun getJson(path : String) : String {
            val uri = this.javaClass.classLoader.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().setResponseCode(400)

        }
    }
}