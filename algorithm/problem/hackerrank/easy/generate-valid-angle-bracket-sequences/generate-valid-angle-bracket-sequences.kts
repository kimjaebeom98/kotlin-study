/**
 * HackerRank Easy — Generate Valid Angle Bracket Sequences
 *
 * <와 >를 각각 n개씩 사용해서 올바른 괄호 문자열을 모두 생성하는 문제다.
 * DFS로 모든 경우를 탐색한다.
 *
 * 현재까지 사용한 여는 괄호와 닫는 괄호의 개수를 각각 open, close로 관리한다.
 * <는 open < n일 때 추가할 수 있고, >는 닫는 괄호가 여는 괄호보다 많아지면
 * 올바르지 않기 때문에 close < open일 때만 추가한다.
 *
 * 두 개수가 모두 n이 되면 하나의 유효한 문자열이 완성된 것이므로 결과에 추가한다.
 */
fun generateAngleBracketSequences(n: Int): Array<String> {
    val answer = mutableListOf<String>()
    fun dfs(s: String, open: Int, close: Int) {
        if (open == n && close == n) {
            answer.add(s)
            return
        }

        if (open < n)
            dfs(s + "<", open + 1, close)

        if (close < open)
            dfs(s + ">", open, close + 1)
    }
    dfs("", 0, 0)
    return answer.toTypedArray()
}

fun main() {
    check(generateAngleBracketSequences(1).toList() == listOf("<>"))
    check(generateAngleBracketSequences(2).toList() == listOf("<<>>", "<><>"))
    check(generateAngleBracketSequences(3).size == 5)
    check(generateAngleBracketSequences(0).toList() == listOf(""))
    println("all passed")
}

main()
