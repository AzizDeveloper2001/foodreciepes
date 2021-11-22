package uz.pdp.foodrecipes.models

import java.io.Serializable

class Food : Serializable {
    var foodname:String?=null
    var preparetion:String?=null
    var image:String?=null
    var time:String?=null
    var category:String?=null
    var ingredents:ArrayList<String>?=null

    constructor(
        foodname: String?,
        preparetion: String?,
        image: String?,
        time: String?,
        category: String?,
        ingredents:ArrayList<String>?
    ) {
        this.foodname = foodname
        this.preparetion = preparetion
        this.image = image
        this.time = time
        this.category = category
        this.ingredents=ingredents
    }

    constructor()

}