package com.movieapp.androidassestment.test.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.movieapp.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun givenEmptyEmail_WhenValidationIsPerformed_ThenShowWarningMessage() {

        onView(withId(com.movieapp.feature_login.R.id.btn_login)).perform(
            click()
        )

        onView(withText(com.movieapp.core.R.string.email_empty_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun givenInvalidEmail_WhenValidationIsPerformed_ThenShowWarningMessage() {

        onView(withId(com.movieapp.feature_login.R.id.et_email)).perform(
           typeText("test"), closeSoftKeyboard()
        )

        onView(withId(com.movieapp.feature_login.R.id.btn_login)).perform(
            click()
        )

        onView(withText(com.movieapp.core.R.string.email_invalid_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun givenValidEmailAndEmptyPassword_WhenValidationIsPerformed_ThenShowWarningMessage() {

        onView(withId(com.movieapp.feature_login.R.id.et_email)).perform(
            typeText("test@gmail.com"), closeSoftKeyboard()
        )

        onView(withId(com.movieapp.feature_login.R.id.btn_login)).perform(
            click()
        )

        onView(withText(com.movieapp.core.R.string.password_empty_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun givenUnRegisteredLoginCredentials_WhenValidationIsPerformed_ThenShowWarningMessage() {

        onView(withId(com.movieapp.feature_login.R.id.et_email)).perform(
            typeText("test@gmail.com")
        )

        onView(withId(com.movieapp.feature_login.R.id.et_password)).perform(
            typeText("test_password"), closeSoftKeyboard()
        )

        onView(withId(com.movieapp.feature_login.R.id.btn_login)).perform(
            click()
        )

        onView(withText(com.movieapp.core.R.string.account_not_found)).check(matches(isDisplayed()))
    }
}


