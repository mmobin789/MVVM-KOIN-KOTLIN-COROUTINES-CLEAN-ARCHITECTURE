package alshariqa.althaqafiya.magazine

object UniqueSubstringLength {
    private lateinit var subStrings: ArrayList<String>
    private lateinit var map: HashMap<Char, Int>
    private lateinit var sb: StringBuilder
    private var newSubstringStarted = false
    private var uniqueString = true


    fun lengthOfLongestSubstring(s: String): Int {

        if (s.isEmpty())
            return 0

        val length = s.length

        if (length == 1)
            return 1


        subStrings = ArrayList(length)
        sb = StringBuilder(length)
        map = HashMap(length)
        checkSubString(s)

        return subStrings.maxBy {
            println(it)
            it.length
        }!!.length


    }

    private fun checkSubString(s: String, index: Int = 0) {
        for (i in index..s.lastIndex) {
            val c = s[i]
            val count = map.getOrDefault(c, 0)
            if (count == 0 || newSubstringStarted) {
                map[c] = count + 1
                sb.append(c)
                newSubstringStarted = false
            } else  // a new substring has begun so save previous one also the string is not unique.
            {
                uniqueString = false
                subStrings.add(sb.toString())
                sb.setLength(0)
                newSubstringStarted = true
                checkSubString(s, i)

            }


        }
        if (uniqueString)
            subStrings.add(sb.toString())
    }
}