package id.ac.polbeng.depandienda.kabkotaretrofitapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import id.ac.polbeng.depandienda.kabkotaretrofitapp.adapter.KabKotaAdapter
import id.ac.polbeng.depandienda.kabkotaretrofitapp.databinding.ActivityMainBinding
import id.ac.polbeng.depandienda.kabkotaretrofitapp.helpers.Config
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem
import id.ac.polbeng.depandienda.kabkotaretrofitapp.response.KabKotaResponse
import id.ac.polbeng.depandienda.kabkotaretrofitapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        getListKabKota()

        // Mengatur aksi ketika layar ditarik ke bawah (Swipe to Refresh)
        binding.swipeRefreshLayout.setOnRefreshListener {
            getListKabKota()
        }
    }

    private fun getListKabKota() {
        // Menampilkan indikator loading swipe refresh
        binding.swipeRefreshLayout.isRefreshing = true

        val client = ApiConfig.getApiService().getListUsers()
        client.enqueue(object : Callback<KabKotaResponse> {
            override fun onResponse(
                call: Call<KabKotaResponse>,
                response: Response<KabKotaResponse>
            ) {
                // Matikan animasi loading setelah data selesai dimuat
                binding.swipeRefreshLayout.isRefreshing = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val listKabKota = responseBody.data
                        setListData(listKabKota)
                    }
                } else {
                    Toast.makeText(applicationContext, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<KabKotaResponse>, t: Throwable) {
                // Matikan animasi loading jika terjadi error jaringan
                binding.swipeRefreshLayout.isRefreshing = false

                Toast.makeText(applicationContext, "Kesalahan jaringan: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setListData(listKabKota: List<KabKotaItem>) {
        val adapter = KabKotaAdapter()
        adapter.submitList(listKabKota)
        binding.rvKabKota.adapter = adapter

        // Menambahkan aksi klik pada item RecyclerView
        adapter.setOnItemClickCallback(object : KabKotaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: KabKotaItem) {
                val json = Gson().toJson(data)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailKabKotaActivity::class.java)
                moveWithObjectIntent.putExtra(Config.EXTRA_KAB_KOTA, json)
                startActivity(moveWithObjectIntent)
            }
        })
    }
}