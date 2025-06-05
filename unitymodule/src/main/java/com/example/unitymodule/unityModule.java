package com.example.unitymodule;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.unity3d.player.UnityPlayer;

public class unityModule
{
    private static unityModule _instance;
    private static Activity _context;

    public static unityModule instance()
    {
        if (_instance == null) {
            _instance = new unityModule();
            _context = UnityPlayer.currentActivity;
        }
        return _instance;
    }

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
            PyObject pyObject = py.getModule("test");

            PyObject obj = pyObject.callAttr("add", 2.7,3.9);


            Log.d("unityModule", obj.toString());
        }
    }
}