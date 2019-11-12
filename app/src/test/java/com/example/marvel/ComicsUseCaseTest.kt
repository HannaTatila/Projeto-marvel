package com.example.marvel

import com.example.marvel.di.ComicsModule.dataModule
import com.example.marvel.di.ComicsModule.domainModule
import com.example.marvel.domain.Comics
import com.example.marvel.domain.repositories.ComicRepository
import com.example.marvel.domain.usecase.ComicsUseCase
import com.example.marvel.rules.LocalTestRule
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito

class ComicsUseCaseTest: KoinTest {

    private val comicsUseCase: ComicsUseCase by inject()

    @get:Rule
    val localTestRule = LocalTestRule(mutableListOf(domainModule, dataModule))

    @Test
    fun `invoke should emmit list comics when api server return success`() {

        // Given
        val comicsList = listOf(
            Comics(
                "Startling Stories: The Incorrigible Hulk (2004) #1",
                "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                "When Hank Pym - a.k.a. Ant-Man - is tapped by U.S. Intelligence to infiltrate an international spy ring that has been siphoning secrets out of Washington"
            ),
            Comics(
                "Startling Stories: The Incorrigible Hulk (2004) #2",
                "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                "2 When Hank Pym - a.k.a. Ant-Man - is tapped by U.S. Intelligence to infiltrate an international spy ring that has been siphoning secrets out of Washington"
            )
        )

        declareMock<ComicRepository> {
            BDDMockito.given(this.getList()).willReturn(Single.just(comicsList))
        }

        // When
        val results = comicsUseCase.invoke().test()

        // Then
        results.assertResult(comicsList)
    }
}