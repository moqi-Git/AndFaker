package com.github.moqi.faker

import com.github.moqi.faker.weibo.datasource.tools.formTimeString
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val testStr = "Wed Jun 01 00:50:25 +0800 2011"

        val str = formTimeString(testStr)
        print(str)
        assertEquals(4, 2 + 2)
    }
}
