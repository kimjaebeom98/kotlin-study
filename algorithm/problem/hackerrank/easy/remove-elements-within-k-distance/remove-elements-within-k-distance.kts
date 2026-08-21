/**
 * HackerRank Easy — Remove Elements Within K Distance
 *
 * 정렬된 배열에서 마지막으로 채택한 원소와 K 이상 떨어진 것만 살리며 개수를
 * 세는 문제다. 비교 기준이 '직전 원소'가 아니라 '마지막으로 채택된 원소'라는 게
 * 핵심이라 — 예시에서 3이 직전의 2가 아닌 채택된 1과 비교돼 제거되는 것처럼 —
 * left 포인터를 마지막 채택 원소에 두고, 채택이 일어날 때만 그 위치로 점프시켰다.
 * right는 매 원소를 한 번씩 훑는 순회 포인터다.
 * ★조건을 만족하는 첫 원소를 즉시 채택하는 것이 최적인 이유는, 정렬돼 있어서
 * 더 뒤의 원소를 대신 채택하면 다음 기준점만 더 커져 이후 선택지가 줄면 줄었지
 * 늘 수 없기 때문이다 — 가장 이른 채택이 항상 손해가 아니다.★
 * 크기가 1 이하면 비교 대상이 없어 그대로 반환한다.
 * 제자리에서 변수만 쓰므로 공간 O(1), right가 n번만 전진하므로 시간 O(n)이고,
 * n이 1000이라 충분하다.
 */
fun removeElementsWithinKDistance(timestamps: Array<Int>, K: Int): Int {
    val n = timestamps.size
    if (n <= 1) return n

    var lastKept = timestamps[0]
    var cnt = 1
    for (i in 1 until n) {
        if (timestamps[i] - lastKept >= K) {
            lastKept = timestamps[i]
            cnt++
        }
    }
    return cnt
}

fun main() {
    check(removeElementsWithinKDistance(arrayOf(1, 2, 3, 8, 10), 3) == 2)
    check(removeElementsWithinKDistance(arrayOf(), 10) == 0)
    check(removeElementsWithinKDistance(arrayOf(0), 5) == 1)
    println("all passed")
}

main()
