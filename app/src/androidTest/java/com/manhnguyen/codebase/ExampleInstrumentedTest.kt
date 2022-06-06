package com.manhnguyen.codebase

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.threeten.bp.LocalDateTime
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mvvmpractices", appContext.packageName)
    }

    @Test

    fun convertDateToLocalTime(){
        kotlin.runCatching {
            val datetime = Date()
            val local = LocalDateTime.parse(datetime.toString())
        }

    }
}
