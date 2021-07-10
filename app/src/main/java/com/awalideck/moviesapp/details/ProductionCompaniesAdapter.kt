package com.awalideck.moviesapp.details

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moviesapp.R
import com.awalideck.moviesapp.data.network.ProductionCompany
import com.awalideck.moviesapp.databinding.ListItemProductionCompanyBinding
import com.awalideck.moviesapp.utils.getPosterURL
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductionCompaniesAdapter(
    private val companies: List<ProductionCompany>, private val context: Context
) : RecyclerView.Adapter<ProductionCompanyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompanyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemProductionCompanyBinding
            .inflate(layoutInflater, parent, false)
        return ProductionCompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductionCompanyViewHolder, position: Int) {
        val company = companies[position]
        holder.bind(company, context)
    }

    override fun getItemCount(): Int {
        return companies.size
    }
}

class ProductionCompanyViewHolder(
    binding: ListItemProductionCompanyBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val logo: ImageView = binding.logo
    private val companyName: TextView = binding.name

    fun bind(productionCompany: ProductionCompany, context: Context) {
        productionCompany.logoPath?.let { path ->
            Picasso.get()
                .load(getPosterURL(path))
                .resizeDimen(R.dimen.logo_image_width, R.dimen.logo_image_height)
                .onlyScaleDown()
                .placeholder(R.drawable.ic_image_24)
                .error(R.drawable.ic_broken_image_24)
                .into(logo)
        }
        companyName.text = productionCompany.name
    }
}