package com.example.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNotesFragment : Fragment() {

    private lateinit var binding: FragmentEditNotesBinding
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)

        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.edtTitleEditNotes.setText(oldNotes.data.title)
        binding.edtSubtitleEditNotes.setText(oldNotes.data.subtitle)
        binding.notesEditNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" -> {
                priority = "1"
                binding.greenEditNotes.setImageResource(R.drawable.baseline_circle_24)
                binding.redEditNotes.setImageResource(0)
                binding.yellowEditNotes.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.yellowEditNotes.setImageResource(R.drawable.baseline_circle_24)
                binding.redEditNotes.setImageResource(0)
                binding.greenEditNotes.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.redEditNotes.setImageResource(R.drawable.baseline_circle_24)
                binding.greenEditNotes.setImageResource(0)
                binding.yellowEditNotes.setImageResource(0)
            }
        }

        binding.greenEditNotes.setOnClickListener {
            priority = "1"
            binding.greenEditNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.redEditNotes.setImageResource(0)
            binding.yellowEditNotes.setImageResource(0)
        }

        binding.yellowEditNotes.setOnClickListener {
            priority = "2"
            binding.yellowEditNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.redEditNotes.setImageResource(0)
            binding.greenEditNotes.setImageResource(0)
        }

        binding.redEditNotes.setOnClickListener {
            priority = "3"
            binding.redEditNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.greenEditNotes.setImageResource(0)
            binding.yellowEditNotes.setImageResource(0)
        }

        binding.btnDeleteNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitleEditNotes.text.toString()
        val subtitle = binding.edtSubtitleEditNotes.text.toString()
        val notes = binding.notesEditNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(oldNotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority)
        viewModel.updateNotes(data)
//
        Toast.makeText(requireContext(), "Notes updated sucesfully", Toast.LENGTH_SHORT).show()


        Navigation.findNavController(it!!)
            .navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.btnYesEditNotes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.btnNoEditNotes)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}
