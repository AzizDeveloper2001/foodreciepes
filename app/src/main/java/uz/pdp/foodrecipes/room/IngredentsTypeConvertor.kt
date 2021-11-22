package uz.pdp.foodrecipes.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredentsTypeConvertor {
    @TypeConverter
    fun fromString(value:String?):ArrayList<String>{
        val listtype= object :TypeToken<ArrayList<String>>(){}.type


        return Gson().fromJson(value,listtype)
    }
    @TypeConverter
    fun fromArraylist(list:ArrayList<String?>):String{
             return Gson().toJson(list)
    }
}