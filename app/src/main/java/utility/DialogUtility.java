package utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.app.techsmartsolutions.R;


/**
 * This is utility class for all Dialog related functionality.

 */
public class DialogUtility
{

    private static final String TAG = "DialogUtility";
    private static Dialog mProgressDialog;

    /**
     * Listener for Dialog button click events.
     */
    public interface OnDialogClickListener
    {
        void onPositiveButtonClick();
        void onNegativeButtonClick();
    }

    /**
     * This method will show the dialog.
     *
     * @param context Context
     * @param title Title
     * @param description Description
     * @param listener OnDialogClickListener
     */
    public static void showDialog(Context context, String title, String description, String positiveButtonText, String negativeButtonText, final OnDialogClickListener listener)
    {
        if(!((Activity)context).isFinishing())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title).setMessage(description).setCancelable(false);
            builder.setPositiveButton(positiveButtonText,
                    new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (listener != null)
                                listener.onPositiveButtonClick();
                        }
                    });
            builder.setNegativeButton(negativeButtonText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                            if (listener != null)
                                listener.onNegativeButtonClick();
                        }
                    });
            builder.show();
        }
    }

    public static void showAlert(Context context, String title, String description)
    {
        if(!((Activity)context).isFinishing())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title).setMessage(description).setCancelable(false);
            builder.setPositiveButton(context.getString(R.string.ok),
                    new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
    }

    public static void showProgressDialog(Context context,boolean center) {
        try {
            if ((mProgressDialog != null && !mProgressDialog.isShowing()) || mProgressDialog == null) {
                if (center) {
                    mProgressDialog = new CustomProgressDialog(context,
                            android.R.style.Theme_Dialog, center);
                } else {
                    mProgressDialog = new CustomProgressDialog(context,
                            android.R.style.Theme_Dialog);
                }

                mProgressDialog.show();
            }
        } catch (Exception e) {
            Logger.e(TAG, "showCustomProgressDialog:" + e);

        }
    }

    public static void cancelProgressDialog() {

        try {
            if (mProgressDialog != null) {
                mProgressDialog.cancel();
            }
        } catch (Exception e) {
            Logger.e(TAG, "cancelProgressDialog:" + e);
        }
    }

}
