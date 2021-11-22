package uz.pdp.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.databinding.SecondItemBinding
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food

class SecondRvAdapter(var list:ArrayList<Food>,var listener:onitemClick):RecyclerView.Adapter<SecondRvAdapter.Vh>() {

     inner class Vh(var itemRvBinding: SecondItemBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(food: Food, position: Int) {
             if(food.image!!.isNotEmpty()){
                 Picasso.get().load(food.image).placeholder(R.drawable.sponn).resize(200,200).into(itemRvBinding.image)
             }

             itemRvBinding.foodname.text=food.foodname
             itemView.setOnClickListener {
                 listener.onclick(food)
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(SecondItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {

       return list.size


    }
    interface onitemClick{
        fun onclick(food:Food)
    }
}