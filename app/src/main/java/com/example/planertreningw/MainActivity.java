package com.example.planertreningw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ivExercise;
    private TextView tvExerciseName, tvCurrentSeries, tvTotalSeries;
    private Button btnDraw, btnMinus, btnPlus, btnNext, btnReset;

    private int currentSeriesCount = 0;
    private int totalSeriesCount = 0;
    private int currentExerciseIndex = 0;

    private final Random random = new Random();
    private final int[] exerciseImages = {
            R.drawable.cwiczenie0,
            R.drawable.cwiczenie1,
            R.drawable.cwiczenie2,
            R.drawable.cwiczenie3,
            R.drawable.cwiczenie4,
            R.drawable.cwiczenie5,
            R.drawable.cwiczenie6
    };
    private final String[] exerciseNames = {
            "Losuj ćwiczenie",
            "Przysiad",
            "Pompki",
            "Plank",
            "Wykroki",
            "Burpees",
            "Podciąganie"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivExercise = findViewById(R.id.ivExercise);
        tvExerciseName = findViewById(R.id.tvExerciseName);
        tvCurrentSeries = findViewById(R.id.tvCurrentSeries);
        tvTotalSeries = findViewById(R.id.tvTotalSeries);

        btnDraw = findViewById(R.id.btnDraw);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnNext = findViewById(R.id.btnNext);
        btnReset = findViewById(R.id.btnReset);

        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawNewExercise();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSeriesCount++;
                updateCurrentSeriesDisplay();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSeriesCount > 0) {
                    currentSeriesCount--;
                    updateCurrentSeriesDisplay();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalSeriesCount += currentSeriesCount;
                updateTotalSeriesDisplay();
                drawNewExercise();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetApp();
            }
        });
    }

    private void drawNewExercise() {
        currentExerciseIndex = random.nextInt(6) + 1;
        currentSeriesCount = 0;

        ivExercise.setImageResource(exerciseImages[currentExerciseIndex]);
        tvExerciseName.setText(exerciseNames[currentExerciseIndex]);
        updateCurrentSeriesDisplay();
    }

    private void updateCurrentSeriesDisplay() {
        tvCurrentSeries.setText(String.valueOf(currentSeriesCount));
    }

    private void updateTotalSeriesDisplay() {
        tvTotalSeries.setText("Łączna liczba serii: " + totalSeriesCount);
    }

    private void resetApp() {
        currentSeriesCount = 0;
        totalSeriesCount = 0;
        currentExerciseIndex = 0;

        ivExercise.setImageResource(exerciseImages[0]);
        tvExerciseName.setText(exerciseNames[0]);
        updateCurrentSeriesDisplay();
        updateTotalSeriesDisplay();
    }
}