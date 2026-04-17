import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class PhoneResetService {

    private Context context;

    public PhoneResetService(Context context) {
        this.context = context;
    }

    public void promptForReset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Factory Reset");
        builder.setMessage("Are you sure you want to perform a factory reset? This action cannot be undone.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                performFactoryReset();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performFactoryReset() {
        // Intent to perform factory reset
        Intent intent = new Intent(Settings.ACTION_FACTORY_RESET);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}