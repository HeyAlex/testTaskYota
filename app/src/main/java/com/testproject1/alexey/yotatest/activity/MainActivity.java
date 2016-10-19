package com.testproject1.alexey.yotatest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.testproject1.alexey.yotatest.R;
import com.testproject1.alexey.yotatest.callback.IMainView;
import com.testproject1.alexey.yotatest.callback.MainCallback;
import com.testproject1.alexey.yotatest.presenter.MainPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainView{

    private MainPresenter mPresenter;

    @OnClick(R.id.buttonGo)
    public void pressDownload(){
        mPresenter.getData(urlText.getText().toString());
    }

    @Bind(R.id.resultText)
    TextView resultText;

    @Bind(R.id.editTextUrl)
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
    }

    @Override
    public void downloadData() {

    }

    @Override
    public void OnBadUrl() {

    }

    @Override
    public void OnEmptyData() {

    }

    @Override
    public void DisplayResult(String data) {
        resultText.setText(data);
    }

    @Override
    public void InteruptDownloading() {

    }
}
