package com.example.unitymodule;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.unity3d.player.UnityPlayer;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;

public class unityModule
{
    private static unityModule _instance;
    private static Activity _context;
    private byte[] tex_bytes;

    public static unityModule instance()
    {
        if (_instance == null) {
            _instance = new unityModule();
            _context = UnityPlayer.currentActivity;
        }
        return _instance;
    }

//    public byte[] getTex(){
//        return  tex_bytes;
//    }

    public void showToast(String text)
    {
        Log.d("unityModule", text);

        _context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(_context, text, Toast.LENGTH_LONG).show();
            }
        });

        // "context" must be an Activity, Service or Application object from your app.
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(_context));
            Python py = Python.getInstance();

            PyObject obj = py.getModule("test").callAttr("add", 2.7,3.9);
            Log.d("UnityModule", obj.toString());

            matplot _matplot = new matplot(_context);
            PyObject signal = _matplot.test_filtering_with_java_data();



//            PyObject obj = pyObject.callAttr("test");
//            PyObject obj = pyObject.callAttr("test").toJava(ByteArray::class.java);



//            matplot _matplot = new matplot(_context);
//            tex_bytes = _matplot.getBytes();


//            Log.d("unityModule", obj.toString());


        }
    }

    public byte[] getIcon(int i) {


        matplot _matplot = new matplot(_context);
        byte[] byteArray = _matplot.getBytes();


//        BitmapDrawable icon= (BitmapDrawable)_context.getPackageManager().getApplicationIcon("com.DefaultCompany.PythonTest");
//        Bitmap bmp = icon.getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}