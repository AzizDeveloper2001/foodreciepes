package uz.pdp.foodrecipes.room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.pdp.foodrecipes.room.Entities.FoodEntitiy

@Dao
interface FoodDao {
    @Insert
    fun addFood( foodEntitiy: FoodEntitiy)

    @Delete
    fun delete(foodEntitiy: FoodEntitiy)

    @Query("select * from foodentitiy")
    fun getAllFoods():List<FoodEntitiy>

}
