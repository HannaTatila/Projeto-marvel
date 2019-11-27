package com.example.marvel

import com.example.marvel.di.ComicsModule.domainModule
import com.example.marvel.di.ComicsModule.presentationModule
import com.example.marvel.domain.Comics
import com.example.marvel.domain.usecase.ComicsUseCase
import com.example.marvel.presentation.ComicsViewModel
import com.example.marvel.presentation.ComicsViewState
import com.example.marvel.rules.LocalTestRule
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito

class ComicsViewModelTest : KoinTest {

    private val comicsViewModel: ComicsViewModel by inject()
    @get:Rule
    val localTestRule = LocalTestRule(mutableListOf(presentationModule, domainModule))

    @Test
    fun `getComicsList should emmit list comics when api server return success`() {

        // Given
        val comicsList = listOf(
            Comics(
                183,
                "Startling Stories: The Incorrigible Hulk (2004) #1",
                "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                "When Hank Pym - a.k.a. Ant-Man - is tapped by U.S. Intelligence to infiltrate an international spy ring that has been siphoning secrets out of Washington"
            ),
            Comics(
                183,
                "Startling Stories: The Incorrigible Hulk (2004) #2",
                "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                "2 When Hank Pym - a.k.a. Ant-Man - is tapped by U.S. Intelligence to infiltrate an international spy ring that has been siphoning secrets out of Washington"
            )
        )

        val comicsViewState = ComicsViewState(false, comicsList)
        var actualComicsViewState = ComicsViewState()

        declareMock<ComicsUseCase> {
            BDDMockito.given(this.invoke()).willReturn(Single.just(comicsList))
        }

        comicsViewModel.comicsViewState.observeForever {
            it?.let { state ->
                actualComicsViewState = state
            }
        }

        // When
        comicsViewModel.getComicsList()

        // Then
        assertEquals(comicsViewState, actualComicsViewState)

    }

}