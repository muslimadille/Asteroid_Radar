package com.udacity.asteroidradar.main
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.api.Asteroid
import com.udacity.asteroidradar.databinding.AstroidListItemBinding

class AsteroidListAdaptor(private var items: List<Asteroid>): RecyclerView.Adapter<MyViewHolder>(){
    private var onItemClickListener: ((Asteroid) -> Unit)? = null

    fun setOnItemClickListener(listener: (Asteroid) -> Unit) {
        onItemClickListener = listener
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems:List<Asteroid>){
        items=newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AstroidListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class MyViewHolder(private val binding: AstroidListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Asteroid) {
        binding.asteroid = item
        binding.executePendingBindings()
    }
}