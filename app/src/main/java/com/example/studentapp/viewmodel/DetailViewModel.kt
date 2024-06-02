package com.example.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val detailLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag2"
    private var queue: RequestQueue? = null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)
                studentLD.value = result
                loadingLD.value = false
                Log.d("showvolley", it)
            },
            {
                Log.d("showvolley", it.toString())
                detailLoadErrorLD.value = false
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