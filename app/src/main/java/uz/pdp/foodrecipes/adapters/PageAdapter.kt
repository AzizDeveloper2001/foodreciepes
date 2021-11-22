package uz.pdp.foodrecipes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.foodrecipes.BottomMenuFragments.PageFragment
import uz.pdp.foodrecipes.models.Food

class PageAdapter(var list:ArrayList<Food>,fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {

       return PageFragment.newInstance(list[position])
    }
}