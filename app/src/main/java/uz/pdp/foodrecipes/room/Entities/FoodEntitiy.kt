package uz.pdp.foodrecipes.room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.pdp.foodrecipes.models.Food

@Entity
data class FoodEntitiy (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    var foodname:String,
    var preparationg:String,
    var image:String,
    var time:String,
    var category:String,
    val ingredents:ArrayList<String>
        )


