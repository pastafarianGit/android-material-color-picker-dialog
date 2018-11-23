package com.pes.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

public class MainActivity extends Activity implements ColorPickerCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ColorPicker colorPicker = new ColorPicker(
                MainActivity.this, // Context
                255, // Default Alpha value
                127, // Default Red value
                123, // Default Green value
                67 // Default Blue value
        );
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(colorPicker.getWindow().getAttributes());
        int dialogWindowWidth = (int) (500 * 0.7f);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (500 * 0.7f);
        Log.d(TAG, "display height: " + dialogWindowHeight + " display width; " +dialogWindowWidth);
        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        colorPicker.getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_main);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPicker.show();
            }
        });

        colorPicker.enableAutoClose();

        /* One way to get values from the Color Picker is to implement the Callback as an
        anonymous inner class. The setCallback method may also be used with "this" if the class
        implements the Callback (see below).
         */
        colorPicker.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int color) {
                Log.d("Alpha", Integer.toString(Color.alpha(color)));
                Log.d("Red", Integer.toString(Color.red(color)));
                Log.d("Green", Integer.toString(Color.green(color)));
                Log.d("Blue", Integer.toString(Color.blue(color)));

                Log.d("Pure Hex", Integer.toHexString(color));
                Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
                Log.d("#Hex with alpha", String.format("#%08X", (0xFFFFFFFF & color)));
            }
        });

    }

    /**
     * One way to get values from the Color Picker is by implementing the
     * {@link ColorPickerCallback} on a class level, as can be seen here.
     *
     * @param color Color chosen
     */
    @Override
    public void onColorChosen(@ColorInt int color) {
        Log.d("Alpha", Integer.toString(Color.alpha(color)));
        Log.d("Red", Integer.toString(Color.red(color)));
        Log.d("Green", Integer.toString(Color.green(color)));
        Log.d("Blue", Integer.toString(Color.blue(color)));
    }
}
