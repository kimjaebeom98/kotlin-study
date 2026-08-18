/**
 * HackerRank Easy — Min-Tracking Stack Implementation
 *
 * 이 문제는 스택의 기본 연산에 더해 최솟값 조회까지 전부 O(1)로 만들어야 하는 문제인데,
 * getMin에서 매번 전체를 훑으면 O(n)이라 값을 넣을 때 '그 시점까지의 최솟값'을 함께
 * 쌓아두는 방식으로 처리했다. i번째 칸의 최솟값은 새로 넣는 값과 바로 아래 칸의
 * 최솟값 중 작은 쪽이라 push 때 O(1)로 갱신되고, getMin은 맨 위 칸을 읽기만 하면 된다.
 * 주의한 점은 두 가지다.
 * 첫째, pop을 할 때 최솟값을 따로 복원할 필요가 없다는 점이다. 최솟값이 각 칸에
 * 종속돼 있어서 크기만 하나 줄이면 이전 시점의 최솟값이 그대로 노출된다. 별도의 min
 * 스택을 쓰는 방법도 있는데 그 경우엔 중복 최솟값 때문에 push 조건을 <=로 둬야 하는
 * 함정이 있어서, 값이 100 이하로 작고 push 횟수도 n 이하라 메모리 이점이 크지 않다고
 * 보고 실수 여지가 적은 쪽을 택했다.
 * 둘째, 입력 파싱인데 main이 readLine을 trim 없이 넘겨줘서 줄 끝 개행이나 공백이
 * 딸려올 수 있어 비교 전에 trim을 했다.
 * push 횟수가 n을 넘지 않는다고 보장되므로 IntArray(n) 두 개로 미리 잡아 재할당과
 * 박싱을 피했고, 문제에서 pop·top·getMin은 스택이 비어 있지 않을 때만 호출된다고
 * 보장하므로 언더플로 검사는 생략했다. 출력 길이는 top과 getMin 횟수에 따라 달라져서
 * 결과만 가변 리스트에 담았다. 연산당 O(1), 전체 시간 O(n) 공간 O(n)이다.
 */
fun processCouponStackOperations(operations: Array<String>): Array<Int> {
    val n = operations.size
    val values = IntArray(n)   // 스택에 쌓인 값
    val mins = IntArray(n)     // 같은 위치까지의 최솟값
    var size = 0               // 스택 크기 (top 인덱스 = size - 1)
    val result = ArrayList<Int>()

    for (raw in operations) {
        val op = raw.trim()
        when {
            op.startsWith("push") -> {
                val x = op.substring(4).trim().toInt()
                values[size] = x
                mins[size] = if (size == 0) x else minOf(x, mins[size - 1])
                size++
            }
            op == "pop" -> size--
            op == "top" -> result.add(values[size - 1])
            op == "getMin" -> result.add(mins[size - 1])
        }
    }

    return result.toTypedArray()
}

fun main() {
    check(
        processCouponStackOperations(
            arrayOf("push 2", "push 0", "push 3", "push 0", "getMin", "pop", "getMin", "pop", "top", "getMin")
        ).toList() == listOf(0, 0, 0, 0)
    )
    check(processCouponStackOperations(arrayOf("push 5", "getMin")).toList() == listOf(5))
    check(processCouponStackOperations(arrayOf("push 0", "top")).toList() == listOf(0))
    println("all passed")
}

main()
