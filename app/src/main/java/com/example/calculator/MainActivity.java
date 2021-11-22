package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText numberS;
    TextView operation;
    Double operand = null;
    String Equel = "=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        numberS = findViewById(R.id.numberS);
        operation = findViewById(R.id.operation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", Equel);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Equel = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operation.setText(Equel);
    }

    public void onNumberClick(View view){

        Button button = (Button)view;
        numberS.append(button.getText());

        if(Equel.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onOperationClick(View view){

        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberS.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberS.setText("");
            }
        }
        Equel = op;
        operation.setText(Equel);
    }

    private void performOperation(Double number, String operation){

        if(operand ==null){
            operand = number;
        }
        else{
            if(Equel.equals("=")){
                Equel = operation;
            }
            switch(Equel){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        result.setText(operand.toString().replace('.', ','));
        numberS.setText("");
    }
}
