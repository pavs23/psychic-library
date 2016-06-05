package com.pavs23.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;

import net.gouline.droidxing.CaptureActivity;
import net.gouline.droidxing.data.CaptureResult;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingScanButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Serializable codeResult = data.getSerializableExtra(CaptureActivity.EXTRA_CODE_RESULT);
            if (codeResult != null && codeResult instanceof CaptureResult) {
                CaptureResult codeResultBlock = (CaptureResult) codeResult;
                Result rawResult = codeResultBlock.getRawResult(); // Raw scan data
                ParsedResult parsedResult = codeResultBlock.getParsedResult(); // Parsed data

                makeToast(parsedResult.getDisplayResult());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingScanButton = (FloatingActionButton) findViewById(R.id.scan_button);

    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        floatingScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanningActivity();
            }
        });
    }

    private void startScanningActivity() {
        startActivityForResult(new Intent(this, CaptureActivity.class), 0);
    }


}
