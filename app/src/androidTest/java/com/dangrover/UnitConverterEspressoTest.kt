package com.dangrover.unitconverter
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.*
import org.hamcrest.CoreMatchers.*
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CompletableFuture.allOf

@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun checkMilesToFeet() {

        // type 1 mile into the from field
        onView(withId(R.id.fromQuantity))
            .perform(typeText("1"))

        // select miles in the from unit spinner
        onView(withId(R.id.fromUnitSpinner)).perform(click());

        // choose "mi" from spinner
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("mi"))).perform(click());

        // choose "ft" in to spinner
        onView(withId(R.id.toUnitSpinner)).perform(click());
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("ft"))).perform(click());

        // check the displayed value
        onView(withId(R.id.resultDisplayLabel)).check(matches(withText("5280.000 ft")))
    }
}