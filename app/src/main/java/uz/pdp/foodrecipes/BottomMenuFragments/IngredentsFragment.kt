package uz.pdp.foodrecipes.BottomMenuFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.IngredentsRvAdapter
import uz.pdp.foodrecipes.databinding.FragmentIngredentsBinding
import uz.pdp.foodrecipes.models.Food

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class IngredentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Food? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Food
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentIngredentsBinding
    lateinit var ingredentsRvAdapter: IngredentsRvAdapter
    lateinit var food: Food
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentIngredentsBinding.inflate(inflater,container,false)

        ingredentsRvAdapter=IngredentsRvAdapter(param1?.ingredents!!)
        binding.rv.adapter=ingredentsRvAdapter
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Food) =
            IngredentsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}