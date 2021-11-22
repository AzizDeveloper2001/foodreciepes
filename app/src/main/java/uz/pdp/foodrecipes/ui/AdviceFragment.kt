package uz.pdp.foodrecipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.firebase.firestore.FirebaseFirestore
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.AdviceVpAdapter
import uz.pdp.foodrecipes.databinding.FragmentAdviceBinding
import uz.pdp.foodrecipes.models.AdviceCategory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AdviceFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentAdviceBinding
    lateinit var adviceVpAdapter: AdviceVpAdapter
    lateinit var firestore: FirebaseFirestore
    lateinit var list:ArrayList<AdviceCategory>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdviceBinding.inflate(inflater,container,false)
        firestore= FirebaseFirestore.getInstance()
        return binding.root
    }

    override fun onResume() {

        list= ArrayList()
        firestore.collection("Maslahatlar kategoriyalari").get()
            .addOnSuccessListener {
                for (i in it.documents){
                 var  a=  i.toObject(AdviceCategory::class.java)
                    list.add(a!!)
                }
                adviceVpAdapter= AdviceVpAdapter(requireContext(),list,object :AdviceVpAdapter.onclick{
                    override fun itemClick(adviceCategory: AdviceCategory) {
                        var bundle=Bundle()
                        bundle.putSerializable("advice",adviceCategory)
                        findNavController().navigate(R.id.adviceInfoFragment,bundle,
                            navOptions { // Use the Kotlin DSL for building NavOptions
                                anim {
                                    enter = android.R.anim.fade_in
                                    exit = android.R.animator.fade_out
                                }
                            })
                    }

                })
                AnimationUtils.loadAnimation(requireContext(), R.anim.scale).also { hyperspaceJumpAnimation ->
                    binding.vp.startAnimation(hyperspaceJumpAnimation)
                }
                binding.vp.adapter=adviceVpAdapter


            }



        super.onResume()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdviceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}