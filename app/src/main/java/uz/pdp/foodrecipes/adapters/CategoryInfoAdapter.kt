package uz.pdp.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.databinding.ItemFoodBinding
import uz.pdp.foodrecipes.databinding.SecondItemBinding
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food

class CategoryInfoAdapter(var list:ArrayList<Food>,var listener:CategoryInfoAdapter.onItemClick):RecyclerView.Adapter<CategoryInfoAdapter.Vh>() {

     inner class Vh(var itemRvBinding: ItemFoodBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(food: Food, position: Int) {
             if(food.image!!.isNotEmpty()){
                 Picasso.get().load(food.image).placeholder(R.drawable.sponn).into(itemRvBinding.image)
             }

             itemRvBinding.name.text=food.foodname
             itemRvBinding.time.text=food.time
             itemView.setOnClickListener {
                 listener.onViewClick(food,position)
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {

       return list.size


    }
    interface onItemClick{
        fun onViewClick(food: Food,position: Int)
    }
}