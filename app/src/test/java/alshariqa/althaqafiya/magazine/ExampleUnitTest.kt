package alshariqa.althaqafiya.magazine

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun anagramN() {
        val s1 = "aawerty"
        val s2 = "wertyaa"
        val map1 = hashMapOf<Char, Int>()
        val map2 = hashMapOf<Char, Int>()
        s1.forEachIndexed { index, c ->
            map1[c] = map1.getOrDefault(c, 0) + 1
            map2[s2[index]] = map2.getOrDefault(s2[index], 0) + 1

        }

        assertEquals(true, map1 == map2)
    }

    // low space complexity
    @Test
    fun anagramNAnother() {
        val s1 = "aawerty"
        val s2 = "wertyaa"
        var asciiCount1 = 0
        var asciiCount2 = 0

        s1.forEachIndexed { index, c ->
            val c2 = s2[index]
            asciiCount1 += c.toByte().toInt()
            asciiCount2 += c2.toByte().toInt()

        }

        assertEquals(true, asciiCount1 == asciiCount2)
    }

    @Test
    fun test() {
        val array1 = arrayOf(1, 2, 3)
        val array2 = arrayOf(0, 1)
        if (array1.size > array2.size) {


        } else if (array2.size > array1.size) {

        }
    }
}
