package com.javimartd.theguardian.data

import com.google.gson.GsonBuilder
import com.javimartd.theguardian.data.api.APIService
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class NewsRepositoryTest {

    private lateinit var sut: NewsDataRepository
    private lateinit var server : MockWebServer
    private lateinit var apiService: APIService

    @Before
    @Throws fun setUp() {

        server = MockWebServer()
        server.start()
        //server.setDispatcher(MockServerDispatcher().RequestDispatcher())

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(server.url("/"))
                .client(okHttpClient)
                .build()
        apiService = retrofit.create(APIService::class.java)

        sut = NewsDataRepository(apiService)
    }

    @Test
    @Throws(Exception::class)
    fun `get news returns data properly`() {

        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/news/news.json"))
        server.enqueue(mockResponse)

        val response = sut.getNews()
        val request = server.takeRequest()
        assertEquals("/search?show-fields=all&api-key=db0d0891-0604-403a-9fad-f8ea5a76dbcb",
                request.path)
        assertEquals("US-Taliban talks offer glimmer of hope on path to Afghan peace",
                response[0].title)
    }

    @Test
    @Throws(Exception::class)
    fun `server error throw exception`() {
        val mockResponse = MockResponse()
                .setResponseCode(500)
        server.enqueue(mockResponse)
    }

    @Test(expected = Exception::class)
    @Throws(Exception::class)
    fun `time out network exception throw exception`() {
        val mockResponse = MockResponse()
        server.enqueue(mockResponse.setSocketPolicy(SocketPolicy.NO_RESPONSE))
        sut.getNews()
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

    @After
    @Throws fun tearDown() {
        server.shutdown()
    }
}