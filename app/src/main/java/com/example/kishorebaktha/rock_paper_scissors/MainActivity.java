package com.example.kishorebaktha.rock_paper_scissors;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
    }
    public void X(View v)
    {
        turn=5;

    }
    public void O(View v)
    {
        turn=7;
    }
    public void Next(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Game.class);
        intent.putExtra("turn",turn);
        startActivity(intent);
    }
}
