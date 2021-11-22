package uz.pdp.foodrecipes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.AdviceItemBinding
import uz.pdp.foodrecipes.models.AdviceCategory

class AdviceVpAdapter(var context:Context,var list:ArrayList<AdviceCategory>,var listener:AdviceVpAdapter.onclick):PagerAdapter() {
    override fun getCount(): Int {
        return 4
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view=AdviceItemBinding.inflate(LayoutInflater.from(container.context),container,false)
        Picasso.get().load(list[position].image).placeholder(R.drawable.placeholderteo).into(view.img)
        view.tv.text=list[position].name
        view.root.setOnClickListener {
           listener.itemClick(list[position])
        }
        container.addView(view.root)

        return view.root
    }
    interface onclick{
        fun itemClick(adviceCategory: AdviceCategory)
    }
}