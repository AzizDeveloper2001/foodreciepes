package uz.pdp.foodrecipes.BottomMenuFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.FavouriteAdapter
import uz.pdp.foodrecipes.databinding.FragmentFavouriteBinding
import uz.pdp.foodrecipes.models.Food
import uz.pdp.foodrecipes.room.Database.AppDatabase
import uz.pdp.foodrecipes.room.Entities.FoodEntitiy
import java.lang.Appendable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentFavouriteBinding
    lateinit var favouriteAdapter: FavouriteAdapter
    lateinit var list:ArrayList<FoodEntitiy>
    lateinit var appDatabase: AppDatabase
    private  val TAG = "FavouriteFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFavouriteBinding.inflate(inflater,container,false)
        appDatabase= AppDatabase.getInstance(requireContext())
        list= ArrayList(appDatabase.foodDao().getAllFoods())

        favouriteAdapter=FavouriteAdapter(requireContext(),list,object :FavouriteAdapter.onchange{
            override fun onChange() {
                binding.animationView.visibility=View.VISIBLE
                binding.tv.visibility=View.VISIBLE
            }

            override fun onclik(food: FoodEntitiy, position: Int) {
                var f=Food(food.foodname,food.preparationg,food.image,food.time,food.category,food.ingredents)
                var bundle=Bundle()
                bundle.putSerializable("food",f)
                findNavController().navigate(R.id.infoFragment,bundle)
            }

        })
        if(list.size==0){
            binding.animationView.visibility=View.VISIBLE
            binding.tv.visibility=View.VISIBLE
        } else {
            binding.animationView.visibility=View.INVISIBLE
            binding.tv.visibility=View.INVISIBLE
        }
        binding.rv.adapter=favouriteAdapter
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}