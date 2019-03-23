package dev.marviszevsky.myapplication;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.DigitsKeyListener;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class MainActivity extends AppCompatActivity {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button dot_btn;
    private Button equals_btn;
    private Button factorial_btn;
    private Button lbrac_btn;
    private Button rbrac_btn;
    private Button clear_btn;
    private Button allclear_btn;
    private Button ln_btn;
    private Button log_btn;
    private Button sqrt_btn;
    private Button xtoy_btn;
    private Button div_btn;
    private Button mult_btn;
    private Button minus_btn;
    private Button plus_btn;
    private Button ans_btn;
    private Button sin_btn;
    private Button cos_btn;
    private Button tan_btn;
    private Button e_btn;
    private Button arctg_btn;
    private Button pi_btn;
    private Button proc_btn;
    private Switch deg_rad;
    private TextView result;
    private TextView operation;
    private boolean isThereDot;
    private HorizontalScrollView scrollView;
    private StringBuilder resultBuilder = new StringBuilder("");
    private int numberCount = 0;
    private boolean cleanFromFuncChars = false;

    private void scrolltoBottom(){
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }


    private void automaticResize(){
       // if(result.getText().toString().length() == 9 || result.getText().toString().length() == 10 || result.getText().toString().length() == 13)
       // result.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

        //int[] tab = {44,20,10,12};
        if(numberCount%3==0)
            result.setText(String.format("%8.2s",result.getText()));
    }


    private  double factorial(double x){
        double facRes=1.0;
        if(x<=15) {
            for (int i = 1; i <= x; i++) {
                facRes = facRes * i;
            }
        }
        return facRes;
    }

    private double calculateExpression( String str){
        Double result = new DoubleEvaluator().evaluate(str);
        return Math.round(result*1e10)/1e10;
    }

    /*private double calculateExpression(final String str){
    return new Object() {
        int pos = -1, ch;

        void nextChar() {
            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
        }

        boolean eat(int charToEat) {
            while (ch == ' ') nextChar();
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        double parse() {
            nextChar();
            double x = parseExpression();
            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
            return x;
        }

        // Grammar:
        // expression = term | expression `+` term | expression `-` term
        // term = factor | term `*` factor | term `/` factor
        // factor = `+` factor | `-` factor | `(` expression `)`
        //        | number | functionName factor | factor `^` factor

        double parseExpression() {
            double x = parseTerm();
            for (;;) {
                if      (eat('+')) x += parseTerm(); // addition
                else if (eat('-')) x -= parseTerm(); // subtraction
                else return x;
            }
        }

        double parseTerm() {
            double x = parseFactor();
            for (;;) {
                if      (eat('*')) x *= parseFactor(); // multiplication
                else if (eat('/')) x /= parseFactor(); // division
                else return x;
            }
        }

        double parseFactor() {
            if (eat('+')) return parseFactor(); // unary plus
            if (eat('-')) return -parseFactor(); // unary minus

            double x;
            int startPos = this.pos;
            if (eat('(')) { // parentheses
                x = parseExpression();
                eat(')');
            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                x = Double.parseDouble(str.substring(startPos, this.pos));
            } else if (ch >= 'a' && ch <= 'z') { // functions
                while (ch >= 'a' && ch <= 'z') nextChar();
                String func = str.substring(startPos, this.pos);
                x = parseFactor();
                if (func.equals("sqrt")) x = Math.sqrt(x);
                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                else if (func.equals("arctg")) x = Math.atan(Math.toRadians(x));
                else if (func.equals("log")) x = Math.log10(x);
                else if (func.equals("ln")) x = Math.log(x);
                //else if (func.equals("pi")) x = Math.PI;
                //else if (func.equals("e")) x = Math.E
                else throw new RuntimeException("Unknown function: " + func);
            } else {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }

            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
            if (eat('!')) x = factorial(x); // exponentiation
            return x;
        }
    }.parse();
}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn0 = findViewById(R.id.buttonr0);
        btn1 = findViewById(R.id.buttonr1);
        btn2 = findViewById(R.id.buttonr2);
        btn3 = findViewById(R.id.buttonr3);
        btn4 = findViewById(R.id.buttonr4);
        btn5 = findViewById(R.id.buttonr5);
        btn6 = findViewById(R.id.buttonr6);
        btn7 = findViewById(R.id.buttonr7);
        btn8 = findViewById(R.id.buttonr8);
        btn9 = findViewById(R.id.buttonr9);
        dot_btn =findViewById(R.id.dot_button);
        equals_btn = findViewById(R.id.equals_button);
        factorial_btn = findViewById(R.id.factorial_button);
        lbrac_btn = findViewById(R.id.lbracket_button);
        rbrac_btn = findViewById(R.id.rbracket_button);
        clear_btn = findViewById(R.id.c_button);
        allclear_btn = findViewById(R.id.ac_button);
        ln_btn = findViewById(R.id.ln_button);
        log_btn = findViewById(R.id.log_button);
        sqrt_btn = findViewById(R.id.sqrt_button);
        xtoy_btn = findViewById(R.id.xtothepowerofy_button);
        div_btn = findViewById(R.id.div_button);
        mult_btn = findViewById(R.id.multiply_button);
        minus_btn = findViewById(R.id.minus_button);
        plus_btn = findViewById(R.id.plus_button);
        ans_btn = findViewById(R.id.lastanswer_button);
        sin_btn = findViewById(R.id.sin_button);
        cos_btn = findViewById(R.id.cos_button);
        tan_btn = findViewById(R.id.tan_button);
        e_btn = findViewById(R.id.e_button);
        arctg_btn = findViewById(R.id.arctg_button);
        pi_btn = findViewById(R.id.pi_button);
        proc_btn = findViewById(R.id.proc_button);

        result = findViewById(R.id.textView);
        operation = findViewById(R.id.textView2);
        isThereDot = false;
        scrollView = findViewById(R.id.lol);





        btn0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            result.setText(new StringBuilder().append(result.getText()).append(getString(R.string._0)).toString());
            resultBuilder.append("0");
            scrolltoBottom();
            }
        });

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string._1)));
                resultBuilder.append("1");
                scrolltoBottom();
            }
        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s2", result.getText()));
                scrolltoBottom();
                resultBuilder.append("2");
            }
        });

        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s3", result.getText()));
                scrolltoBottom();
                resultBuilder.append("3");
            }
        });

        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s4", result.getText()));
                scrolltoBottom();
                resultBuilder.append("4");
            }
        });

        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s5", result.getText()));
                scrolltoBottom();
                resultBuilder.append("5");
            }
        });

        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string._6)));
                scrolltoBottom();
                resultBuilder.append("6");
            }
        });

        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string._7)));
                scrolltoBottom();
                resultBuilder.append("7");
            }
        });

        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string._8)));
                scrolltoBottom();
                resultBuilder.append("8");
            }
        });

        btn9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string._9)));
                scrolltoBottom();
                resultBuilder.append("9");
            }
        });

        dot_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isThereDot == false) result.setText(String.format("%s%s", result.getText(), getString(R.string.dot)));
                isThereDot = true;
                scrolltoBottom();
                resultBuilder.append(".");
            }
        });

        equals_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(result.getText());
                isThereDot = false;
                scrolltoBottom();
                Double calcExp =0.0;
                try {
                    if (resultBuilder.length() != 0)
                        calcExp = calculateExpression(resultBuilder.toString());

                    /*if (calcExp % 1.0 != 0)
                        result.setText(String.format("%s", calcExp));
                    else
                        result.setText(String.format("%.0f",calcExp));

                    */result.setText(new Double(calcExp).toString());
                    System.out.print(resultBuilder.toString());
                    resultBuilder.setLength(0);
                    resultBuilder.append(result.getText());
                }
                catch (Exception e) {
                    result.setText("Error");
                }
            }
        });

        factorial_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), "!"));
                resultBuilder.append("!");
                scrolltoBottom();
            }
        });

        lbrac_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s", result.getText(), getString(R.string.left_brac)));
                resultBuilder.append("(");
                scrolltoBottom();
            }
        });

        rbrac_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.getText().toString().substring(result.getText().length()-1, result.getText().length()).equals("("))
                    result.setText(String.format("%s%s", result.getText(), getString(R.string.right_brac)));
                    resultBuilder.append(")");
                    scrolltoBottom();
            }
        });

        clear_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;

                if(result.getText().length() != 0 ) {
                    if (Character.isLetter(result.getText().charAt(result.getText().length() - 1))) {
                        for (int i = result.getText().length() - 1; i >= 0; i--) {
                            if (Character.isLetter(result.getText().charAt(i)) && cleanFromFuncChars == false)
                                count++;
                            else if (!(Character.isLetter(result.getText().charAt(i))))
                                cleanFromFuncChars = true;
                        }
                        result.setText(result.getText().toString().substring(0, result.getText().toString().length() - count));
                        resultBuilder.setLength(resultBuilder.length() - count);
                    }

                else {
                        result.setText(result.getText().toString().substring(0, result.getText().toString().length() - 1));
                        resultBuilder.setLength(resultBuilder.length() - 1);
                    }
                    isThereDot = false;
                    scrolltoBottom();
            }}
        });

        allclear_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                operation.setText("");
                resultBuilder.setLength(0);
                isThereDot = false;
            }
        });

        ln_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s", result.getText(), getString(R.string.ln), getString(R.string.left_brac)));
                scrolltoBottom();
                resultBuilder.append("ln(");
            }
        });

        log_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s", result.getText(), getString(R.string.log),getString(R.string.left_brac)));
                resultBuilder.append("log(");
                scrolltoBottom();
            }
        });

        sqrt_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s", result.getText(), getResources().getString(R.string.sqrt), getString(R.string.left_brac)));
                scrolltoBottom();
                resultBuilder.append("sqrt(");
            }
        });

        xtoy_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(String.format("%s%s",result.getText(),"^"));
                resultBuilder.append("^");
            }
        });

        div_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.div)));
                isThereDot = false;
                scrolltoBottom();
                resultBuilder.append("/");
            }
        });

        mult_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.multiply)));
                isThereDot = false;
                resultBuilder.append("*");
                scrolltoBottom();
            }
        });

        minus_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.minus)));
                isThereDot = false;
                resultBuilder.append("-");
                scrolltoBottom();
            }
        });

        plus_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.plus)));
                isThereDot = false;
                resultBuilder.append("+");
                scrolltoBottom();
            }
        });

        ans_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.ans)));
                resultBuilder.append(operation.getText());
                scrolltoBottom();
            }
        });

        sin_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s",result.getText(),getString(R.string.sin),getString(R.string.left_brac)));
                resultBuilder.append("sin(");
                scrolltoBottom();
            }
        });

        cos_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s",result.getText(),getString(R.string.cos),getString(R.string.left_brac)));
                resultBuilder.append("cos(");
                scrolltoBottom();
            }
        });

        tan_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s",result.getText(),getString(R.string.tan),getString(R.string.left_brac)));
                resultBuilder.append("tan(");
                scrolltoBottom();
            }
        });

        e_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.e)));
                resultBuilder.append("2.7182818284590452");
                scrolltoBottom();
            }
        });

        arctg_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s%s",result.getText(),getString(R.string.arctg),getString(R.string.left_brac)));
                resultBuilder.append("arctg(");
                scrolltoBottom();
            }
        });

        pi_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.pi)));
                resultBuilder.append("pi");
                scrolltoBottom();
            }
        });

        proc_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%s%s",result.getText(),getString(R.string.proc)));
                resultBuilder.append("/100");
                scrolltoBottom();
            }
        });



        int[] tab = {44,20,10,12};

        result.setHorizontallyScrolling(true);
        operation.setHorizontallyScrolling(true);




        //scrolltoBottom();




        //result.setSelected(true);
    }

}
