package com.nmp.week4anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp.week4anmp.model.Cat

class CatViewModel(application: Application): AndroidViewModel(application) {

   val catsLD = MutableLiveData<ArrayList<Cat>>()
 val catsLoadErrorLD = MutableLiveData<Boolean>()
 val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh() {
        loadingLD.value = true
        catsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.12.200.45/anmpexercise/cats.json"


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {

                val sType = object : TypeToken<List<Cat>>() { }.type
                val result = Gson().fromJson<List<Cat>>(it, sType)
                catsLD.value = result as ArrayList<Cat>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                catsLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}