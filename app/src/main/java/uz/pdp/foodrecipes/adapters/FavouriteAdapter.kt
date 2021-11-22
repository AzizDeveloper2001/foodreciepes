package uz.pdp.foodrecipes.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.databinding.SItemBinding
import uz.pdp.foodrecipes.databinding.SecondItemBinding
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food
import uz.pdp.foodrecipes.room.Database.AppDatabase
import uz.pdp.foodrecipes.room.Entities.FoodEntitiy

class FavouriteAdapter(var context:Context,var list:ArrayList<FoodEntitiy>,var listener:onchange):RecyclerView.Adapter<FavouriteAdapter.Vh>() {
lateinit var appDatabase: AppDatabase
     inner class Vh(var itemRvBinding: SItemBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(food: FoodEntitiy, position: Int) {
             if(food.image!!.isNotEmpty()){
                 Picasso.get().load(food.image).placeholder(R.drawable.sponn).into(itemRvBinding.image)
             }

             itemRvBinding.foodname.text=food.foodname
             itemRvBinding.like.isLiked=true
             itemView.setOnClickListener {
                 listener.onclik(food,position)
             }
             itemRvBinding.like.setOnLikeListener(object :OnLikeListener{
                 override fun liked(likeButton: LikeButton?) {

                 }

                 override fun unLiked(likeButton: LikeButton?) {

                     appDatabase= AppDatabase.getInstance(itemRvBinding.root.context)

                     appDatabase.foodDao().delete(food)
                     list.remove(food)
                     if(list.size==0){
                         listener.onChange()
                     }
                     notifyDataSetChanged()
                 }

             })

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(SItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {

       return list.size


    }
    interface onchange{
        fun onChange()
        fun onclik(food: FoodEntitiy,position: Int)
    }
}