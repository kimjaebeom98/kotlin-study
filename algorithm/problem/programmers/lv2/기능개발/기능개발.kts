/**
 * 프로그래머스 Level 2 — 기능개발
 *
 * 뒤 기능이 앞 기능의 배포를 기다리는, 먼저 들어온 것부터 처리되는 구조라 큐로
 * 모델링했다. 먼저 각 기능의 완료 소요일을 계산하는데, 남은 진도를 속도로 나눠서
 * 딱 떨어지면 그 값, 나머지가 있으면 하루를 더했다 — 지문의 95%에 4%면 2일이
 * 걸린다는 예시가 이 올림 케이스다.
 * 이후 큐 앞에서 하나를 꺼내 이번 배포의 리더로 삼고, 큐 앞의 소요일이 리더 이하인
 * 동안 계속 꺼내 같은 배포로 묶어 센다 — 앞 기능이 배포될 때 이미 완성돼 기다리던
 * 뒤 기능들이 함께 나가는 것이다. 리더보다 오래 걸리는 기능을 만나면 묶음을
 * 확정하고, 그 기능이 다음 바퀴의 새 리더가 된다.
 * 바깥 루프가 '배포 한 번'에 대응해서 마지막 묶음도 같은 경로로 처리되므로 별도
 * 보정이 없다. 각 기능은 정확히 한 번 넣고 한 번 꺼내므로 이중 while이어도 전체
 * O(n)이고, n이 최대 100이라 즉시 끝난다.
 */
class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val queue = ArrayDeque<Int>()
        for (i in progresses.indices) {
            val remain = 100 - progresses[i]
            val day = if (remain % speeds[i] == 0) remain / speeds[i]
                      else remain / speeds[i] + 1
            queue.addLast(day)
        }

        val answer = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val leader = queue.removeFirst()
            var cnt = 1
            while (queue.isNotEmpty() && queue.first() <= leader) {
                queue.removeFirst()
                cnt++
            }
            answer.add(cnt)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val s = Solution()
    check(s.solution(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5)).toList() == listOf(2, 1))
    check(s.solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1)).toList() == listOf(1, 3, 2))
    println("all passed")
}

main()
