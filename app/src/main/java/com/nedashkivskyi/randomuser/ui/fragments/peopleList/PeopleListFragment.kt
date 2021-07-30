package com.nedashkivskyi.randomuser.ui.fragments.peopleList

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.randomuser.R
import com.nedashkivskyi.randomuser.databinding.FragmentPeopleListBinding
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.ui.activities.MainActivity
import com.nedashkivskyi.randomuser.utils.Status
import com.nedashkivskyi.randomuser.utils.sorting.SortedPeopleList
import com.nedashkivskyi.randomuser.utils.sorting.SortedType
import com.nedashkivskyi.randomuser.viewModel.DbViewModel
import com.nedashkivskyi.randomuser.viewModel.PersonViewModel
import com.nedashkivskyi.randomuser.viewModel.SortedTypeViewModel
import javax.inject.Inject

class PeopleListFragment : Fragment() {

    private lateinit var binding: FragmentPeopleListBinding
    @Inject
    lateinit var personViewModel: PersonViewModel
    @Inject
    lateinit var dbViewModel: DbViewModel
    @Inject
    lateinit var adapter: AdapterPersonRecyclerView
    @Inject
    lateinit var sortedTypeViewModel: SortedTypeViewModel
    @Inject
    lateinit var dialog: DeleteAllPeopleListAlertDialog

    private lateinit var type: SortedType
    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        initConfig(inflater, container)

        sortedTypeViewModel.sortedType.observe(viewLifecycleOwner, {
            type = it
        })

        personViewModel.res.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING ->
                    Log.d("TAG", "onCreateView: ${Status.LOADING}")
                Status.SUCCESS ->
                    dbViewModel.insert(it.data?.results?.get(0)!!)
                Status.ERROR ->
                    Log.d("TAG", "onCreateView: ${Status.ERROR}")
            }
        })

        dbViewModel.allPeople.observe(viewLifecycleOwner, {
            recyclerViewRealization(it, type)
        })

        return binding.root
    }

    private fun initConfig(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        binding.personViewModel = personViewModel
        recyclerView = binding.recyclerView
        setHasOptionsMenu(true)
    }

    private fun recyclerViewRealization(people: List<Result>, _type: SortedType) {
        this.type = _type
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter.result = SortedPeopleList.sortedPeopleList(people, _type)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll ->
                dialog.show(activity?.supportFragmentManager!!, "DeleteAlertDialog")
            R.id.fromOldToNew ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.FROM_OLD_TO_NEW)
            R.id.fromNewToOld ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.FROM_NEW_TO_OLD)
            R.id.byAgeFromYoungToOld ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.BY_AGE_FROM_YOUNG_TO_OLD)
            R.id.byAgeFromOldToYoung ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.BY_AGE_FROM_OLD_TO_YOUNG)
            R.id.bySurnameFromAtoZ ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.BY_SURNAME_A_TO_Z)
            R.id.bySurnameFromZtoA ->
                recyclerViewRealization(dbViewModel.allPeople.value!!, SortedType.BY_SURNAME_Z_TO_A)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        sortedTypeViewModel.changeSortedType(type)
    }
}