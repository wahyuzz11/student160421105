package com.example.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Student
import kotlin.math.log

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() , ButtonClickListener
{

    var id = ""
    class StudentViewHolder(val binding : StudentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent:  ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
//    pakai .text kalau sama text view kalau set text itu jika edit text
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        holder.binding.student = studentList[position]
        holder.binding.listener = this


    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
        Log.d("test-adapter",v.tag.toString())
    }



}
