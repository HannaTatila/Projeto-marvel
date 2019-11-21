package com.example.marvel

import com.example.marvel.presentation.ComicsActivity
import org.junit.Test
import org.junit.After
import org.junit.Before
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.Espresso.onView
import android.os.AsyncTask
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import com.example.marvel.core.di.BASE_URL
import com.example.marvel.core.di.networkModule
import com.example.marvel.di.ComicsModule.dataModule
import com.example.marvel.di.ComicsModule.domainModule
import com.example.marvel.di.ComicsModule.presentationModule
import com.example.marvel.util.generateDispatchAndroidTest
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Retrofit
import java.io.Serializable

open class ComicsActivityTest : KoinTest {

    private var server = MockWebServer()
    private lateinit var activityScenario: ActivityScenario<ComicsActivity>

    @Before
    open fun setUp() {
        activityScenario = ActivityScenario.launch(ComicsActivity::class.java)
        activityScenario.moveToState(Lifecycle.State.CREATED)

        server.start()
    }

    @Test
    fun shouldShowComicsListWhenOpenComicsActivity() {
        //initWebServer(successComicList())

        server.dispatcher = successComicList()

        onView(withId(R.id.activityComicsList)).check(matches(isDisplayed()))
    }

    private fun successComicList() = generateDispatchAndroidTest(
        "/v1/public/comics?ts=1&apikey=c41a380d16444cae4dfa217d408336e1&hash=78abe8add9743df8eff80d42a5672cdf",
        "listComics"
    )

    @After
    open fun tearDown() {
        server?.close()
        stopKoin()
        activityScenario.close()
    }


    ////////

/*    private lateinit var activityScenario: ActivityScenario<ComicsActivity>
    var mockWebServer = MockWebServer()

    @Before
    fun init() {
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2.x IO Scheduler"))
        mockWebServer.url("/").toString()
    }

    @Test
    fun shouldShowComicsListWhenOpenComicsActivity() {
        activityScenario = ActivityScenario.launch(ComicsActivity::class.java)
        activityScenario.moveToState(Lifecycle.State.CREATED)
        mockWebServer.dispatcher = successComicList()
        onView(withId(R.id.activityComicsList)).check(matches(isDisplayed()))
    }

    private fun successComicList() = generateDispatchAndroidTest(
        "/v1/public/comics?ts=1&apikey=c41a380d16444cae4dfa217d408336e1&hash=78abe8add9743df8eff80d42a5672cdf",
        "listComics"
    )

    @After
    fun after() {
        stopKoin()
        mockWebServer.shutdown()
        activityScenario.close()
    }*/

}