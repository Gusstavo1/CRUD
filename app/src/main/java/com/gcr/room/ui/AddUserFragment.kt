package com.gcr.room.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gcr.room.R
import com.gcr.room.database.User
import com.gcr.room.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*

class AddUserFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add_user, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddUser.setOnClickListener {
            insertDatatoDataBase()
        }
    }

    private fun insertDatatoDataBase(){
        val firstName = etNameUser.text.toString()
        val lastName = etLastName.text.toString()
        val age = etAgeUser.text.toString()

        if(inputCheck(firstName,lastName,age)){
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Usuario Agregado",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addUserFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Necesita todos los campos",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName:String,lastName:String,age:String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}