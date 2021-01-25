package com.gcr.room.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gcr.room.R
import com.gcr.room.database.User
import com.gcr.room.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.etNameUser.setText(args.currentUser.name)
        view.etLastName.setText(args.currentUser.lastName)
        view.etAgeUser.setText(args.currentUser.age.toString())

        view.btnAddUser.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
    }

    private fun updateItem(){

        val name = etNameUser.text.toString()
        val lastName = etLastName.text.toString()
        val age = Integer.parseInt(etAgeUser.text.toString())

        if(inputCheck(name,lastName,age.toString())){
            val updateCurrentUser = User(args.currentUser.id,name,lastName,age)
            viewModel.updateUser(updateCurrentUser)
            Toast.makeText(requireContext(),"Actualizado correctamente",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Todos los campos son necesarios",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName:String,lastName:String,age:String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu){
            deleteUser()
            Log.d("FR", "Click boton")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_,_ ->
            viewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Eliminado ${args.currentUser.name} ",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            //findNavController().popBackStack()
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Borrar ${args.currentUser.name}")
        builder.setMessage("¿Estas seguro de borrar usuario ?")
        builder.create().show()
    }

}