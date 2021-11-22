package uz.pdp.foodrecipes.BottomMenuFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import uz.pdp.foodrecipes.R
import uz.pdp.foodrecipes.databinding.FragmentInfoBinding
import uz.pdp.foodrecipes.models.Food
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import uz.pdp.foodrecipes.adapters.InfoVpAdapter
import uz.pdp.foodrecipes.room.Database.AppDatabase
import uz.pdp.foodrecipes.room.Entities.FoodEntitiy


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InfoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentInfoBinding
    lateinit var appDatabase: AppDatabase
    var food=Food()
    lateinit var infoVpAdapter: InfoVpAdapter
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var foodentity:List<FoodEntitiy>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInfoBinding.inflate(inflater,container,false)
        firebaseFirestore= FirebaseFirestore.getInstance()


        appDatabase= AppDatabase.getInstance(requireContext())
        if(arguments?.getSerializable("food")!=null){
            food=arguments?.getSerializable("food") as Food
        }

        binding.backk.setOnClickListener {
            findNavController().popBackStack()
        }



     foodentity=  appDatabase.foodDao().getAllFoods()
       one@for (a in foodentity){
           var f=Food(a.foodname,a.preparationg,a.image,a.time,a.category,food.ingredents)
            if(f.foodname==food.foodname && f.preparetion==food.preparetion && f.image==food.image){
                binding.like.isLiked=true
                break@one
            }
        }
       loadInformation()
        return binding.root
    }
    fun loadInformation(){
        binding.collapse.title=food.foodname
        binding.collapse.setExpandedTitleColor(Color.parseColor("#009688"))
        binding.collapse.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        Picasso.get().load(food.image).placeholder(R.drawable.placeholderteo).into(binding.image)
        infoVpAdapter=InfoVpAdapter(food,childFragmentManager)
        binding.vp.adapter=infoVpAdapter
        binding.tablayout.setupWithViewPager(binding.vp)
        binding.like.setOnLikeListener(object :OnLikeListener{
            override fun liked(likeButton: LikeButton?) {
                var foodEntitiy=FoodEntitiy(foodname = food.foodname!!,
                    preparationg = food.preparetion!!,time = food.time!!,image = food.image!!,
                    category = food.category!!,ingredents = food.ingredents!!)
                appDatabase.foodDao().addFood(foodEntitiy)
                Toast.makeText(requireContext(), "Yoqtirilganlarga qo'shildi!", Toast.LENGTH_SHORT).show()


            }

            override fun unLiked(likeButton: LikeButton?) {
                one@for (f in appDatabase.foodDao().getAllFoods()) {
                    if(f.foodname==food.foodname && f.image==food.image && f.preparationg==food.preparetion){
                        appDatabase.foodDao().delete(f)
                        Toast.makeText(requireContext(), "Yoqtirilganlardan o'chirildi", Toast.LENGTH_SHORT).show()
                        break@one
                    }

                }
            }

        })
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
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}