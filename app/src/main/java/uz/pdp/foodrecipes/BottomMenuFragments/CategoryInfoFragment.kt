package uz.pdp.foodrecipes.BottomMenuFragments

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.CategoryInfoAdapter
import uz.pdp.foodrecipes.databinding.FragmentCategoryInfoBinding
import uz.pdp.foodrecipes.models.Food


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CategoryInfoFragment : Fragment() {
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

    lateinit var binding:FragmentCategoryInfoBinding
    lateinit var firestore: FirebaseFirestore
    lateinit var list: ArrayList<Food>
    lateinit var type:String
    lateinit var categoryInfoAdapter: CategoryInfoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoryInfoBinding.inflate(inflater,container,false)
        type=arguments?.getString("name")!!
        (activity as AppCompatActivity?)!!.supportActionBar?.title=type

        firestore= FirebaseFirestore.getInstance()
        list= ArrayList()
        firestore.collection(type).get()
            .addOnSuccessListener {
                for (document in it.documents) {
                    var food= document.toObject(Food::class.java)
                    if (food != null && !list.contains(food)) {
                        list.add(food)
                    }

                }
               categoryInfoAdapter=CategoryInfoAdapter(list,object :CategoryInfoAdapter.onItemClick{
                   override fun onViewClick(food: Food, position: Int) {
                       var bundle=Bundle()
                       bundle.putSerializable("food",food)
                       findNavController().navigate(R.id.infoFragment,bundle,
                           navOptions { // Use the Kotlin DSL for building NavOptions
                               anim {
                                   enter = android.R.anim.fade_in
                                   exit = android.R.animator.fade_out
                               }
                           })
                   }

               })
                val dividerItemDecoration = DividerItemDecoration(
                    binding.rv.context,
                    DividerItemDecoration.VERTICAL
                )
                binding.rv.addItemDecoration(dividerItemDecoration)
                binding.rv.adapter=categoryInfoAdapter
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Navigation.findNavController(view).currentDestination?.label=type
    }


//    override fun onResume() {
//        super.onResume()
//        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
//        var navController=requireActivity().findNavController(R.id.nav_host_fragment_content_main)
//        var appBarConfiguration= AppBarConfiguration(navController.graph)
//        var a=requireActivity().findViewById<Toolbar>(R.id.toolbarr)
//
//        a.setupWithNavController(navController,appBarConfiguration)
//        (activity as AppCompatActivity?)!!.setSupportActionBar(a)
//        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
////        var a=  requireActivity().findViewById<Toolbar>(R.id.toolbarr)
////
////        (activity as AppCompatActivity?)!!.setSupportActionBar(a)
////        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}