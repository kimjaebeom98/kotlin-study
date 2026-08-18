/**
 * HackerRank Easy — Validate Properly Nested Brackets
 *
 * 닫힌 괄호는 가장 최근에 열린 괄호와 짝이어야 하는 구조라, 후입선출인 스택으로 풀었다.
 * 열린 괄호는 push하고, 닫힌 괄호가 오면 스택의 top과 대조하는데 — 스택이 비어 있거나
 * top과 종류가 다르면 그 닫힌 괄호는 영원히 짝을 찾을 수 없으므로 그 자리에서 즉시 false를 반환한다.
 * 순회가 끝난 뒤 스택에 남은 게 있다면 닫히지 못한 열린 괄호가 있다는 뜻이라, 스택이 비었는지가 곧 정답이다.
 * 괄호 아닌 문자는 건드리지 않으므로 괄호 없는 문자열과 빈 문자열은 자연히 true가 된다.
 * 각 문자는 한 번 처리되고 각 괄호는 최대 한 번 push, 한 번 pop되므로 전체 O(n), n이 1000이라 충분하다.
 */
fun areBracketsProperlyMatched(code_snippet: String): Boolean {
    val stk = ArrayDeque<Char>()

    for (o in code_snippet) {
        when (o) {
            '(', '{', '[' -> stk.addLast(o)
            ')', '}', ']' -> {
                if (stk.isEmpty()) return false
                val c = stk.removeLast()
                val matched = (o == ')' && c == '(') ||
                              (o == '}' && c == '{') ||
                              (o == ']' && c == '[')
                if (!matched) return false
            }
        }
    }
    return stk.isEmpty()
}

fun main() {
    check(areBracketsProperlyMatched("if (a[0] > b[1]) { doSomething(); }"))
    check(areBracketsProperlyMatched("int x = 42; // no brackets here"))
    check(areBracketsProperlyMatched("() {} []"))
    check(areBracketsProperlyMatched(""))
    check(!areBracketsProperlyMatched("(]"))
    check(!areBracketsProperlyMatched("([)]"))
    check(!areBracketsProperlyMatched("("))
    check(!areBracketsProperlyMatched(")"))
    println("all passed")
}

main()
