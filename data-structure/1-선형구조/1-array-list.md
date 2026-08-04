# 1.1 배열 · 리스트

## 정적 배열과 동적 배열

정적 배열은 크기가 고정이고, 메모리에 연속으로 배치되기 때문에 인덱스 접근이 O(1)이다.
코틀린의 `ArrayList`(`mutableListOf()`가 JVM에서 실제로 만드는 것)는 내부에 이 고정 크기 배열을 하나 들고 있다가,
꽉 차면 더 큰 배열(대략 2배)을 새로 만들어 기존 원소를 복사하는 방식으로 "가변 크기"처럼 동작한다.

이때 매번 복사가 일어나는 건 아니라서, `add()`를 n번 했을 때 전체 복사 비용의 합은 O(n)이고 평균적으로 한 번의 `add()`는 O(1)이다
반대로 꽉 찰 때마다 딱 1칸씩만 늘렸다면 복사 비용의 합이 `0+1+2+...+(n-1)`, 즉 O(n²)이 되어 평균 O(n)으로 훨씬 느려진다.

## MyArrayList\<T\> 설계

- **내부 저장소**: `Array<Any?>`. 코틀린은 제네릭 타입 파라미터 `T`로 직접 배열을 만들 수 없다 —
  클래스 몸통 코드는 딱 한 번만 컴파일되고 모든 타입 인스턴스가 그 코드를 공유하기 때문에, 컴파일 시점에 `T`가 정확히 몇 바이트짜리 타입인지 알 수 없다(타입 소거). 그래서 가장 넓은 타입 `Any`로 우회하고, 아직 채워지지 않은 칸을 표시할 값이 필요해서 `Any?`로 nullable을 허용한다.
- **`size`**: 실제로 채워진 원소 개수. `private set`을 붙여서 읽기는 클래스 밖에서도 자유롭게(`list.size`), 쓰기는 클래스 내부(`add()` 등)에서만 가능하게 캡슐화한다. 외부에서 `size`를 마음대로 바꿀 수 있으면 "size는 항상 실제 원소 개수와 같다"는 불변식이 깨질 수 있다.

## 구현

```kotlin
class MyArrayList<T> {
    private var array: Array<Any?> = arrayOfNulls(10) // 초기 용량 10

    var size: Int = 0
        private set

    fun add(value: T) {
        if (size == array.size) resize()
        array[size] = value
        size++
    }

    fun get(index: Int): T {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException()

        @Suppress("UNCHECKED_CAST")
        return array[index] as T
    }

    private fun resize() {
        val newArray: Array<Any?> = arrayOfNulls(array.size * 2)
        for (i in array.indices) newArray[i] = array[i]
        array = newArray
    }

    override fun toString(): String {
        val sb = StringBuilder("[")
        for (i in 0 until size) {
            sb.append(array[i])
            if (i != size - 1) sb.append(", ")
        }
        sb.append("]")
        return sb.toString()
    }
}
```

- `add()` — 꽉 찼으면(`size == array.size`) `resize()`를 먼저 부른 다음, `array[size]`에 넣고 `size`를 늘린다. 리사이즈 직후든 아니든 이 시점엔 `array[size]` 자리가 항상 비어있다는 게 보장된다.
- `get()` — 범위 체크 후 `Any?`로 저장돼있던 값을 `as T`로 캐스팅해서 반환한다. `add()`에서 항상 실제 `T` 값만 넣었다는 걸 알고 있으므로 이 캐스팅은 안전하고, 컴파일러의 unchecked cast 경고는 `@Suppress`로 끈다.
- `resize()` — 현재 크기의 2배짜리 새 배열을 만들고, 기존 원소를 복사한 뒤 참조를 교체한다.
- `toString()` — `Any`(모든 클래스의 조상)에 이미 정의된 `toString()`을 오버라이드하는 것. `array.size`(용량)가 아니라 `size`(실제 원소 수)만큼만 순회해야 빈 칸이 안 섞여 나온다. `Any`의 `toString()`/`equals()`/`hashCode()`는 기본이 `open`이라, 다른 함수와 달리 별도로 `open` 표시 없이도 오버라이드할 수 있다.

## 코틀린 표준 라이브러리 대응

- `Array<T>`는 고정 크기, `MutableList<T>`(JVM에서는 `ArrayList`)가 가변 크기 버전이다.
- `arrayListOf(...)`는 반환 타입이 `ArrayList<T>`(구체 클래스)이고, `mutableListOf(...)`는 반환 타입이 `MutableList<T>`(인터페이스)다. 둘 다 내부적으로 `ArrayList`를 쓰지만, 구현이 아니라 인터페이스에 의존하는 게 관용적이라 보통 `mutableListOf`를 쓴다.

## 남은 것

인터뷰 문제(배열 회전, 두 정렬 배열 병합, 중복 원소 제거)는 아직 안 풀었다 — 다음에 이어서.
