package com.nedashkivskyi.randomuser.ui.fragments.peopleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.randomuser.databinding.PersonRowItemBinding
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.utils.SerializableConverter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class AdapterPersonRecyclerView @Inject constructor()
    : RecyclerView.Adapter<AdapterPersonRecyclerView.PersonViewHolder>() {
    lateinit var result: List<Result>

    class PersonViewHolder(private val binding: PersonRowItemBinding) : RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): PersonViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PersonRowItemBinding.inflate(layoutInflater, parent, false)
                return PersonViewHolder(binding)
            }
        }

        fun bind(resultsList: List<Result>, position: Int) {
            val personData = resultsList[position]
            binding.person = SerializableConverter.encodeToString(personData)

            val portrait = binding.portrait
            val name = binding.name
            val surname = binding.surname
            val age = binding.age

            Picasso.get().load(personData.picture.large).into(portrait)
            name.text = (personData.name.title + " " + personData.name.first)
            surname.text = personData.name.last
            age.text = "${personData.dob.age} years old"
        }

//        private fun comparePeopleByIdFromNewToOld(): Comparator<Result> =
//            Comparator { o1, o2 -> o1._id.compareTo(o2._id) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(result, position)
    }

    override fun getItemCount(): Int {
        return result.size
    }

}