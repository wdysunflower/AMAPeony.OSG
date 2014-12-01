package com.peony.osg.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.peony.osg.R;
import com.peony.osg.model.object.Catalog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class TxtReaderActivity extends Activity {

    private Catalog mCatalog;
    private TextView mTXTFileText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_reader);

        mCatalog = (Catalog) getIntent().getSerializableExtra("catalog");

        initView();
        initFile();
    }

    private void initView() {
        TextView titleTV = (TextView) findViewById(R.id.main_title_tv);
        titleTV.setText(mCatalog.Title);

        mTXTFileText = (TextView) findViewById(R.id.txt_file_text);
    }

    private void initFile() {
        try {
            InputStreamReader isr =
                    new InputStreamReader(getAssets().open(mCatalog.getFilename()), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            isr.close();
            mTXTFileText.setText(sb.toString());
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
    }

    public void onBackPress(View v) {
        this.finish();
    }
}
