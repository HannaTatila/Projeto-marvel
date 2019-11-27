package com.example.marvel

import com.example.marvel.data.ComicResponse
import com.example.marvel.data.ThumbnailResponse
import com.example.marvel.data.datasource.remote.ComicRemoteDataSource
import com.example.marvel.di.ComicsModule.dataModule
import com.example.marvel.domain.Comics
import com.example.marvel.domain.repositories.ComicRepository
import com.example.marvel.rules.LocalTestRule
import com.example.marvel.util.generateDispatch
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito

class ComicRepositoryTest: KoinTest {

    private val comicRepository: ComicRepository by inject()

    @get:Rule
    val localTestRule = LocalTestRule(mutableListOf(dataModule))

    @Test
    fun `getList should emmit list comics when api server return success`() {

        // Given
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

        val comicsList = listOf(
            Comics(
                183,
                "Startling Stories: The Incorrigible Hulk (2004) #1",
                "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                "For Doctor Bruce Banner life is anything but normal. But what happens when two women get between him and his alter ego, the Incorrigible Hulk? Hulk confused! \r\nIndy superstar Peter Bagge (THE MEGALOMANIACAL SPIDER-MAN) takes a satirical jab at the Hulk mythos with a tale of dames, debauchery and destruction.\r\n32 PGS./MARVEL PSR...$2.99"
            )
        )

        declareMock<ComicRemoteDataSource> {
            BDDMockito.given(this.getList()).willReturn(Single.just(comicResponseList))
        }

        // When
        val results = comicRepository.getList().test()

        // Then
        results.assertResult(comicsList)

    }

}