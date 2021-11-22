package uz.pdp.foodrecipes.BottomMenuFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FragmentAdviceInfoBinding
import uz.pdp.foodrecipes.models.AdviceCategory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AdviceInfoFragment : Fragment() {
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

    lateinit var binding:FragmentAdviceInfoBinding
    lateinit var adviceCategory: AdviceCategory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentAdviceInfoBinding.inflate(inflater,container,false)
        adviceCategory=arguments?.getSerializable("advice") as AdviceCategory
        Picasso.get().load(adviceCategory.image).placeholder(R.drawable.placeholderteo).into(binding.image)
        binding.desc.text=adviceCategory.desc
        if(adviceCategory.list?.size!! > 0){
            for (i in adviceCategory.list!!){
                binding.list.text="${binding.list.text}\n$i"
            }
            }

        binding.backk.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.collapse.title=adviceCategory.name
        binding.collapse.setExpandedTitleColor(Color.parseColor("#009688"))
        binding.collapse.setCollapsedTitleTextColor(Color.parseColor("#ffffff"))

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

//        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
//        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        super.onStop()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdviceInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}