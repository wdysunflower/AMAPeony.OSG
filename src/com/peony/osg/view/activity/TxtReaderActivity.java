package com.peony.osg.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    public interface OnChangePageListener {
        public void setReader(TxtReaderActivity reader);

        public void prePage();

        public void nextPage();
    }

    private Catalog mCatalog;
    private TextView mTXTFileText;
    private View mPrePageBtn;
    private View mNextPageBtn;

    private OnChangePageListener mChangePagerL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_reader);

        mCatalog = (Catalog) getIntent().getSerializableExtra("catalog");

        initView();
        initFile();
    }

    private void initView() {
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

    public void onChangePage(View v) {
        switch (v.getId()) {
            case R.id.reader_pre_page:
                if (mChangePagerL != null) {
                    mChangePagerL.prePage();
                }
                break;
            case R.id.reader_next_page:
                if (mChangePagerL != null) {
                    mChangePagerL.nextPage();
                }
                break;
        }
    }

    public void setOnChangePagerListener(OnChangePageListener listener) {
        mChangePagerL = listener;
        mChangePagerL.setReader(this);
    }

    public void setBtnEnable(boolean pre, boolean next) {
        mPrePageBtn.setEnabled(pre);
        mNextPageBtn.setEnabled(next);
    }
}
