package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rick = findViewById<RecyclerView>(R.id.rv_character)

        ApiConfig.getService().getRick().enqueue(object : Callback<com.example.retrofit.Response>{
            override fun onResponse(call: Call<com.example.retrofit.Response>, response: Response<com.example.retrofit.Response>){
                if(response.isSuccessful){
                    val responseRick = response.body()
                    val dataRick = responseRick?.results
                    val ItemAdapter = ItemAdapter(dataRick as List<ResultsItem>)
                    rick.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        ItemAdapter.notifyDataSetChanged()
                        adapter = ItemAdapter
                    }
                }
            }
            override fun onFailure (call: Call<com.example.retrofit.Response>, t: Throwable){
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}