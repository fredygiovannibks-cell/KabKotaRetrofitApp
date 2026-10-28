package id.ac.polbeng.depandienda.kabkotaretrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.polbeng.depandienda.kabkotaretrofitapp.databinding.KabkotaItemBinding
import id.ac.polbeng.depandienda.kabkotaretrofitapp.helpers.Config
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem

class KabKotaAdapter : ListAdapter<KabKotaItem, KabKotaAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: KabKotaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KabkotaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kabKota = getItem(position)
        holder.bind(kabKota)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(kabKota)
        }
    }

    class ViewHolder(val binding: KabkotaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kabKota: KabKotaItem) {
            binding.tvKabKota.text = kabKota.kabupatenkota
            binding.tvPusatPemerintahan.text = "Pusat Pemerintahan: ${kabKota.pusatPemerintahan}"

            val url = "${Config.LOGO_URL}/${kabKota.logo}"
            Glide.with(itemView.context)
                .load(url)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imgLogo)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KabKotaItem>() {
            override fun areItemsTheSame(oldItem: KabKotaItem, newItem: KabKotaItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: KabKotaItem, newItem: KabKotaItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}