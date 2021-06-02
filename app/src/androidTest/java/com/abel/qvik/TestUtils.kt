package com.abel.qvik

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

fun Int.checkInvisible() {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
}

fun Int.checkVisible() {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun Int.checkExists() {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(Matchers.not(ViewAssertions.doesNotExist())))
}
