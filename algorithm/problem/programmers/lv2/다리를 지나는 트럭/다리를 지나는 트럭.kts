/**
 * 프로그래머스 Level 2 — 다리를 지나는 트럭
 *
 * 큐를 이용해서 다리를 시뮬레이션한다. 다리의 한 칸을 큐의 원소 하나로 표현하고,
 * 트럭이 없는 칸은 0으로 둔다.
 *
 * 매 초마다 removeFirst()를 해서 다리에서 빠지는 트럭의 무게를 현재 무게에서
 * 빼준다. 그다음 대기 중인 첫 번째 트럭을 확인해서 현재 무게와 합쳤을 때 제한
 * 무게 이하라면 다리에 올리고, 그렇지 않으면 이번 초에는 트럭을 올리지 않고
 * 0을 넣는다.
 *
 * 대기 중인 트럭이 모두 다리에 올라가면 반복을 종료하고, 마지막 트럭이 다리를
 * 완전히 건너는 데 필요한 bridge_length를 더해서 반환한다.
 *
 * 큐의 크기는 트럭 개수가 아니라 다리 길이를 의미하기 때문에 처음에
 * bridge_length만큼 0으로 초기화한다.
 */
class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val wait = truck_weights.toCollection(ArrayDeque())

        val bridge = ArrayDeque<Int>()
        repeat(bridge_length) {
            bridge.addLast(0)
        }
        var time = 0

        // 다리에 올라가 있는 트럭들 무게의 총 합
        var curW = 0

        while (wait.isNotEmpty()) {
            time++
            val out = bridge.removeFirst()
            curW -= out

            if (curW + wait.first() <= weight) {
                val truck = wait.removeFirst()
                curW += truck
                bridge.addLast(truck)
            } else {
                bridge.addLast(0)
            }
        }

        return time + bridge_length
    }
}

fun main() {
    val s = Solution()
    check(s.solution(2, 10, intArrayOf(7, 4, 5, 6)) == 8)
    check(s.solution(100, 100, intArrayOf(10)) == 101)
    check(s.solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)) == 110)
    println("all passed")
}

main()
