/**
 * HackerRank Easy — Two Sum
 *
 * 두 원소의 합이 목표가 되는 쌍을 찾는 문제인데, 모든 쌍을 대보면 O(n²)이라
 * 해시맵으로 O(n)으로 만들었다. 핵심은 '현재 원소의 짝은 목표 빼기 현재 값'이라는
 * 역발상이다 — 쌍을 찾는 대신, 각 원소가 필요로 하는 값이 이미 지나갔는지를 조회한다.
 * 맵에는 값을 키, 인덱스를 값으로 넣되 반드시 조회 후에 삽입하는데, 이 순서 하나로
 * 세 가지가 보장된다: 대조 시점의 맵에 과거만 있어 자기 자신과의 합이 차단되고,
 * 찾은 인덱스가 항상 현재보다 앞서 i < j 조건이 자동 충족되며, 값이 같은 다른
 * 원소끼리의 합은 허용된다.
 * 빈 배열과 원소 하나는 루프가 쌍을 못 만들어 -1 -1로 떨어진다.
 * 순회 O(n)에 맵 조회 O(1)이라 전체 O(n), 추가 공간 O(n)이고 n이 1000이라 충분하다.
 */
fun findTaskPairForSlot(taskDurations: Array<Int>, slotLength: Int): Array<Int> {
    val seen = HashMap<Int, Int>()          // 값 → 인덱스

    for ((idx, duration) in taskDurations.withIndex()) {
        val need = slotLength - duration
        val found = seen[need]
        if (found != null) return arrayOf(found, idx)   // found < idx 자동 보장
        seen[duration] = idx                // 조회 후 삽입 — 순서가 정확성
    }
    return arrayOf(-1, -1)
}

fun main() {
    check(findTaskPairForSlot(arrayOf(2, 7, 11, 15), 9).toList() == listOf(0, 1))
    check(findTaskPairForSlot(arrayOf(1, 2, 3, 4), 8).toList() == listOf(-1, -1))
    check(findTaskPairForSlot(arrayOf(), 10).toList() == listOf(-1, -1))
    check(findTaskPairForSlot(arrayOf(5), 5).toList() == listOf(-1, -1))
    println("all passed")
}

main()
