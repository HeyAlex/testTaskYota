package com.testproject1.alexey.yotatest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
        enableState(false);
        mPresenter.getData(urlText.getText().toString());
    }

    @Bind(R.id.buttonGo)
    Button mButton;

    @Bind(R.id.resultText)
    TextView resultText;

    @Bind(R.id.error)
    TextView error;

    @Bind(R.id.editTextUrl)
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
        enableStateButton(false);
        urlText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.validateUrl(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                mPresenter.cancelTask();
            } catch (NullPointerException ex) {
            }
            enableState(true);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void OnEmptyData() {

      //  enableState(true);
    }




    @Override
    public void DisplayResult(String data) {
        resultText.setText(data);
       // enableState(true);
    }

    @Override
    public void InteruptDownloading() {
        mPresenter.cancelTask();
        enableState(true);
    }


    @Override
    public void enableState(boolean state) {
        mButton.setEnabled(state);
        urlText.setEnabled(state);
    }

    @Override
    public void enableStateButton(boolean state) {
        mButton.setEnabled(state);
    }

    @Override
    public void OnValidationError(String hint) {
        error.setText(hint);
    }

}
