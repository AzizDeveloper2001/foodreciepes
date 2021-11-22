package uz.pdp.foodrecipes.room.Database

import android.content.Context
import androidx.room.*
import uz.pdp.foodrecipes.room.Dao.FoodDao
import uz.pdp.foodrecipes.room.Entities.FoodEntitiy
import uz.pdp.foodrecipes.room.IngredentsTypeConvertor


@Database(entities = [FoodEntitiy::class],version = 1)
@TypeConverters(IngredentsTypeConvertor::class)
    abstract class AppDatabase:RoomDatabase() {
    abstract fun foodDao():FoodDao
        companion object{
            private var appDatabase:AppDatabase?=null
            @Synchronized
            fun getInstance(context: Context):AppDatabase{
                if(appDatabase==null){
                    appDatabase= Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()


                }
                return appDatabase!!
            }
        }
    }
