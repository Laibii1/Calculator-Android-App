package com.example.calculatorapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;     // imported rom build.gradle to calculate result
import org.mozilla.javascript.Scriptable;  // imported rom build.gradle to calculate result

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // variable creation
    TextView Result_Textview,Solution_Textview;
    MaterialButton ButtonAC,ButtonPoint,ButtonC,ButtonOpenBracket,ButtonCloseBracket;
    MaterialButton ButtonDivide, ButtonMultiply, ButtonAdd, ButtonSubtract, ButtonEqual;
    MaterialButton Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,
            Button8,Button9;



    // to assign id to textview and buttons from xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result_Textview= findViewById(R.id.result_textView);
        Solution_Textview= findViewById(R.id.solution_textView);
        // getting button id by calling function
        getId(ButtonC,R.id.button_c);
        getId(ButtonAC,R.id.button_AC);
        getId(ButtonPoint,R.id.button_point);
        getId(ButtonOpenBracket,R.id.button_OpenBracket);
        getId(ButtonCloseBracket,R.id.button_CloseBracket);
        getId(ButtonDivide,R.id.button_divide);
        getId(ButtonMultiply,R.id.button_multiply);
        getId(ButtonAdd,R.id.button_add);
        getId(ButtonSubtract,R.id.button_subtract);
        getId(ButtonEqual,R.id.button_equal);
        getId(Button0,R.id.button_0);
        getId(Button1,R.id.button_1);
        getId(Button2,R.id.button_2);
        getId(Button3,R.id.button_3);
        getId(Button4,R.id.button_4);
        getId(Button5,R.id.button_5);
        getId(Button6,R.id.button_6);
        getId(Button7,R.id.button_7);
        getId(Button8,R.id.button_8);
        getId(Button9,R.id.button_9);

    }

        // function to get buttons id
    void getId(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
    MaterialButton button= (MaterialButton) view;
    String ButtonText= button.getText().toString();
    String dataToCalculate= Solution_Textview.getText().toString();
    // AC,=, and C features
    if(ButtonText.equals("AC")){
        Solution_Textview.setText("");
        Result_Textview.setText("0");
        return;
    }
    if(ButtonText.equals("=")){
        Solution_Textview.setText(Result_Textview.getText());
        return;
    }
    if(ButtonText.equals("C")){
        dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
    }else{
        dataToCalculate= dataToCalculate+ButtonText;
    }
    // Showing result in Soultion textview
    Solution_Textview.setText(dataToCalculate);

    String finalResult = CalculatedResult(dataToCalculate);
    if(!finalResult.equals("ERROR")){
        Result_Textview.setText(finalResult);
    }
    }
    // calculated results by library added in build.gradle
    String CalculatedResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,
                    "javascript", 1,null).toString();
            // removing unrequired .0 from calculated results
            if(finalResult.endsWith(".0")){
                finalResult= finalResult.replace(".0","");
            }

            return finalResult;
        }catch (Exception e){
            return "ERROR";
        }
    }



}