package com.macalester.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.macalester.minesweeper.databinding.ActivityMainBinding
import com.macalester.minesweeper.model.FlagModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resetButton.setOnClickListener {
            binding.minesweeperView.resetGame()
            if (binding.flagToggle.isChecked()){
                FlagModel.enableFlagMode()
            }
            else{
                FlagModel.disableFlagMode()
            }
        }

        binding.flagToggle.setOnClickListener() {
            if (FlagModel.flagModeOn){
                Snackbar.make(binding.root,
                    getString(R.string.fieldOn),
                    Snackbar.LENGTH_LONG).show()
                FlagModel.disableFlagMode()
            }
            else{
                if (FlagModel.flagModeOn){
                    FlagModel.disableFlagMode()
                }
                else{
                    FlagModel.enableFlagMode()
                }
                Snackbar.make(binding.root,
                    getString(R.string.flagOn),
                    Snackbar.LENGTH_LONG).show()
                FlagModel.enableFlagMode()
            }
        }
    }
}