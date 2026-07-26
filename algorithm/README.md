# 🧮 algorithm — 알고리즘 / 문제 풀이

PS(코딩테스트)를 코틀린으로 풀 때 필요한 것들.

## 📝 문제 풀이

| 난이도 | 문제 | 풀이 | 핵심 |
|:--:|---|---|---|
| Lv.1 | [두 정수 사이의 합](programmers/level1/두%20정수%20사이의%20합/) | [해설](programmers/level1/두%20정수%20사이의%20합/Analysis.md) · [코드](programmers/level1/두%20정수%20사이의%20합/Solution.kt) | 등차수열 합 O(1), `Int` 오버플로우 |

### 문제 폴더 구성

문제 하나당 폴더 하나. 세 파일을 같은 형식으로 둔다.

```
programmers/level1/두 정수 사이의 합/
├── README.md      문제 원문 + 풀이 링크
├── Analysis.md    해설 — 문제 분석, 함정, 다른 풀이, 복잡도
└── Solution.kt    제출한 답안 + 경계값 테스트
```

---

## 📚 개념 정리

여러 문제에서 반복해 쓰는 알고리즘·공식은 [concepts/](concepts/)에 한 번만 정리하고, 각 문제 해설에서 링크한다.

| 문서 | 내용 |
|---|---|
| [등차수열](concepts/arithmetic-sequence.md) | 일반항 · 항의 개수 · 합 공식 O(1), `step` / `IntProgression`, 오버플로우 함정 |
