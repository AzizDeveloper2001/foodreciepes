package uz.pdp.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.CategoryItemBinding
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.databinding.SecondItemBinding
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food

class CategoryAdapter(var list:ArrayList<Category>,var listener:onClick):RecyclerView.Adapter<CategoryAdapter.Vh>() {

     inner class Vh(var itemRvBinding: CategoryItemBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(category: Category, position: Int) {
             if(category.image!!.isNotEmpty()){
                 Picasso.get().load(category.image).placeholder(R.drawable.sponn).into(itemRvBinding.image)
             }

             itemRvBinding.categoryitem.text=category.name
             itemView.setOnClickListener {
                 listener.onItemClick(category,position)
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {

       return list.size


    }
    interface onClick{
        fun onItemClick(category: Category,position: Int)
    }
}