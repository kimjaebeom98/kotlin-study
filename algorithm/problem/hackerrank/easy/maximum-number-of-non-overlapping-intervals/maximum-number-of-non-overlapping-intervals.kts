/**
 * HackerRank Easy — Maximum Number of Non-Overlapping Intervals
 *
 * 최대한 많은 회의를 넣는 문제라 그리디로 접근했다. 기준은 끝나는 시간 오름차순인데,
 * 일찍 끝나는 회의를 고를수록 남는 시간이 최대가 되어 이후 선택지가 줄어들 일이 없기 때문이다.
 * 시작 시간 기준은 [0,100] 같은 긴 회의를 먼저 잡아 실패하는 반례가 있다.
 * 정렬 후 순회하며 직전 선택의 끝 시간보다 시작이 같거나 늦은 회의만 채택했고,
 * 등호를 포함한 것은 끝나는 순간 시작하는 회의를 허용한다는 문제 조건 때문이다.
 * 빈 배열과 회의 1개는 정렬 전에 바로 반환했다. 정렬이 지배하므로 전체 O(n log n), n이 1000이라 충분하다.
 */
fun maximizeNonOverlappingMeetings(meetings: Array<Array<Int>>): Int {

    if (meetings.size <= 1) return meetings.size

    val sortMeetings = meetings.sortedBy { it[1] }
    var record = sortMeetings[0][1]
    var cnt = 1

    for (idx in 1..sortMeetings.size - 1) {
        if (record <= sortMeetings[idx][0]) {
            record = sortMeetings[idx][1]
            cnt++
        }
    }
    return cnt
}

fun main() {
    check(maximizeNonOverlappingMeetings(arrayOf(arrayOf(1, 2), arrayOf(2, 3), arrayOf(3, 4), arrayOf(1, 3))) == 3)
    check(maximizeNonOverlappingMeetings(arrayOf(arrayOf(0, 5), arrayOf(0, 1), arrayOf(1, 2), arrayOf(2, 3), arrayOf(3, 5), arrayOf(4, 6))) == 4)
    check(maximizeNonOverlappingMeetings(arrayOf(arrayOf(5, 10))) == 1)
    check(maximizeNonOverlappingMeetings(arrayOf(arrayOf(1, 2), arrayOf(2, 3), arrayOf(3, 4))) == 3)
    check(maximizeNonOverlappingMeetings(arrayOf()) == 0)
    println("all passed")
}

main()
