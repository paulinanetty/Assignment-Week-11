package com.nmp.week4anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nmp.week4anmp.model.Student

class DetailViewModel {
    val studentLD = MutableLiveData<Student>()

    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1
    }

}