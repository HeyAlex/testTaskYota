package com.testproject1.alexey.yotatest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.testproject1.alexey.yotatest.R;
import com.testproject1.alexey.yotatest.callback.IMainView;
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

        if (savedInstanceState != null){
            error.setText(savedInstanceState.getString("ERROR"));
            resultText.setText(savedInstanceState.getString("DATA"));
            urlText.setText(savedInstanceState.getString("URL"));
            enableStateButton(savedInstanceState.getBoolean("BUTTON_STATE"));
        }


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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("ERROR", error.getText().toString());
        outState.putString("DATA",resultText.getText().toString());
        outState.putString("URL",urlText.getText().toString());
        outState.putBoolean("BUTTON_STATE",mButton.isEnabled());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                InteruptDownloading();
            } catch (NullPointerException ex) {
                enableState(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }





    @Override
    public void DisplayResult(String data) {
        resultText.setText(data);
    }

    @Override
    public void InteruptDownloading() {
        mPresenter.cancelTask();
        enableStateButton(true);
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
    public void OnError(String hint) {
        error.setText(hint);
        enableState(true);
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
