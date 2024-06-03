package com.example.cricbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn1;

    Button btn2;
    Button btn3;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button btn4;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    int totalRunsA = 0;
    int wicketA=0;
    int totalRunsB = 0;
    int wicketB=0;
    int ballsLeft;
    boolean teamAAllOut = false;
    boolean teamBAllOut = false;
    TextView ballsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballsTextView = (TextView) findViewById(R.id.textView11);
        btn1= (Button)findViewById(R.id.button3);
        btn2= (Button)findViewById(R.id.button4);
        btn3=(Button)findViewById(R.id.button12);
        text1=(TextView)findViewById(R.id.textView13);
        text2=(TextView)findViewById(R.id.textView) ;
        text3=(TextView)findViewById(R.id.textView6) ;
        text4=(TextView)findViewById(R.id.textView4);
        text5=(TextView)findViewById(R.id.textView5);
        text2.setText(String.valueOf(totalRunsA));
        text3.setText(String.valueOf(totalRunsB));
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        button3 = findViewById(R.id.button8);
        button4 = findViewById(R.id.button9);
        button5 = findViewById(R.id.button10);
        button6 = findViewById(R.id.button11);
        btn4=findViewById(R.id.button14);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msga="Team A is Batting";
                text1.setText(msga);
                ballsLeft = 30;
                updateBallsText();
                text2.setText("0");
                text4.setText("0");
                Toast.makeText(MainActivity.this,"Team A is Batting!",Toast.LENGTH_SHORT).show();



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgb="Team B is Batting";
                text1.setText(msgb);
                ballsLeft = 30;
                updateBallsText();
                text3.setText("0");
                text5.setText("0");
                Toast.makeText(MainActivity.this,"Team B is Batting!",Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWicket(1);
                updateBalls();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchResult();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(1);
                updateBalls();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(2);
                updateBalls();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(3);
                updateBalls();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(4);
                updateBalls();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(5);
                updateBalls();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRuns(6);
                updateBalls();
            }
        });
    }

    private void updateRuns(int runs) {
        String message=text1.getText().toString();

        if (message.equalsIgnoreCase("Team A is Batting")) {
            if (!teamAAllOut) {
                totalRunsA += runs;
                text2.setText(String.valueOf(totalRunsA));
            }
        } else if (message.equalsIgnoreCase("Team B is Batting")) {
            if (!teamBAllOut) {
                totalRunsB += runs;
                text3.setText(String.valueOf(totalRunsB));
            }
        }

    }

    private void updateWicket(int wkt)
    {
        String message=text1.getText().toString();
        if(message.equalsIgnoreCase("Team A is Batting"))
        {

                wicketA+=wkt;
                text4.setText(String.valueOf(wicketA));
            if((text4.getText().toString()).equalsIgnoreCase("10"))
            {
                text1.setText("Team A All Out");
            }
//            text1.setText("Team A All Out");

        }
        else if(message.equalsIgnoreCase("Team B is Batting"))
        {
              wicketB+=wkt;
              text5.setText(String.valueOf(wicketB));

              if((text5.getText().toString()).equalsIgnoreCase("10"))
              {
                  text1.setText("Team B All Out");
              }

            //text1.setText("Team B All Out");
        }
    }
    private void updateBalls() {
        ballsLeft--;
        if (ballsLeft == 0) {
            String message = text1.getText().toString();
            if (message.equalsIgnoreCase("Team A is Batting")) {
                text1.setText("Team A Match Over");
                ballsLeft = 0; // Reset ballsLeft to 0
            } else if (message.equalsIgnoreCase("Team B is Batting")) {
                text1.setText("Team B Match Over");
                ballsLeft = 0; // Reset ballsLeft to 0
            }
            ballsTextView.setText("0");
        } else if (ballsLeft > 0) {
            updateBallsText();
        }

    }
    private  void matchResult()
    {
        String scoreA=text2.getText().toString();
        String scoreB=text3.getText().toString();
        double sca;
        double scb;
        sca=Double.parseDouble(scoreA);
        scb=Double.parseDouble(scoreB);
        if(sca>scb)
        {
            text1.setText("Team A Won!");
        }
        else if(scb>sca)
        {
            text1.setText("Team B Won!");
        }
        else
        {
            text1.setText("Match Draw!");
        }



    }
    private void updateBallsText() {
        ballsTextView.setText(String.valueOf(ballsLeft));
    }
}
