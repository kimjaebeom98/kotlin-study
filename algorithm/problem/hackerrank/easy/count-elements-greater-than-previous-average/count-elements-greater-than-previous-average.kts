/**
 * HackerRank Easy — Count Elements Greater Than Previous Average
 *
 * 이 문제는 각 원소를 '이전 원소들의 평균'과 비교하는 문제인데, 평균을 매번 다시 구하면
 * O(n²)이라 누적합을 유지해서 O(n)으로 처리했다. 평균은 합 나누기 개수니까,
 * 합만 갱신하면 매 시점 O(1)로 얻을 수 있다.
 * 주의한 점은 두 가지다. 첫째, 원소가 최대 10^9이고 n이 1000이라 합이 10^12까지
 * 가능해 Int를 넘으므로 누적합을 Long으로 뒀다. 둘째, `v > sum/idx` 비교를 직접
 * 나눗셈으로 하면 정수 나눗셈의 버림이나 부동소수점 오차가 생길 수 있어, 양변에 idx를
 * 곱해 `v*idx > sum` 형태의 정수 비교로 바꿨다 — idx > 0일 때만 비교하므로 0으로
 * 나누는 문제도 없다.
 * 빈 배열이면 루프가 돌지 않아 0이 반환되고, 첫 원소는 idx > 0 조건으로 건너뛴다.
 * 시간 O(n), 공간 O(1)이고 n이 1000이라 충분하다.
 */
fun countResponseTimeRegressions(responseTimes: Array<Int>): Int {
    var sum = 0L
    var count = 0

    for (idx in responseTimes.indices) {
        // v > sum/idx  ⟺  v*idx > sum (idx>0) — 나눗셈 제거로 부동소수점 오차 원천 차단
        if (idx > 0 && responseTimes[idx].toLong() * idx > sum) count++
        sum += responseTimes[idx]
    }
    return count
}

fun main() {
    check(countResponseTimeRegressions(arrayOf(100, 200, 150, 300)) == 2)
    check(countResponseTimeRegressions(arrayOf()) == 0)
    check(countResponseTimeRegressions(arrayOf(100)) == 0)
    println("all passed")
}

main()
