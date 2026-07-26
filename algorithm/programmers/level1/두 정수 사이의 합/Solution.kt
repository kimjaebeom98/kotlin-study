/**
 * 프로그래머스 Level 1 — 두 정수 사이의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/12912
 *
 * 등차수열 합 공식으로 O(1) 계산.
 * 대소관계가 정해져 있지 않으므로 min/max로 정규화한 뒤,
 * 곱셈 전에 Long으로 올려서 오버플로우를 막는다.
 */
class Solution {
    fun solution(a: Int, b: Int): Long {
        val min = minOf(a, b)
        val max = maxOf(a, b)
        val count = max - min + 1
        return (min.toLong() + max) * count / 2
    }
}

fun main() {
    val s = Solution()
    check(s.solution(3, 5) == 12L)
    check(s.solution(3, 3) == 3L)
    check(s.solution(5, 3) == 12L)
    check(s.solution(-5, 5) == 0L)
    check(s.solution(-10_000_000, 10_000_000) == 0L)
    check(s.solution(0, 10_000_000) == 50_000_005_000_000L)
    println("all passed")
}
