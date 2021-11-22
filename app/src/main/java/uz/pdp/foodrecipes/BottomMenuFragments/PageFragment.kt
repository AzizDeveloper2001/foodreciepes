package uz.pdp.foodrecipes.BottomMenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FragmentInfoBinding
import uz.pdp.foodrecipes.databinding.FragmentPageBinding
import uz.pdp.foodrecipes.models.Food
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PageFragment : Fragment() {
    private var param1: Food? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Food
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentPageBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentPageBinding.inflate(inflater,container,false)
        if(param1!!.image!!.isNotEmpty()){
            Picasso.get().load(param1?.image).placeholder(R.drawable.placeholderteo).into(binding.image)
        }
        binding.name.text= param1?.foodname
        binding.time.text=param1?.time
        binding.desc.text=param1?.preparetion!!.substring(0,50)+"..."
        binding.root.setOnClickListener {
            var bundle=Bundle()
            bundle.putSerializable("food",param1)
            findNavController().navigate(R.id.infoFragment,bundle)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Food) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}