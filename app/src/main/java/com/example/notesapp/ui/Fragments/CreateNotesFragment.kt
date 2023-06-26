package com.example.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {

    private lateinit var binding: FragmentCreateNotesBinding

    private var priority: String = "1"

    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.greenCreateNotes.setImageResource(R.drawable.baseline_circle_24)

        binding.greenCreateNotes.setOnClickListener {
            priority = "1"
            binding.greenCreateNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.redCreateNotes.setImageResource(0)
            binding.yellowCreateNotes.setImageResource(0)
        }

        binding.yellowCreateNotes.setOnClickListener {
            priority = "2"
            binding.yellowCreateNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.redCreateNotes.setImageResource(0)
            binding.greenCreateNotes.setImageResource(0)
        }

        binding.redCreateNotes.setOnClickListener {
            priority = "3"
            binding.redCreateNotes.setImageResource(R.drawable.baseline_circle_24)
            binding.greenCreateNotes.setImageResource(0)
            binding.yellowCreateNotes.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes()
        }

        return binding.root
    }

    private fun createNotes() {

        val title = binding.edtTitleCreateNotes.text.toString()
        val subtitle = binding.edtSubtitleCreateNotes.text.toString()
        val notes = binding.notesCreateNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority)
        viewModel.addNotes(data)
//
        Toast.makeText(requireContext(), "Notes created sucesfully", Toast.LENGTH_SHORT).show()


        Navigation.findNavController(binding.root)
            .navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}