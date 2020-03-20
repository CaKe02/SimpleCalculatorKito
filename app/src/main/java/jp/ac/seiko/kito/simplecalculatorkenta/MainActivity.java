package jp.ac.seiko.kito.simplecalculatorkenta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewAnswer;
    private TextView mTextViewFormula;
    private Button mButton0;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonMultiply;
    private Button mButtonDivide;
    private Button mButtonEqual;
    private Button mButtonClear;

    int mProcessValue = 0;
    int mOperationValue = 0;
    int mNextOperationValue = 0;
    int mNumber = 0;
    String mFirstValue = "";
    String mFValueSymbol = "";
    String mSecondValue = "";


    private void init () {
        mProcessValue = 0;
        mOperationValue = 0;
        mNextOperationValue = 0;
        mNumber = 0;
        mFirstValue = "";
        mFValueSymbol = "";
        mSecondValue = "";
        mTextViewFormula.setText("");
        mTextViewAnswer.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewAnswer = findViewById(R.id.textView_answer);
        mTextViewFormula = findViewById(R.id.textView_formula);
        mButton0 = findViewById(R.id.button_0);
        mButton1 = findViewById(R.id.button_1);
        mButton2 = findViewById(R.id.button_2);
        mButton3 = findViewById(R.id.button_3);
        mButton4 = findViewById(R.id.button_4);
        mButton5 = findViewById(R.id.button_5);
        mButton6 = findViewById(R.id.button_6);
        mButton7 = findViewById(R.id.button_7);
        mButton8 = findViewById(R.id.button_8);
        mButton9 = findViewById(R.id.button_9);
        mButtonPlus = findViewById(R.id.button_plus);
        mButtonMinus = findViewById(R.id.button_minus);
        mButtonMultiply = findViewById(R.id.button_multiply);
        mButtonDivide = findViewById(R.id.button_divide);
        mButtonEqual = findViewById(R.id.button_equal);
        mButtonClear = findViewById(R.id.button_clear);

        Button[] allButtons = {
                mButton0,
                mButton1,
                mButton2,
                mButton3,
                mButton4,
                mButton5,
                mButton6,
                mButton7,
                mButton8,
                mButton9,
                mButtonPlus,
                mButtonMinus,
                mButtonMultiply,
                mButtonDivide,
                mButtonEqual,
                mButtonClear

        };
        for (Button button : allButtons) {
            button.setOnClickListener(this);
        }
    }

    private void Symbol() {
        switch (mOperationValue) {
            case 1://足し算
                mTextViewFormula.setText(mFirstValue + " +");
                break;
            case 2://引き算
                mTextViewFormula.setText(mFirstValue + " -");
                break;
            case 3://掛け算
                mTextViewFormula.setText(mFirstValue + " ×");
                break;
            case 4://割り算
                mTextViewFormula.setText(mFirstValue + " ÷");
                break;
        }
        mFValueSymbol = mTextViewFormula.getText().toString();
    }

    private void Answer() {
        String AnswerValue;
        int f = Integer.parseInt(mFirstValue);
        int s = Integer.parseInt(mSecondValue);
        switch (mOperationValue) {
            case 1://足し算
                AnswerValue = String.valueOf(f + s);
                mTextViewAnswer.setText(AnswerValue);
                break;
            case 2://引き算
                AnswerValue = String.valueOf(f - s);
                mTextViewAnswer.setText(AnswerValue);
                break;
            case 3://掛け算
                AnswerValue = String.valueOf(f * s);
                mTextViewAnswer.setText(AnswerValue);
                break;
            case 4://割り算
                AnswerValue = String.valueOf(f / s);
                mTextViewAnswer.setText(AnswerValue);
                break;
            default:
                mTextViewAnswer.setText(f);
        }
    }

    private void Process() {
        switch (mProcessValue) {
            case 1://最初の数値を決める
                if (mFirstValue.equals("0")) {
                    mFirstValue = String.valueOf(mNumber);
                } else {
                    mFirstValue = mFirstValue + mNumber;
                }
                mTextViewFormula.setText(mFirstValue);
                mProcessValue = 0;
                mTextViewAnswer.setText("");
                break;
            case 2://記号、待機
                if (mFirstValue.equals("")) {
                    mProcessValue = 0;
                } else {
                    Symbol();
                }
                break;
            case 3://二個目の数値を決める
                if (mSecondValue.equals("0")) {
                    mSecondValue = String.valueOf(mNumber);
                } else {
                    mSecondValue = mSecondValue + mNumber;
                }
                mTextViewFormula.setText(mFValueSymbol + " " +mSecondValue);
                mProcessValue = 2;
                break;
            case 4:
                if (mSecondValue.equals("")) {
                    Symbol();
                }
                mProcessValue = 2;
                break;
            case 5://答え
                Answer();
                break;
            case 6:
                mOperationValue = 0;
                mFirstValue = "";
                mFValueSymbol = "";
                mSecondValue = "";
                mTextViewFormula.setText("");
                mTextViewAnswer.setText("");
                mFirstValue = mFirstValue + mNumber;
                mTextViewFormula.setText(mFirstValue);
                mProcessValue = 0;
                break;
            case 7:
                mNumber = 0;
                mFValueSymbol = "";
                mSecondValue = "";

                mFirstValue = mTextViewAnswer.getText().toString();
                Symbol();
                mProcessValue = 2;
                break;
            case 8:
                Answer();

                mNumber = 0;
                mFValueSymbol = "";
                mSecondValue = "";

                mFirstValue = mTextViewAnswer.getText().toString();
                //Symbol();に似てる
                switch (mNextOperationValue) {
                    case 1://足し算
                        mTextViewFormula.setText(mFirstValue + " +");
                        break;
                    case 2://引き算
                        mTextViewFormula.setText(mFirstValue + " -");
                        break;
                    case 3://掛け算
                        mTextViewFormula.setText(mFirstValue + " ×");
                        break;
                    case 4://割り算
                        mTextViewFormula.setText(mFirstValue + " ÷");
                        break;
                }
                mFValueSymbol = mTextViewFormula.getText().toString();
                mOperationValue = mNextOperationValue;
                mTextViewAnswer.setText("");
                mProcessValue = 2;
                break;
            case 9:
                mTextViewAnswer.setText(mFirstValue);
                mTextViewFormula.setText(mFirstValue);
                mOperationValue = 0;
                mProcessValue = 5;
                break;
            default:
                init();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                mNumber = 0;
                mProcessValue +=  1;
                break;
            case R.id.button_1:
                mNumber = 1;
                mProcessValue += 1;
                break;
            case R.id.button_2:
                mNumber = 2;
                mProcessValue += 1;
                break;
            case R.id.button_3:
                mNumber = 3;
                mProcessValue += 1;
                break;
            case R.id.button_4:
                mNumber = 4;
                mProcessValue += 1;
                break;
            case R.id.button_5:
                mNumber = 5;
                mProcessValue += 1;
                break;
            case R.id.button_6:
                mNumber = 6;
                mProcessValue += 1;
                break;
            case R.id.button_7:
                mNumber = 7;
                mProcessValue += 1;
                break;
            case R.id.button_8:
                mNumber = 8;
                mProcessValue += 1;
                break;
            case R.id.button_9:
                mNumber = 9;
                mProcessValue += 1;
                break;
            case R.id.button_plus:
                if (mProcessValue == 2 && mSecondValue.equals("") == false) {
                    mNextOperationValue = 1;
                    mProcessValue = 8;
                } else {
                    mOperationValue = 1;
                    mProcessValue += 2;
                }
                break;
            case R.id.button_minus:
                if (mProcessValue == 2 && mSecondValue.equals("") == false) {
                    mNextOperationValue = 2;
                    mProcessValue = 8;
                } else {
                    mOperationValue = 2;
                    mProcessValue += 2;
                }
                break;
            case R.id.button_multiply:
                if (mProcessValue == 2 && mSecondValue.equals("") == false) {
                    mNextOperationValue = 3;
                    mProcessValue = 8;
                } else {
                    mOperationValue = 3;
                    mProcessValue += 2;
                }
                break;
            case R.id.button_divide:
                if (mProcessValue == 2 && mSecondValue.equals("") == false) {
                    mNextOperationValue = 4;
                    mProcessValue = 8;
                } else {
                    mOperationValue = 4;
                    mProcessValue += 2;
                }
                break;
            case R.id.button_equal:
                switch (mProcessValue) {
                    case 0:
                        mProcessValue = 9;
                        break;
                    case 2:
                        if (mSecondValue.equals("")) {
                            mProcessValue = 9;
                        } else {
                            mProcessValue = 5;
                        }
                        break;
                    case 5:
                        mProcessValue =10;
                        break;
                    default:
                        mProcessValue = 5;
                }
                break;
            case R.id.button_clear:
                mProcessValue = 10;
                break;
        }
        Process();
    }
}
