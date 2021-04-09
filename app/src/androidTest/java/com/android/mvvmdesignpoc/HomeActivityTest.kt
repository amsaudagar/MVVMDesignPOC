package com.android.mvvmdesignpoc

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.mvvmdesignpoc.features.dashboard.view.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule @JvmField var activityActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testScreenLoaded() {
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(withText("HOME")))
    }
}