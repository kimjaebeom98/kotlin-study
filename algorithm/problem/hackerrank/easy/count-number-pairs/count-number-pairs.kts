/**
 * HackerRank Easy — Count Number Pairs
 *
 * 두 원소의 합이 예산 이하인 쌍의 개수를 세는 문제다. 모든 쌍을 대보면 O(n²)이고
 * n이 1000이라 사실 통과되지만, 배열이 이미 정렬돼 있다는 조건이 있어 투 포인터로
 * O(n)이 가능해 그쪽을 택했다.
 * 양 끝에 포인터를 두고, 두 값의 합이 예산 이하이면 count에 right - left를 더한다
 * — 가장 작은 left와 가장 큰 right의 합이 성립하면, 정렬 덕에 left와 그 사이 모든
 * 원소의 쌍이 전부 성립하므로 한 번에 묶어 세는 것이다. 그리고 left를 전진시킨다.
 * 반대로 합이 초과라면 right를 버리는데, 남은 구간의 최솟값인 left와 더해도
 * 초과이므로 right가 만들 수 있는 쌍은 남은 구간에 존재하지 않기 때문이다 —
 * right가 이전 원소들과 만들던 쌍은 과거의 묶음 세기에 이미 포함됐다.
 * 두 값의 합은 최대 2×10^9로 Int 한계 2.1×10^9 안이라 그대로 뒀고, 쌍의 최대
 * 개수도 약 50만이라 count도 Int로 충분하다. n이 2 미만이면 루프 조건이 바로
 * 거짓이라 0이 나온다. 각 반복마다 포인터 하나가 반드시 전진해 전체 O(n),
 * n이 1000이라 즉시 끝난다.
 */
fun countAffordablePairs(prices: Array<Int>, budget: Int): Int {
    var left = 0
    var right = prices.lastIndex
    var count = 0

    while (left < right) {
        if (prices[left] + prices[right] <= budget) {
            count += right - left   // left와 (left+1 .. right) 전부가 쌍 — 정렬 덕에 묶음 세기
            left++
        } else {
            right--                 // 최솟값과도 초과 → right의 남은 쌍은 존재 불가, 버림
        }
    }
    return count
}

fun main() {
    check(countAffordablePairs(arrayOf(1, 2, 3, 4, 5), 7) == 8)
    check(countAffordablePairs(arrayOf(), 100) == 0)
    check(countAffordablePairs(arrayOf(5), 5) == 0)
    println("all passed")
}

main()
