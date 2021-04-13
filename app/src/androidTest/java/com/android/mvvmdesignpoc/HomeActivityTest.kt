package com.android.mvvmdesignpoc

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.mvvmdesignpoc.features.dashboard.view.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun testScreenLoaded() {
        // Verify if the screen is loaded or not
        onView(withId(R.id.txtScreenTitle)).check(matches(isDisplayed()))

        CountingIdlingResourceSingleton.increment()

        //We can wait for API response using kotlin coroutines on back-ground thread
        val job = GlobalScope.launch {
            delay(2000)
        }

        job.invokeOnCompletion {
            CountingIdlingResourceSingleton.decrement()
        }

        //Verify title of home screen
        onView(withId(R.id.txtScreenTitle)).check(matches(withText("ABOUT CANADA")))

        //Declare the expected test data
        val title = "Beavers"
        val imageURL = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        val description = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"

        //Verify the details for the first row item
        onView(allOf(isDisplayed(),
            first(withId(R.id.txtTitle))))
            .check(matches(withText(title)))

        onView(allOf(isDisplayed(),
            first(withId(R.id.txtDescription))))
            .check(matches(withText(description)))

        onView(allOf(isDisplayed(),
            first(withId(R.id.image))))
            .check(matches(withContentDescription(imageURL)))

        //Click on first element from recycler view
        onView(allOf(isDisplayed(), first(withId(R.id.cardView)))).perform(click())


        //Verify the details for selected row
        onView(withId(R.id.txtTitle)).check(matches(withText(title)))
        onView(withId(R.id.txtDescription)).check(matches(withText(description)))
        onView(withId(R.id.image)).check(matches(withContentDescription(imageURL)))
    }

    private fun <T> first(matcher: Matcher<T>): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("First matching row item")
            }
        }
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }


    private object CountingIdlingResourceSingleton {

        private const val RESOURCE = "GLOBAL"

        @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            countingIdlingResource.increment()
        }

        fun decrement() {
            if (!countingIdlingResource.isIdleNow) {
                countingIdlingResource.decrement()
            }
        }
    }
}