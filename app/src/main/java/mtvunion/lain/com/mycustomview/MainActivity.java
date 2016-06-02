package mtvunion.lain.com.mycustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mtvunion.lain.com.mycustomview.view.CustomView1;

public class MainActivity extends AppCompatActivity {
    private CustomView1 mCustomView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCustomView1 = (CustomView1) findViewById(R.id.customView1);
    }
}
