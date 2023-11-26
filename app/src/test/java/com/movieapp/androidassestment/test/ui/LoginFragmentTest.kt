package com.movieapp.androidassestment.test.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.movieapp.androidassestment.testutil.launchFragmentInHiltContainer
import com.movieapp.androidassestment.testutil.withIndex
import com.movieapp.feature_login.R
import com.movieapp.feature_login.ui.LoginFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(
    manifest = Config.NONE,
    application = HiltTestApplication::class
)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun `check login label is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {
            onView(withIndex(withId(R.id.tv_login_label), 0)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `check email edit text is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {

            onView(withIndex(withId(R.id.til_email), 0)).check(matches(isDisplayed()))
            onView(withIndex(withId(R.id.et_email), 0)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `check password edit text is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {

            onView(withIndex(withId(R.id.til_password), 0)).check(matches(isDisplayed()))
            onView(withIndex(withId(R.id.et_password), 0)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `check login button is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {
            onView(withIndex(withId(R.id.btn_login), 0)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `check forgot password option is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {
            onView(withIndex(withId(R.id.txt_forgot_password_lbl), 0)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `check sign up option is displayed`() {

        launchFragmentInHiltContainer<LoginFragment> {
            onView(withIndex(withId(R.id.tv_signup_lbl), 0)).check(matches(isDisplayed()))
        }
    }
}