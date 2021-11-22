package uz.pdp.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.databinding.FirstItemBinding
import uz.pdp.foodrecipes.models.Food
import uz.pdp.foodrecipes.models.MainScreen

class FirstRvAdapter(var list:ArrayList<MainScreen>,var listener: onClick):RecyclerView.Adapter<FirstRvAdapter.Vh>() {
    lateinit var secondRvAdapter:SecondRvAdapter

     inner class Vh(var itemRvBinding: FirstItemBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){
         fun onBind(mainScreen: MainScreen, position: Int) {
            secondRvAdapter= SecondRvAdapter(mainScreen.list,object :SecondRvAdapter.onitemClick{
                override fun onclick(food: Food) {
                    listener.onitemClick(food,mainScreen,position)
                }

            })
             itemRvBinding.rv.adapter=secondRvAdapter
             itemRvBinding.name.text=mainScreen.name
             itemRvBinding.next.setOnClickListener {
                 listener.onNextClick(mainScreen,position)
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(FirstItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface onClick{
        fun onNextClick(mainScreen: MainScreen,position: Int)
        fun onitemClick(food:Food,mainScreen: MainScreen,position: Int)
    }
}