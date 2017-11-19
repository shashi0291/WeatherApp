package com.droid.us.myweatherapp.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.droid.us.myweatherapp.R;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class DialogUtility {

    /**
     * Method responsible to display UI messages using snack-bar.
     *
     * @param context context to show toast
     * @param view    root view
     * @param text    the messge to be displayed
     */
    /*public static void makeText(@NonNull Context context,
                                @NonNull View view,
                                @NonNull CharSequence text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        snackbar.show();
    }*/
}
