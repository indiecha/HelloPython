package com.example.unitymodule
import android.content.Context
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

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
}