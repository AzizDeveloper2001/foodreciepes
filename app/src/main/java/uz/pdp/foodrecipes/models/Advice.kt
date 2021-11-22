package uz.pdp.foodrecipes.models

import java.io.Serializable

class Advice : Serializable {
     var name:String?=null
     var image: String?=null

     constructor(name: String?, image: String?) {
         this.name = name
         this.image = image
     }

     constructor()

 }