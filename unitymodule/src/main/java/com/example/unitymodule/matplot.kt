package com.example.unitymodule

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class matplot(context:Context) {

    init {
        try {
            Python.start(AndroidPlatform(context))
            val py = Python.getInstance()
            val pyObject = py.getModule("test")

            val obj = pyObject.callAttr("add", 2.7, 3.9)
            val bytes = obj.callAttr("plot","x","y").toJava(ByteArray::class.java)

            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            Log.d("matlplot", "bitmap: ${bitmap}")

        } catch (e: PyException) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}