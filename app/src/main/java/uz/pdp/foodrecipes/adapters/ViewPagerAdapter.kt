package uz.pdp.foodrecipes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.foodrecipes.BottomMenuFragments.CategoryFragment
import uz.pdp.foodrecipes.BottomMenuFragments.FavouriteFragment
import uz.pdp.foodrecipes.BottomMenuFragments.HomeFragment

class ViewPagerAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment=Fragment()
        when(position){
            0->fragment=HomeFragment.newInstance()
            1->fragment=CategoryFragment.newInstance()
            2->fragment=FavouriteFragment.newInstance()

        }
        return fragment
    }
}