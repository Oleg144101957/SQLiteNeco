package com.vishnevskiypro.sqliteneco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vishnevskiypro.sqliteneco.databinding.ActivityMainBinding
import com.vishnevskiypro.sqliteneco.db.MyDBManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val myDBmanager = MyDBManager(this)


    override fun onResume() {
        super.onResume()
        myDBmanager.openDb()

        val dataList = myDBmanager.readDbData()

        for (item in dataList){
            binding.tvTest.append(item)
            binding.tvTest.append("\n")
        }



    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            binding.tvTest.text = ""

            val title = binding.etTitle.text.toString()
            val content = binding.edContent.text.toString()
            myDBmanager.insertTODb(title = title, content = content)

            val dataList = myDBmanager.readDbData()

            for (item in dataList){
                binding.tvTest.append(item)
                binding.tvTest.append("\n")
            }

        }


    }

    override fun onDestroy() {
        myDBmanager.closeDB()
        super.onDestroy()
    }






}