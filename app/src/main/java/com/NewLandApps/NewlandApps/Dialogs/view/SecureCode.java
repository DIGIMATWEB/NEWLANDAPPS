package com.NewLandApps.NewlandApps.Dialogs.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;

public class SecureCode extends DialogFragment implements View.OnClickListener{
    public static final String TAG = progressDialog.class.getSimpleName();
    private TextView txvTripsTitleLoader;
    private boolean hasTitle = false;
    private String title="";
    private Button verify;
    private EditText editText,editText2,editText3,editText4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar);
        hasTitle();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.securecode, container, false);
        txvTripsTitleLoader = view.findViewById(R.id.txvTripsTitleLoader);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparentalfa);
        setCancelable(false);

//        if (hasTitle) {
//            txvTripsTitleLoader.setVisibility(View.VISIBLE);
//            txvTripsTitleLoader.setText(title);
//        } else {
//            txvTripsTitleLoader.setVisibility(View.GONE);
//        }

//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (getDialog() != null && getDialog().isShowing()) {
//                    dismiss();
//                }
//            }
//        }, 18000); // 18 seconds
        initView(view);
        return view;
    }

    private void initView(View view) {
        editText=view.findViewById(R.id.editText);
        editText2=view.findViewById(R.id.editText2);
        editText3=view.findViewById(R.id.editText3);
        editText4=view.findViewById(R.id.editText4);
        verify= view.findViewById(R.id.verify);
        verify.setOnClickListener(this);
    }

    private void hasTitle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            hasTitle = bundle.getBoolean("HAS_TITLE");
            title=bundle.getString("title");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify:
                String v4=editText.getText().toString();
                String v3=editText2.getText().toString();
                String v2=editText3.getText().toString();
                String v1=editText4.getText().toString();
                String result=v1+v2+v3+v4;
                Log.e("codeAcces",result);
                if(result.equals("0591")){
                    SharedPreferences preferencias=getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putString(GeneralConstantsV2.VERIFICATIONCODE, result);

                    editor.commit();
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "Codigo incorrecto,verificalo con el departamento de R.H.", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
