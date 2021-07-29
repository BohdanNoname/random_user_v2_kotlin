package com.nedashkivskyi.randomuser.ui.fragments.peopleList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.randomuser.databinding.FragmentPeopleListBinding
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.ui.activities.MainActivity
import com.nedashkivskyi.randomuser.utils.SortedPeopleList
import com.nedashkivskyi.randomuser.utils.Status
import com.nedashkivskyi.randomuser.viewModel.DbViewModel
import com.nedashkivskyi.randomuser.viewModel.PersonViewModel
import javax.inject.Inject

class PeopleListFragment : Fragment() {

    private lateinit var binding: FragmentPeopleListBinding
    @Inject lateinit var personViewModel: PersonViewModel
    @Inject lateinit var dbViewModel: DbViewModel
    @Inject lateinit var adapter: AdapterPersonRecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        initConfig(inflater, container)

        personViewModel.res.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING ->
                    Log.d("TAG", "onCreateView: ${Status.LOADING}")
                Status.SUCCESS ->
                    dbViewModel.insert(it.data?.results?.get(0)!!)
                Status.ERROR ->
                    Log.d("TAG", "onCreateView: ${Status.ERROR}")
            }
        })

        dbViewModel.allPeople.observe(viewLifecycleOwner, Observer {people ->
            recyclerViewRealization(binding.recyclerView, people)
        })
        return binding.root
    }

    private fun initConfig(inflater: LayoutInflater, container: ViewGroup?){
        binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        binding.personViewModel = personViewModel
    }

    private fun recyclerViewRealization(recyclerView: RecyclerView, people: List<Result>){
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter.result = SortedPeopleList.sortedByAgeFromOldToYong(people)
        recyclerView.adapter = adapter
    }
}