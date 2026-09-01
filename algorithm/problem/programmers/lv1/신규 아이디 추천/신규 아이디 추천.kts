/**
 * 프로그래머스 Level 1 — 신규 아이디 추천
 *
 * 7단계 규칙을 그대로 순서대로 적용한다. 1~3단계(소문자화, 허용 문자만 남기기,
 * 연속 마침표 축약)는 문자를 한 번 훑으면서 한 루프로 합쳤다 — 허용되지 않는
 * 문자는 애초에 append하지 않으므로 연속 마침표 판정('.'인데 직전에 쓴 문자도
 * '.'이면 skip)이 필터링 이후의 문자열 기준으로 정확히 성립한다.
 * 4단계는 루프가 끝난 뒤 앞뒤 마침표를 제거하고, 5단계는 빈 문자열이면 "a"를
 * 넣는다. 6단계는 15자로 자른 뒤 끝이 마침표면 한 글자 더 잘라내고, 7단계는
 * 길이가 3 미만인 동안 마지막 문자를 반복해서 붙인다.
 */
class Solution {
    fun solution(new_id: String): String {
        val sb = StringBuilder()

        // 1~3단계: 소문자화 + 허용 문자만 남기기 + 연속 마침표 축약
        for (ch in new_id) {
            val c = ch.lowercaseChar()
            val allowed = c in 'a'..'z' || c in '0'..'9' ||
                          c == '-' || c == '_' || c == '.'
            if (!allowed) continue
            if (c == '.' && sb.isNotEmpty() && sb.last() == '.') continue
            sb.append(c)
        }

        // 4단계: 앞뒤 마침표 제거
        if (sb.isNotEmpty() && sb.last() == '.') sb.deleteCharAt(sb.length - 1)
        if (sb.isNotEmpty() && sb.first() == '.') sb.deleteCharAt(0)

        // 5단계
        if (sb.isEmpty()) sb.append('a')

        // 6단계
        if (sb.length >= 16) {
            sb.setLength(15)
            if (sb.last() == '.') sb.setLength(14)
        }

        // 7단계
        while (sb.length < 3) sb.append(sb.last())

        return sb.toString()
    }
}

fun main() {
    val s = Solution()
    check(s.solution("...!@BaT#*..y.abcdefghijklm") == "bat.y.abcdefghi")
    check(s.solution("z-+.^.") == "z--")
    check(s.solution("=.=") == "aaa")
    check(s.solution("123_.def") == "123_.def")
    check(s.solution("abcdefghijklmn.p") == "abcdefghijklmn")
    println("all passed")
}

main()
