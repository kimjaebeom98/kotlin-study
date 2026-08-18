/**
 * HackerRank Easy — Find the Smallest Missing Positive Integer
 *
 * 답이 될 수 있는 범위부터 좁혔다. 원소가 n개뿐이라 1부터 n이 전부 있어도 답은 n+1이고,
 * 하나라도 빠지면 1~n 안에 있다. 그래서 -10^9~10^9라는 값 범위와 무관하게, 1~n 밖의
 * 값은 전부 무시해도 된다. 크기 n+1짜리 출석 배열에 1~n 범위의 값만 체크하고,
 * 1부터 훑어 처음 비어 있는 수를 반환한다. 끝까지 다 차 있었다면 그 자체가 1~n이
 * 완전하다는 뜻이므로 n+1을 반환한다. 중복은 같은 칸에 두 번 체크될 뿐이고,
 * 빈 배열은 두 루프 모두 돌지 않아 n+1=1이 나온다.
 * 시간 O(n), 추가 공간 O(n)이고 n이 1000이라 충분하다. 지문의 O(1) 공간 조건까지
 * 맞추려면 배열 자신을 출석부로 쓰는 사이클 정렬이 있는데, 15분 제약에서는 검증이
 * 쉬운 이쪽을 택하고 그 방법의 존재를 언급하는 게 낫다고 판단했다.
 */
fun findSmallestMissingPositive(orderNumbers: Array<Int>): Int {
    val n = orderNumbers.size
    val chk = BooleanArray(n + 1)          // 인덱스 1..n 사용

    for (num in orderNumbers) {
        if (num in 1..n) chk[num] = true   // 범위 밖(음수, 0, n 초과)은 소음 → 무시
    }
    for (num in 1..n) {
        if (!chk[num]) return num          // 처음 비는 수가 답
    }
    return n + 1                           // 끝까지 다 참 = 1..n 완전 → 답은 n+1
}

fun main() {
    check(findSmallestMissingPositive(arrayOf(3, 4, -1, 1)) == 2)
    check(findSmallestMissingPositive(arrayOf()) == 1)
    check(findSmallestMissingPositive(arrayOf(1, 1)) == 2)
    println("all passed")
}

main()
