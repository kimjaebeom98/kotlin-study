/**
 * 프로그래머스 Level 2 — 프로세스
 *
 * 큐에서 꺼낸 프로세스를 실행할지 되돌릴지가 '남은 것 중 최고 우선순위인가'로
 * 결정되는 문제라, 실행 순서는 큐로, 최고 우선순위 조회는 max-heap으로 나눠 들었다.
 * 큐에는 우선순위와 원래 위치를 쌍으로 넣어 location 추적을 유지했고, 힙에는
 * 우선순위만 넣었다. 꺼낸 값이 힙의 peek보다 작으면 더 높은 게 남아 있다는
 * 뜻이라 큐 뒤로 되돌리고, 같으면 실행을 확정해 카운트를 올리고 힙에서도
 * 제거한다 — 대상 위치면 그 시점의 카운트가 답이다.
 * 힙 없이 매번 큐 전체에서 최댓값을 찾으면 실행마다 O(n)이라 전체 O(n²)인데,
 * 힙은 peek O(1), poll O(log n)이라 O(n log n)이다. 다만 n이 100이고
 * 우선순위가 1~9뿐이라 O(n²)도 통과되고, 힙 대신 크기 9 카운트 배열로도
 * 가능하다 — 여기서는 '흘러들며 매번 최댓값'이라는 신호에 맞는 힙을 택했다.
 * 되돌리기가 있어도 각 프로세스는 자기보다 높은 것들이 소진되면 반드시
 * 실행되므로 종료가 보장된다.
 */
import java.util.PriorityQueue

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()          // (우선순위, 원래 위치)
        val maxHeap = PriorityQueue<Int>(reverseOrder())

        for ((i, p) in priorities.withIndex()) {
            queue.addLast(p to i)
            maxHeap.add(p)                                 // 한 루프로 통합
        }

        var executed = 0
        while (queue.isNotEmpty()) {
            val (p, idx) = queue.removeFirst()
            if (p < maxHeap.peek()) {                      // 더 높은 게 남아있음 → 뒤로
                queue.addLast(p to idx)
            } else {                                       // 실행 확정
                executed++
                if (idx == location) return executed
                maxHeap.poll()
            }
        }
        return executed                                    // 도달 불가 (location은 항상 유효)
    }
}

fun main() {
    val s = Solution()
    check(s.solution(intArrayOf(2, 1, 3, 2), 2) == 1)
    check(s.solution(intArrayOf(1, 1, 9, 1, 1, 1), 0) == 5)
    println("all passed")
}

main()
