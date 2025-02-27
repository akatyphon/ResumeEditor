package com.android.resume

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.android.resume.databinding.ActivityMainBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvFontSizeLabel.text = getString(R.string.font_size_seekbar, 18)

        binding.btnFontColor.setOnClickListener {
            pickColor {
                binding.tvResumeText.setTextColor(it.toColorInt())
            }
        }

        binding.btnBgColor.setOnClickListener {
            pickColor {
                binding.tvResumeText.setBackgroundColor(it.toColorInt())
            }
        }

        binding.sbFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvFontSizeLabel.text = getString(R.string.font_size_seekbar, progress)
                binding.tvResumeText.textSize = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

    }

    private fun pickColor(onPick: (color: String) -> Unit) {
        ColorPickerDialog.Builder(this)
            .setTitle("Pick color")
            .setColorShape(ColorShape.SQAURE)
            .setDefaultColor(R.color.purple_200)
            .setColorListener { _, colorHex ->
                onPick(colorHex)
            }
            .show()
    }
}


