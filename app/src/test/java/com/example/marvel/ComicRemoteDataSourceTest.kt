package com.example.marvel

import com.example.marvel.core.di.networkModule
import com.example.marvel.data.ComicResponse
import com.example.marvel.data.ThumbnailResponse
import com.example.marvel.data.datasource.remote.ComicRemoteDataSource
import com.example.marvel.di.ComicsModule.dataModule
import com.example.marvel.rules.LocalTestRule
import com.example.marvel.util.generateDispatch
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ComicRemoteDataSourceTest : KoinTest {

    private val comicRemoteDataSource: ComicRemoteDataSource by inject()

    @get:Rule
    val localTestRule = LocalTestRule(mutableListOf(dataModule, networkModule))

    @Test
    fun `getList should emmit list comics when api server return success`() {

        // Given
        localTestRule.serverRule.dispatcher = successComicList()

        val comicResponseList = mutableListOf(
            ComicResponse(
                183,
                ThumbnailResponse(
                    "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
                    "jpg"
                ),
                "Startling Stories: The Incorrigible Hulk (2004) #1",
                "For Doctor Bruce Banner life is anything but normal. But what happens when two women get between him and his alter ego, the Incorrigible Hulk? Hulk confused! \r\nIndy superstar Peter Bagge (THE MEGALOMANIACAL SPIDER-MAN) takes a satirical jab at the Hulk mythos with a tale of dames, debauchery and destruction.\r\n32 PGS./MARVEL PSR...$2.99"
            )
        )

        // WHen
        val results = comicRemoteDataSource.getList().test()

        // Then
        results.assertResult(comicResponseList)

    }

    private fun successComicList() = generateDispatch(
        "/v1/public/comics?ts=1&apikey=c41a380d16444cae4dfa217d408336e1&hash=78abe8add9743df8eff80d42a5672cdf",
        "listComicsResourcesJson"
    )

}