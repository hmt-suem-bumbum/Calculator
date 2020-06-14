package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editNumber;
    TextView tvResult;
    Button btnNumber1;
    Button btnNumber2;
    Button btnNumber3;
    Button btnNumber4;
    Button btnNumber5;
    Button btnNumber6;
    Button btnNumber7;
    Button btnNumber8;
    Button btnNumber9;
    Button btnNumber0;

    Button btnCong;
    Button btnTru;
    Button btnNhan;
    Button btnChia;

    Button btnResult;
    Button btnClear;
    Button btnClearAll;
    Button btnPoint;
    final String Tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEvenClickViews();
    }

    public void initWidget() {
        editNumber = this.<EditText>findViewById(R.id.edt_input);
        tvResult = this.<TextView>findViewById(R.id.tv_result);
        btnNumber0 = (Button)findViewById(R.id.btnNumber0);
        btnNumber1 = (Button)findViewById(R.id.btnNumber1);
        btnNumber2 = (Button)findViewById(R.id.btnNumber2);
        btnNumber3 = (Button)findViewById(R.id.btnNumber3);
        btnNumber4 = (Button)findViewById(R.id.btnNumber4);
        btnNumber5 = (Button)findViewById(R.id.btnNumber5);
        btnNumber6 = (Button)findViewById(R.id.btnNumber6);
        btnNumber7 = (Button)findViewById(R.id.btnNumber7);
        btnNumber8 = (Button)findViewById(R.id.btnNumber8);
        btnNumber9 = (Button)findViewById(R.id.btnNumber9);

        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru = (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);

        btnResult = (Button)findViewById(R.id.btnResult);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClearAll = (Button)findViewById(R.id.btnClearAll);
        btnPoint = (Button)findViewById(R.id.btnPoint);
    }

    public void setEvenClickViews() {
        btnNumber0.setOnClickListener((View.OnClickListener) this);
        btnNumber1.setOnClickListener((View.OnClickListener) this);
        btnNumber2.setOnClickListener((View.OnClickListener) this);
        btnNumber3.setOnClickListener((View.OnClickListener) this);
        btnNumber4.setOnClickListener((View.OnClickListener) this);
        btnNumber5.setOnClickListener((View.OnClickListener) this);
        btnNumber6.setOnClickListener((View.OnClickListener) this);
        btnNumber7.setOnClickListener((View.OnClickListener) this);
        btnNumber8.setOnClickListener((View.OnClickListener) this);
        btnNumber9.setOnClickListener((View.OnClickListener) this);

        btnCong.setOnClickListener((View.OnClickListener) this);
        btnTru.setOnClickListener((View.OnClickListener) this);
        btnNhan.setOnClickListener((View.OnClickListener) this);
        btnChia.setOnClickListener((View.OnClickListener) this);

        btnResult.setOnClickListener((View.OnClickListener) this);
        btnClear.setOnClickListener((View.OnClickListener) this);
        btnClearAll.setOnClickListener((View.OnClickListener) this);
        btnPoint.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNumber0:
                editNumber.append("0");
                break;
            case R.id.btnNumber1:
                editNumber.append("1");
                break;
            case R.id.btnNumber2:
                editNumber.append("2");
                break;
            case R.id.btnNumber3:
                editNumber.append("3");
                break;
            case R.id.btnNumber4:
                editNumber.append("4");
                break;
            case R.id.btnNumber5:
                editNumber.append("5");
                break;
            case R.id.btnNumber6:
                editNumber.append("6");
                break;
            case R.id.btnNumber7:
                editNumber.append("7");
                break;
            case R.id.btnNumber8:
                editNumber.append("8");
                break;
            case R.id.btnNumber9:
                editNumber.append("9");
                break;
            case R.id.btnCong:
                editNumber.append("+");
                break;
            case R.id.btnTru:
                editNumber.append("-");
                break;
            case R.id.btnNhan:
                editNumber.append("*");
                break;
            case R.id.btnChia:
                editNumber.append("/");
                break;
            case R.id.btnResult:
//                int numberA = Integer.parseInt(String.valueOf(editNumber.getText()));
//                DecimalFormat df = new DecimalFormat("###.####");
                double result = 0;
                addOperation(editNumber.getText().toString());
                addNumber(editNumber.getText().toString());
                if(arrOperation.size()>=arrNumber.size()){
                    tvResult.setText("Lỗi");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++){
                        switch (arrOperation.get(i)){
                            case "+":
                                if (i == 0 ){
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                }else {
                                    result = result + arrNumber.get(i+1);
                                }
                                break;
                            case "-":
                                if (i == 0 ){
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                }else {
                                    result = result - arrNumber.get(i+1);
                                }
                                break;
                            case "*":
                                if (i == 0 ){
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                }else {
                                    result = result * arrNumber.get(i+1);
                                }
                                break;
                            case "/":
                                if (i == 0 ){
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                }else {
                                    result = result / arrNumber.get(i+1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText((result) + "");
                }
                Log.d(Tag, "onClick: "+result);
                break;
            case R.id.btnClear:
//                String numberAfterRemove = delete(editNumber.getText().toString());
//                editNumber.setText(numberAfterRemove);
                BaseInputConnection teBaseInputConnection = new BaseInputConnection(editNumber, true);
                teBaseInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));

                break;
            case R.id.btnClearAll:
                editNumber.setText("");
                tvResult.setText("");
                break;
            case R.id.btnPoint:
                editNumber.append(".");
                break;
        }
    }

    public String delete(String number){
        int length = number.length();
        String tem =  number.substring(0, length-1);
        return  tem;
    }
    //mảng chứa phép tính
    public ArrayList<String> arrOperation;
    //mảng chứa các số
    public ArrayList<Double> arrNumber;

    public int addOperation(String input){
        arrOperation = new ArrayList<>();
//        arrNumber = new ArrayList<>();
        char[] cArray = input.toCharArray();
        for (int i = 0; i< cArray.length; i++){
            switch (cArray[i]){
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput){
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }

}