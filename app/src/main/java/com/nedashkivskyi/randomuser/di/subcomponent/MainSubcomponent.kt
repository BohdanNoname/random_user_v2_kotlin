package com.nedashkivskyi.randomuser.di.subcomponent

import com.nedashkivskyi.randomuser.ui.activities.MainActivity
import com.nedashkivskyi.randomuser.ui.fragments.peopleList.PeopleListFragment
import com.nedashkivskyi.randomuser.ui.fragments.personData.PersonDataFragment
import dagger.Subcomponent


@Subcomponent
interface MainSubcomponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainSubcomponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: PeopleListFragment)
    fun inject(fragment: PersonDataFragment)
}