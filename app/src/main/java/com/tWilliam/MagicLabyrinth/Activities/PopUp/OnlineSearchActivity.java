package com.tWilliam.MagicLabyrinth.Activities.PopUp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.Toast;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TGameThread.TInitThread;

public class OnlineSearchActivity extends Activity {
    private final int PERMISSION_REQUEST_INTERNET = 100001;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_online_search_popup);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if ( permssionCheck != PackageManager.PERMISSION_GRANTED ){
            if ( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
            }
        }

        final TInitThread initThread = new TInitThread();
        initThread.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
