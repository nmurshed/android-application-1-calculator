package com.niyaz.casioms_8l;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.text.DecimalFormat;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import android.text.TextUtils;


public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private TextView screen2;
    private TextView screen3;
    private String displayscreen="";
    private String displayscreen2="";
    private String currentoperator = "";
    private String memplus= "";
    private String memminus= "";
    private double total = 0.0;
    private static DecimalFormat df2 = new DecimalFormat("0.####");
    private static DecimalFormat df3 = new DecimalFormat("0.######E0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(TextView)findViewById(R.id.display);
        screen2=(TextView)findViewById(R.id.topdisplay) ;
        screen3=(TextView)findViewById(R.id.mstatus);
        screen.setText(displayscreen);

    }
    private void updatescreen(){
        screen.setText(displayscreen);
    } //Function to update top screen

    private void updatescreen2(){
        screen2.setText(displayscreen2);} //Function to update bottom screen

    public void onclickmemplus(View v){
        Button b = (Button)v;
        displayscreen=screen.getText().toString();
        displayscreen2=screen2.getText().toString();
        screen3.setText("M");                     // Displays M if there is a number stored in the memory.
        if (displayscreen.equals("") && displayscreen2.equals("") )
            return;
        else if (displayscreen.equals("") && !displayscreen2.equals("")){
            if (displayscreen2.contains("+") || displayscreen2.contains("-") || displayscreen2.contains("x") || displayscreen2.contains("÷")){
                return;}
            memplus =screen2.getText().toString();
            total+=Double.parseDouble(memplus);}
        else
        {
        memplus =screen.getText().toString();
        total+=Double.parseDouble(memplus);}
    }
    public void onclickmemminus(View v){
        Button b = (Button)v;
        displayscreen=screen.getText().toString();
        displayscreen2=screen2.getText().toString();
        if (displayscreen.equals("") && displayscreen2.equals("") )
            return;
        else if (displayscreen.equals("") && !displayscreen2.equals("")){
            if (displayscreen2.contains("+") || displayscreen2.contains("-") || displayscreen2.contains("x") || displayscreen2.contains("÷")){
                return;}
            memminus =screen2.getText().toString();
            total-=Double.parseDouble(memminus);}
        else
        {
            memminus =screen.getText().toString();
            total-=Double.parseDouble(memminus);}
    }
    public void onclickmrc(View v){    // memory recall button diplays the latest number in the memory.
        Button b = (Button)v;
        String t;
        t=df2.format(total);
        screen.setText(t);
    }

    public void onclicknumber(View v){
        Button b= (Button)v;
        String c = b.getText().toString();
        if (displayscreen.equals("") && displayscreen2.equals("")){
        if (c.charAt(0)=='.')return;}
        displayscreen2+=b.getText();
        updatescreen2();

    }


    public void onclickoperator(View v){
        Button b = (Button)v;
        displayscreen+=screen.getText();


        if (displayscreen.equals("")){               // If condition when 1st use
            displayscreen2=screen2.getText().toString();
            displayscreen2+=b.getText();
            currentoperator=b.getText().toString();
            updatescreen2();
        }


        else if (!displayscreen.equals("")) {        // If condition for when there is already a solution in the screen
            displayscreen2=screen.getText().toString();
            if (displayscreen2.charAt(0)=='-'||displayscreen2.charAt(0)=='+'){
                String s="0";
                displayscreen2=s+displayscreen2;}
            currentoperator=b.getText().toString();
            displayscreen2+=currentoperator;
            screen2.setText(displayscreen2);

        }
    }

    public void clear(){
        displayscreen="";
        displayscreen2="";
        currentoperator= "";
    }
    public void allclear(){
        displayscreen="";
        displayscreen2="";
        currentoperator= "";
        total = 0.0;
        screen3.setText("");
    }
    public void onclickc(View v){
        clear();
        updatescreen();
        updatescreen2();
    }
    public void onclickac(View v){
        allclear();
        updatescreen();
        updatescreen2();
    }
    public void onclickpercent(View v){             // Function to evaluate percentage of a number
        Button b = (Button) v;
        String temp;
        double t;
        String y;
        displayscreen = screen.getText().toString();
        displayscreen2 = screen2.getText().toString();
        if (displayscreen.equals("") && displayscreen2.equals(""))   //handle if display is empty
            return;
        else if (displayscreen.equals("") && !displayscreen2.equals("")) {
            temp = screen2.getText().toString();
            if (temp.contains("+") || temp.contains("-") || temp.contains("x") || temp.contains("÷")){  //handle if user inserts only operators
                return;}
            t = Double.valueOf(temp) /100.0;
            y=df2.format(t);
            screen.setText(y);
            String z="%";
            screen2.setText(temp+z);
        }
        else
            {
            temp = screen.getText().toString();
            t = Double.valueOf(temp) /100.0;
            y=df2.format(t);
            screen.setText(y);
            String z="%";
            screen2.setText(temp+z);
        }
    }
    public void onclicksqrt(View v) {
        Button b = (Button) v;
        String temp;
        double t;
        String y;
        displayscreen = screen.getText().toString();
        displayscreen2 = screen2.getText().toString();
        if (displayscreen.equals("") && displayscreen2.equals(""))
            return;
        else if (displayscreen.equals("") && !displayscreen2.equals("")) {
            temp = screen2.getText().toString();
            if (temp.contains("+") || temp.contains("-") || temp.contains("x") || temp.contains("÷")){
                return;}
            t = Math.sqrt(Double.valueOf(temp));
            y=df2.format(t);
            screen.setText(y);
            String z="√";
            screen2.setText(z+temp);
        } else {
            temp = screen.getText().toString();
            t = Math.sqrt(Double.valueOf(temp));
            y=df2.format(t);
            screen.setText(y);
            String z="√";
            screen2.setText(z + temp);
        }
    }


    public double operation( String a, String b, String o){     //Function for various operation
        switch (o){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "÷":try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.e("calc",e.getMessage());
            }

                default: return -1;
        }
    }
    public void onclickequal(View v){
        String temp;
        double result =0.0;
        if (displayscreen2.equals("") && displayscreen.equals(""))return;
        temp=screen2.getText().toString();
        String text = temp;
        if (temp.charAt(0)=='-'||temp.charAt(0)=='+'){
            String g="0";
            temp=g+temp;
        }

        List<String> operatorlist = new ArrayList<String>();   // Create array list and separate operators and operands
        List<String> operandlist = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(temp,"+-x÷",true);
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if ("+-x÷".contains(token)){
                operatorlist.add(token);
            }else {
                operandlist.add(token);
            }
        }
        String t;

        if (operandlist.size() <2) {        //handle wrong user inputs
            if (temp.charAt(0)=='x'||temp.charAt(0)=='÷')
            {
                allclear();
                updatescreen();
                updatescreen2();
                return;
            }
            else
            return;
        }
        if (operandlist.get(0).equals("0")){            // handle when negative number is inserted at beginning
            StringBuilder sb = new StringBuilder(text);
            sb.deleteCharAt(0);
            text=sb.toString();
        }


       if (operatorlist.size() >= operandlist.size()){ //handle multiple insertion of operators in between operands
           allclear();
           updatescreen();
           updatescreen2();
           return;}

        for (int i = 0; i<operandlist.size();i++){  //checks if there are multiple '.' in a number
         if (operandlist.get(i).length() - operandlist.get(i).replace(".", "").length()>1){
            allclear();
             updatescreen();
             updatescreen2();
            return;
        }}

        result = operation(operandlist.get(0), operandlist.get(1), operatorlist.get(0));
        for (int i = 1; i<operatorlist.size();i++) {

            result=operation(String.valueOf(result),operandlist.get(i+1),operatorlist.get(i));
        }
        if (String.valueOf(result).length()>9)
        t=df3.format(result);               // exponential formatting
        else t=df2.format(result);
        screen.setText(t);                  // General formatting
        String tempdisp=operandlist.get(0);
        for (int i = 1;i<operandlist.size();i++)
            tempdisp+=operatorlist.get(i-1)+operandlist.get(i);


        screen2.setText(text);
    }
}
