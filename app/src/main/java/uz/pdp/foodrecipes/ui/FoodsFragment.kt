package uz.pdp.foodrecipes.ui
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.pdp.foodrecipes.adapters.ViewPagerAdapter
import uz.pdp.foodrecipes.databinding.FragmentFoodsBinding
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import uz.pdp.foodrecipes.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FoodsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentFoodsBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFoodsBinding.inflate(inflater,container,false)
        viewPagerAdapter=ViewPagerAdapter(childFragmentManager)
        binding.viewpager.adapter=viewPagerAdapter
        binding.bottomBar.setItemSelected(uz.pdp.foodrecipes.R.id.homeFragment,true)

        binding.bottomBar.setOnItemSelectedListener(object:ChipNavigationBar.OnItemSelectedListener{
            override fun onItemSelected(id: Int) {
                when(id){
                    R.id.homeFragment->binding.viewpager.currentItem=0
                    R.id.categoryFragment->binding.viewpager.currentItem=1
                    R.id.favouriteFragment->binding.viewpager.currentItem=2

                }
            }

        })


        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                 when(position){
                     0-> binding.bottomBar.setItemSelected(uz.pdp.foodrecipes.R.id.homeFragment,true)
                     1-> binding.bottomBar.setItemSelected(uz.pdp.foodrecipes.R.id.categoryFragment,true)
                     2-> binding.bottomBar.setItemSelected(uz.pdp.foodrecipes.R.id.favouriteFragment,true)

                 }

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}