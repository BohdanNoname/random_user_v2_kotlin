package com.nedashkivskyi.randomuser.ui.fragments.personData
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.nedashkivskyi.randomuser.R
import com.nedashkivskyi.randomuser.databinding.FragmentPersonDataBinding
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.ui.activities.MainActivity
import com.nedashkivskyi.randomuser.utils.SerializableConverter

class PersonDataFragment : Fragment() {

    private val args: PersonDataFragmentArgs by navArgs<PersonDataFragmentArgs>()
    private lateinit var binding: FragmentPersonDataBinding
    private lateinit var personArg: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initConfig(inflater, container)
        val result: Result = SerializableConverter.decodeFromString<Result>(personArg)
        binding.person = result
        return binding.root
    }

    private fun initConfig(inflater: LayoutInflater, container: ViewGroup?){
        binding = FragmentPersonDataBinding.inflate(layoutInflater, container, false)
        personArg = args.person
    }
}