package com.example.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int ScoreA=0;
    int ScoreB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //void displayForTeamA();


    }
    /**
     * Displays the given score for Team A.
     */

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void plusThreeForTeamA(View view)
    {
        ScoreA =ScoreA+3;
        displayForTeamA(ScoreA);
    }
    public void plusTowForTeamA(View view)
    {
        ScoreA =ScoreA+2;
        displayForTeamA(ScoreA);
    }
    public void freeThrowForTeamA(View view)
    {
        ScoreA =ScoreA+1;
        displayForTeamA(ScoreA);
    }

    /**
     * Displays the given score for Team B.
     */

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void plusThreeForTeamB(View view)
    {
        ScoreB =ScoreB+3;
        displayForTeamB(ScoreB);
    }
    public void plusTowForTeamB(View view)
    {
        ScoreB =ScoreB+2;
        displayForTeamB(ScoreB);
    }
    public void freeThrowForTeamB(View view)
    {
        ScoreB =ScoreB+1;
        displayForTeamB(ScoreB);
    }
    public void resetForBoth(View view)
    {   ScoreA=0;
        ScoreB =0;
        displayForTeamA(ScoreA);
        displayForTeamB(ScoreB);
    }

}