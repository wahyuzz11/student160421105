package com.example.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentStudentListBinding
import com.example.studentapp.viewmodel.ListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private lateinit var binding:FragmentStudentListBinding
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = studentListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.recView.visibility = View.VISIBLE


            } else {
                binding.recView.visibility = View.VISIBLE
            }
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError?.visibility = View.VISIBLE

            }else{
                binding.txtError?.visibility = View.GONE
            }
        })
    }


}