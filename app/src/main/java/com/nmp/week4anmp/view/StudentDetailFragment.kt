package com.nmp.week4anmp.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nmp.week4anmp.R
import com.nmp.week4anmp.databinding.FragmentStudentDetailBinding
import com.nmp.week4anmp.model.Student
import com.nmp.week4anmp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment(),ButtonDetailClickListener {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener=this
        binding.student = Student("","","","","https://randomuser.me/api/portraits/men/74.jpg")

        val studentId = arguments?.getString("studentId")

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(studentId.toString())
        observeViewModel()

    }

    fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it
//            var student = it
//
//
//            binding.btnupdate.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(
//                            student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_24
//                        )
//                    }
//
//            }

        })
    }

    override fun onButtonDetailClick(v: View) {
        TODO("Not yet implemented")
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(context,"Successfully Updated" + v.tag.toString(), Toast.LENGTH_SHORT).show()
    }

}



