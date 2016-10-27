package org.dalol.orthodoxmezmurmedia.modules.rate;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/27/2016
 */
public class RateDialog implements DialogInterface.OnClickListener {

    private final Activity activity;
    private String title;
    private String message;
    private String positiveButton;
    private String negativeButton;
    private String neutralButton;

    public RateDialog(Activity activity) {
        this.activity = activity;
    }

    public RateDialog withTitle(String title) {
        this.title = title;
        return RateDialog.this;
    }

    public RateDialog withMessage(String message) {
        this.message = message;
        return RateDialog.this;
    }

    public RateDialog withPositiveButtonLabel(String label) {
        this.positiveButton = label;
        return RateDialog.this;
    }

    public RateDialog withNegativeButtonLabel(String label) {
        this.negativeButton = label;
        return RateDialog.this;
    }

    public RateDialog withNeutralButtonLabel(String label) {
        this.neutralButton = label;
        return RateDialog.this;
    }

    public void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this.activity)
                .setTitle(this.title)
                .setMessage(this.message)
                .setPositiveButton(this.positiveButton, this)
                .setNegativeButton(this.negativeButton, this)
                .setNeutralButton(this.neutralButton, this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                handlePositiveAction();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                handleNegativeAction();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                handleNeutralAction();
                break;
        }
    }

    private void handleNeutralAction() {

    }

    private void handleNegativeAction() {

    }

    private void handlePositiveAction() {

    }
}
