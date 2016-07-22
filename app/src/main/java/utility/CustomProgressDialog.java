package utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.app.techsmartsolutions.R;

/**
 *
 */
public class CustomProgressDialog extends Dialog {

    private boolean center;
    private String mMessage;


    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        center = false;
    }

    public CustomProgressDialog(Context context, int theme, String mesg) {
        super(context, theme);
        mMessage = mesg;
    }

    public CustomProgressDialog(Context context, int theme, boolean center) {
        super(context, theme);
        this.center = center;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (center) {
            setContentView(R.layout.custom_progress_dialog_center);
        } else {
            setContentView(R.layout.custom_progress_dialog);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}

