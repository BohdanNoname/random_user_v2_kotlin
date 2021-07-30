package com.nedashkivskyi.randomuser.ui.fragments.peopleList

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nedashkivskyi.randomuser.R
import com.nedashkivskyi.randomuser.viewModel.DbViewModel
import javax.inject.Inject

class DeleteAllPeopleListAlertDialog @Inject constructor(): DialogFragment() {

    @Inject lateinit var dbViewModel: DbViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.questionForDeletingAllPeople)
                .setPositiveButton("Yes") { dialog, which ->
                    dbViewModel.deleteAll()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                    Log.d("TAG", "onCreateDialog: NO")
                }
            builder.create()
        }!!
    }