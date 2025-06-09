package com.example.unitymodule
import android.content.Context
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import kotlin.random.Random

class matplot(context:Context) {

    val _context = context

    fun getBytes(): ByteArray {
//        Python.start(AndroidPlatform(_context))
        val py = Python.getInstance()
        val pyObject = py.getModule("test")

//            val obj = pyObject.callAttr("add", 2.7, 3.9)
        val bytes = pyObject.callAttr("test_plot").toJava(ByteArray::class.java)
        return bytes
//        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//        Log.d("matlplot", "bitmap: ${bitmap}")

    }

    fun test_filtering_with_java_data(): PyObject{
        val py = Python.getInstance()
        val eeg = createRandomDoubleArrayArray(8, {3000}, 0.0, 50.2)

        val obj = py.getModule("test").callAttr(
            "test_filtering_with_java_data", eeg
        )

        print(obj.toString())
        return obj
    }


    fun createRandomDoubleArrayArray (
        outerSize: Int, // 외부 배열의 크기
        innerSizeProvider: (Int) -> Int, // 각 내부 배열의 크기를 제공하는 함수 (인덱스 기반)
        minRandomValue: Double = 0.0, // 랜덤 값의 최소값 (기본값 0.0)
        maxRandomValue: Double = 1.0 // 랜덤 값의 최대값 (기본값 1.0)
    ): Array<DoubleArray> {
        // 입력 값 유효성 검사 (선택 사항이지만 권장)
        require(outerSize >= 0) { "Outer size must be non-negative." }
        require(minRandomValue <= maxRandomValue) { "Min random value cannot be greater than max random value." }

        // 1. 외부 배열 생성
        val resultArray = Array(outerSize) { outerIndex ->
            // 2. 각 내부 배열의 크기 결정
            val currentInnerSize = innerSizeProvider(outerIndex)
            require(currentInnerSize >= 0) { "Inner size for index $outerIndex must be non-negative." }

            // 3. 내부 DoubleArray 생성 및 랜덤 값으로 채우기
            DoubleArray(currentInnerSize) {
                // 4. 지정된 범위 내에서 랜덤 Double 값 생성
                Random.nextDouble(minRandomValue, maxRandomValue)
            }
        }
        return resultArray
    }

}