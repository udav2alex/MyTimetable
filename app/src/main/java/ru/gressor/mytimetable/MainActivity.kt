package ru.gressor.mytimetable

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gressor.mytimetable.databinding.ActivityMainBinding
import ru.gressor.mytimetable.ui.classes.ClassesFragment
import ru.gressor.mytimetable.ui.home.HomeFragment
import ru.gressor.mytimetable.utils.SkypeLinkListener

class MainActivity : AppCompatActivity(), SkypeLinkListener {
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
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.tabHome.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment !is HomeFragment) {
                val old = binding.tabHome.currentTextColor
                binding.tabHome.setTextColor(binding.tabClasses.currentTextColor)
                binding.tabClasses.setTextColor(old)
                onBackPressed()
            }
        }
    }

    override fun startSkypeLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }
}