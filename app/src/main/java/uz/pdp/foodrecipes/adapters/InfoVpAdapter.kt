package uz.pdp.foodrecipes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.foodrecipes.BottomMenuFragments.IngredentsFragment
import uz.pdp.foodrecipes.BottomMenuFragments.PageFragment
import uz.pdp.foodrecipes.BottomMenuFragments.PreperationFragment
import uz.pdp.foodrecipes.models.Food

class InfoVpAdapter(var food: Food, fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return if(position==0){
            IngredentsFragment.newInstance(food)
        } else {
            PreperationFragment.newInstance(food)
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {

        return if(position==0){
            "Masalliqlar"
        } else {
            "Tayyorlanishi"
        }
    }
}