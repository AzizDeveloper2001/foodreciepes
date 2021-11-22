package uz.pdp.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.databinding.IngredentsItemBinding
import uz.pdp.foodrecipes.databinding.SecondItemBinding
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food

class IngredentsRvAdapter(var list:ArrayList<String>):RecyclerView.Adapter<IngredentsRvAdapter.Vh>() {

     inner class Vh(var itemRvBinding: IngredentsItemBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(name: String, position: Int) {


             itemRvBinding.name.text=name
             itemRvBinding.checkbox.setOnCheckedChangeListener(object :
                 CompoundButton.OnCheckedChangeListener {
                 override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                     if(isChecked){
                         itemRvBinding.remove.visibility=View.VISIBLE
                     } else {
                         itemRvBinding.remove.visibility=View.INVISIBLE
                     }
                 }

             })

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(IngredentsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {

       return list.size


    }

}