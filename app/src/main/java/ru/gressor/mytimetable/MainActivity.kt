package ru.gressor.mytimetable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gressor.mytimetable.databinding.ActivityMainBinding
import ru.gressor.mytimetable.ui.ClassesFragment
import ru.gressor.mytimetable.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        binding.tabClasses.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment !is ClassesFragment) {
                val old = binding.tabHome.currentTextColor
                binding.tabHome.setTextColor(binding.tabClasses.currentTextColor)
                binding.tabClasses.setTextColor(old)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ClassesFragment())
                    .commit()
            }
        }

        binding.tabHome.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment !is HomeFragment) {
                val old = binding.tabHome.currentTextColor
                binding.tabHome.setTextColor(binding.tabClasses.currentTextColor)
                binding.tabClasses.setTextColor(old)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }
    }
}