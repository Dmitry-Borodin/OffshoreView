package com.two_two.offshoreview.json;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by marazm on 05.05.15.
 */
public class ProgressDialogForJson {
    private static ProgressDialog pDialog;

    public static void pDialogProgress(Context context){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
    }
    public static void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }
}
