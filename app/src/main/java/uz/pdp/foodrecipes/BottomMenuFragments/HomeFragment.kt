package uz.pdp.foodrecipes.BottomMenuFragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.adapters.FirstRvAdapter
import uz.pdp.foodrecipes.adapters.PageAdapter
import uz.pdp.foodrecipes.databinding.FragmentHomeBinding
import uz.pdp.foodrecipes.models.AdviceCategory
import uz.pdp.foodrecipes.models.Category
import uz.pdp.foodrecipes.models.Food
import uz.pdp.foodrecipes.models.MainScreen
import uz.pdp.foodrecipes.room.Database.AppDatabase

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class HomeFragment : Fragment() {
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

    lateinit var binding:FragmentHomeBinding
    lateinit var pageAdapter: PageAdapter
    lateinit var list: ArrayList<Food>
    lateinit var handler: Handler
    lateinit var runnable: Runnable
    lateinit var firstRvAdapter: FirstRvAdapter

    lateinit var firestrore:FirebaseFirestore

    lateinit var l: ArrayList<MainScreen>
    lateinit var appDatabase: AppDatabase
    lateinit var a:ArrayList<String>
    lateinit var b:ArrayList<Food>
    private  val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentHomeBinding.inflate(inflater,container,false)

        appDatabase= AppDatabase.getInstance(requireContext())


        firestrore= FirebaseFirestore.getInstance()

        loaddata()

//        for (i in 1..4){
//
//            a =ArrayList<String>()
//            a.add("kartoshka")
//           var b=Food("","","","","",a)
//            firestrore.collection("Kun retsepti")
//                .add(b)
//        }









        handler= Handler(Looper.getMainLooper())
         runnable=object:Runnable{
            override fun run() {
                if (binding.vp.currentItem == b.size - 1) {
                    binding.vp.currentItem = 0;
            } else {
                    binding.vp.currentItem = binding.vp.currentItem + 1;
            }
            handler.postDelayed(this, 5000);
            }

        }
        handler.postDelayed(runnable,5000)

        return binding.root
    }


    override fun onResume() {
        super.onResume()

    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }

    private fun loaddata() {
         b=ArrayList()
        firestrore.collection("Kun retsepti").get()
            .addOnSuccessListener {
                var documents=it.documents
                for(document in documents){
                    var food=document.toObject(Food::class.java)
                    b.add(food!!)
                }
                if(b.size>0){
                    pageAdapter= PageAdapter(b,childFragmentManager)
                    binding.vp.adapter=pageAdapter
                    binding.dotsIndicator.setViewPager(binding.vp)
                }

            }
        list= ArrayList()
        var favouritelist=ArrayList<Food>()
        l= ArrayList()
      var   a= ArrayList<Food>()
        firestrore.collection("Yangi retseptlar").get()
            .addOnSuccessListener {
                var documents=it.documents
                for (document in documents) {
                    var food=document.toObject(Food::class.java)
                    if (food != null) {
                        list.add(food)
                    }

                }

               firestrore.collection("Kategoriya").get()
                   .addOnSuccessListener { dd->
                       var d=dd.documents
                       for (documentSnapshot in d) {
                           var category=documentSnapshot.toObject(Category::class.java)
                           a.add(Food(category!!.name,"",category?.image,"","",null))

                       }
                       for (i in appDatabase.foodDao().getAllFoods()){
                           favouritelist.add(Food(i.foodname,i.preparationg,i.image,i.time,i.category,i.ingredents))
                       }
                       l.add(MainScreen("Yangi retseptalar",list))
                       l.add(MainScreen("Kategoriylar",a))
                       l.add(MainScreen("Yoqtirilganlar",favouritelist))
                       firstRvAdapter= FirstRvAdapter(l,object :FirstRvAdapter.onClick{
                           override fun onNextClick(mainScreen: MainScreen, position: Int) {
                               when (position) {
                                   1 -> {
                                       var vp=    requireActivity().findViewById<ViewPager>(R.id.viewpager)

                                       vp.currentItem=1
                                   }
                                   0 -> {
                                        var bundle=Bundle()
                                       bundle.putString("name","Yangi retseptlar")
                                       findNavController().navigate(R.id.categoryInfoFragment,bundle)
                                   }
                                   2 -> {
                                       var vp=    requireActivity().findViewById<ViewPager>(R.id.viewpager)

                                       vp.currentItem=2
                                   }
                               }
                           }

                           override fun onitemClick(food: Food, mainScreen: MainScreen,position:Int) {


                               when (position) {
                                   0 -> {
                                       var bundle=Bundle()
                                       bundle.putSerializable("food",food)
                                       findNavController().navigate(R.id.infoFragment,bundle,
                                           navOptions {
                                               anim {
                                                   enter = android.R.anim.fade_in
                                                   exit = android.R.animator.fade_out
                                               }
                                           })
                                   }
                                   1 -> {
                                       var bundle=Bundle()
                                       bundle.putString("name",food.foodname)
                                       findNavController().navigate(R.id.categoryInfoFragment,bundle,

                                           navOptions {
                                               anim {
                                                   enter = android.R.anim.fade_in
                                                   exit = android.R.animator.fade_out
                                               }
                                           })
                                   }
                                   2 -> {
                                       var bundle=Bundle()
                                      bundle.putSerializable("food",food)
                                       findNavController().navigate(R.id.infoFragment,bundle,
                                           navOptions {
                                               anim {
                                                   enter = android.R.anim.fade_in
                                                   exit = android.R.animator.fade_out
                                               }
                                           })
                                   }
                               }
                           }

                       })
                       binding.rv.adapter=firstRvAdapter
                       binding.animationView.visibility=View.INVISIBLE

                   }


            }
         }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}