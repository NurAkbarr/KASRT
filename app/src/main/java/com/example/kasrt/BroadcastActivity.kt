package com.example.kasrt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kasrt.model.DataItem
import com.example.kasrt.model.ResponseUser
import com.example.kasrt.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BroadcastActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        textViewTitle = findViewById(R.id.textViewBroadcastTitle)
        textViewMessage = findViewById(R.id.textViewBroadcastMessage)

        fetchBroadcastInfo()
    }

    private fun fetchBroadcastInfo() {
        val apiService = ApiConfig.getApiService()
        val call = apiService.getBroadcastInfo()

        call.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val responseUser = response.body()
                    responseUser?.let {
                        displayBroadcastInfo(it.data)
                    }
                } else {
                    // Handle error response
                    textViewTitle.text = "Error"
                    textViewMessage.text = "Failed to fetch broadcast info"
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                // Handle failure
                textViewTitle.text = "Error"
                textViewMessage.text = "Failed to fetch broadcast info"
            }
        })
    }

    private fun displayBroadcastInfo(dataItemList: List<DataItem>) {
        val broadcastInfo = StringBuilder()
        for ((index, dataItem) in dataItemList.withIndex()) {
            broadcastInfo.append("Judul ${index + 1}: ${dataItem.title}\n")
            broadcastInfo.append("Pesan ${index + 1}: ${dataItem.message}\n")
            if (index < dataItemList.size - 1) {
                broadcastInfo.append("\n") // Menambahkan spasi antara setiap item
            }
        }
        textViewTitle.text = "Informasi Terbaru"
        textViewMessage.text = broadcastInfo.toString()
    }
}
