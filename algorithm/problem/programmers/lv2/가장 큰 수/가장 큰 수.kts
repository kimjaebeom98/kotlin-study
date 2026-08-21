/**
 * 프로그래머스 Level 2 — 가장 큰 수
 *
 * 각 수를 문자열로 바꾼 뒤 "a+b vs b+a" 이어붙인 값을 비교하는 커스텀 정렬로
 * 내림차순 배치하면, 이어 붙였을 때 가장 큰 문자열이 된다. 정렬 후 이어 붙인
 * 결과의 맨 앞이 '0'이면 전체가 0으로만 이루어진 경우(예: [0, 0])이므로 "0"
 * 하나만 반환한다.
 */
class Solution {
    fun solution(numbers: IntArray): String {
        val result = numbers.map { it.toString() }.sortedWith(Comparator { a, b -> (b + a).compareTo(a + b) }).joinToString("")
        return if (result[0] == '0') "0" else result
    }
}

fun main() {
    val s = Solution()
    check(s.solution(intArrayOf(6, 10, 2)) == "6210")
    check(s.solution(intArrayOf(3, 30, 34, 5, 9)) == "9534330")
    check(s.solution(intArrayOf(0, 0)) == "0")
    println("all passed")
}

main()
