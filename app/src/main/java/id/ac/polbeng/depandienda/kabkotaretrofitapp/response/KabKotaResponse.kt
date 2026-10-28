package id.ac.polbeng.depandienda.kabkotaretrofitapp.response

import com.google.gson.annotations.SerializedName
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem

data class KabKotaResponse(
	@field:SerializedName("data")
	val data: List<KabKotaItem>,

	@field:SerializedName("message")
	val message: String
)