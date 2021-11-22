package uz.pdp.foodrecipes.BottomMenuFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.firebase.firestore.FirebaseFirestore
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.CategoryAdapter
import uz.pdp.foodrecipes.databinding.FragmentCategoryBinding
import uz.pdp.foodrecipes.models.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CategoryFragment : Fragment() {
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

    lateinit var binding:FragmentCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var list: ArrayList<Category>
    lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoryBinding.inflate(inflater,container,false)
        firestore= FirebaseFirestore.getInstance()
        list= ArrayList()
        firestore.collection("Kategoriya").get()
            .addOnSuccessListener {
                var d=it.documents
                for (documentSnapshot in d) {
                    var category=documentSnapshot.toObject(Category::class.java)
                    if (category != null) {
                        list.add(category)
                    }

                }
                categoryAdapter= CategoryAdapter(list,object :CategoryAdapter.onClick{
                    override fun onItemClick(category: Category, position: Int) {
                        var bundle=Bundle()
                        bundle.putString("name",category.name)
                        findNavController().navigate(R.id.categoryInfoFragment,bundle,

                                       navOptions { // Use the Kotlin DSL for building NavOptions
                                           anim {
                                               enter = android.R.anim.fade_in
                                               exit = android.R.animator.fade_out
                                           }
                                       })
                    }

                })
                binding.rv.adapter=categoryAdapter
            }
        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}