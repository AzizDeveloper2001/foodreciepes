package uz.pdp.foodrecipes.models

import java.io.Serializable

class AdviceCategory : Serializable {
     var name:String?=null
     var image: String?=null
    var desc:String?=null
    var list:ArrayList<String>?=null

     constructor(name: String?, image: String?,desc:String?,list:ArrayList<String>?) {
         this.name = name
         this.image = image
         this.desc=desc
         this.list=list
     }

     constructor()

 }