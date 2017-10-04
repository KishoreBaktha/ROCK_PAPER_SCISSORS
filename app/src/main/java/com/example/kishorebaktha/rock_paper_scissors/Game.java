package com.example.kishorebaktha.rock_paper_scissors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    ImageView img1,img2;
    Random r=new Random();
    TextView you,opp;
    MediaPlayer player,player2,player3;
    Context context=this;
    Button rock,paper,scissors;
    int score1=0,score2=0,limit;
    final Handler mHandler = new Handler();
    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(Game.this);
            a_builder.setMessage("do you want to exit the game?")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(score1==limit)
                                player2.stop();
                            else
                                player3.stop();
                            finish();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    rock.setEnabled(true);
                    paper.setEnabled(true);
                    scissors.setEnabled(true);
                    if(score1==limit)
                        player2.stop();
                    else
                        player3.stop();
                    player=MediaPlayer.create(context,R.raw.fmusic);
                    player.start();
                    score1=0;
                    you.setText("0");
                    score2=0;
                    opp.setText("0");
                }
            });
            AlertDialog ab = a_builder.create();
            ab.setTitle("Alert");
            ab.show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        limit=intent.getIntExtra("turn",5);
        img1=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
        you=(TextView)findViewById(R.id.textView);
        rock=(Button)findViewById(R.id.button);
        paper=(Button)findViewById(R.id.button2);
        scissors=(Button)findViewById(R.id.button3);
        opp=(TextView)findViewById(R.id.textView2);
        you.setText("0");
        opp.setText("0");
        player=MediaPlayer.create(context,R.raw.fmusic);
        player.start();
    }
    public void ROCK(View view)
    {
        img1.setImageResource(R.drawable.rock);
        int m=r.nextInt(3);
        if(m==0)
            img2.setImageResource(R.drawable.rock);
        if(m==1)
            img2.setImageResource(R.drawable.paper);
        if(m==2)
            img2.setImageResource(R.drawable.scissor);
        check(0,m);
    }
    public void PAPER(View view)
    {
        img1.setImageResource(R.drawable.paper);
        int m=r.nextInt(3);
        if(m==0)
            img2.setImageResource(R.drawable.rock);
        if(m==1)
            img2.setImageResource(R.drawable.paper);
        if(m==2)
            img2.setImageResource(R.drawable.scissor);
        check(1,m);
    }
    public void SCISSORS(View view)
    {
        img1.setImageResource(R.drawable.scissor);
        int m=r.nextInt(3);
        if(m==0)
            img2.setImageResource(R.drawable.rock);
        if(m==1)
            img2.setImageResource(R.drawable.paper);
        if(m==2)
            img2.setImageResource(R.drawable.scissor);
        check(2,m);
    }
    public void check(int a,int b)
    {
        if(a==b)
            Toast.makeText(getApplicationContext(),"DRAW",Toast.LENGTH_SHORT).show();
        else
        {
            if(a==0&&b==1) {
                Toast.makeText(getApplicationContext(), "YOU LOSE", Toast.LENGTH_SHORT).show();
                score2++;
                opp.setText(String.valueOf(score2));
            }
            else if(a==0&&b==2) {
                Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_SHORT).show();
                score1++;
                you.setText(String.valueOf(score1));
            }
            else if(a==1&&b==0) {
                score1++;
                you.setText(String.valueOf(score1));
                Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_SHORT).show();
            }

            else if(a==1&&b==2) {
                Toast.makeText(getApplicationContext(), "YOU LOSE", Toast.LENGTH_SHORT).show();
                score2++;
                opp.setText(String.valueOf(score2));
            }
            else if(a==2&&b==0) {
                Toast.makeText(getApplicationContext(), "YOU LOSE", Toast.LENGTH_SHORT).show();
                score2++;
                opp.setText(String.valueOf(score2));
            }
            else if(a==2&&b==1) {
                Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_SHORT).show();
                score1++;
                you.setText(String.valueOf(score1));
            }
        }
        if(score1==limit)
        {
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);
            player2=MediaPlayer.create(context,R.raw.clap);
            player.stop();
            player2.start();
            LayoutInflater layoutInflater=getLayoutInflater();
            View view=layoutInflater.inflate(R.layout.green_toast,null);
            Toast toast=new Toast(this);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,100);
            toast.show();
            startTestThread();
        }
        if(score2==limit)
        {
            player3=MediaPlayer.create(context,R.raw.boo);
            player.stop();
            player3.start();
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);
            LayoutInflater layoutInflater=getLayoutInflater();
            View view=layoutInflater.inflate(R.layout.yellow_toast,null);
            Toast toast=new Toast(this);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,100);
            toast.show();
            startTestThread();
        }
    }
    protected void startTestThread() {
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.postDelayed(mUpdateResults,1000);
            }
        };
        t.start();
    }
}
