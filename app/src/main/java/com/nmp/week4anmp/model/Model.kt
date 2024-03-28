package com.nmp.week4anmp.model

import com.google.gson.annotations.SerializedName

data class Student(

    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val dob:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String

)

data class Cat(

    val id:String?,
    val name:String?,
    val breed:String?,
    val age:String?,
    val color:String?,
    val owner:CatOwner?,
    val traits:List<String>?,
    val images:String?,

)

data class CatOwner(

    val name:String?,
    val age:String?,
    val address:String?,

    )
