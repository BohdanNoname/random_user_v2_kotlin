package com.nedashkivskyi.randomuser.ui.binding

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.ui.fragments.peopleList.PeopleListFragment
import com.nedashkivskyi.randomuser.ui.fragments.peopleList.PeopleListFragmentDirections
import com.nedashkivskyi.randomuser.ui.fragments.personData.PersonDataFragment
import com.nedashkivskyi.randomuser.viewModel.PersonViewModel

class BindingAdapter {
    companion object{
        @BindingAdapter("android:addNewPerson")
        @JvmStatic
        fun addNewPerson(btn: FloatingActionButton, viewModel: PersonViewModel){
            btn.setOnClickListener {
                viewModel.createQueryForNewPerson()
            }
        }

        @BindingAdapter("android:navigateToPersonDataFragment")
        @JvmStatic
        fun navigateToPersonDataFragment(layout: LinearLayout, person: String){
            
            layout.setOnClickListener { view ->
                Log.d("TAG", "navigateToPersonDataFragment:${person}")
                val action = PeopleListFragmentDirections.actionPeopleListFragmentToPersonDataFragment(person)
                view.findNavController().navigate(action)
            }
        }
    }
}