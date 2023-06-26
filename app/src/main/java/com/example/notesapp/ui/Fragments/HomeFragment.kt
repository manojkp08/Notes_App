package com.example.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    val viewModel: NotesViewModel by viewModels()

    var oldMyNotes = arrayListOf<Notes>()

    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner, {notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            //StaggeredGrilayoutManager for the recyclerView.
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.allNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(), notesList)
            binding.allNotes.adapter = adapter
        })

        binding.btnFilterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, {notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //StaggeredGrilayoutManager for the recyclerView.
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.allNotes.layoutManager = staggeredGridLayoutManager
                //We do not have to set adapters like this.
//                binding.allNotes.adapter = NotesAdapter(requireContext(), notesList)
                //We have to set adapters like this.
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotes.adapter = adapter
            })
        }

        binding.btnFilterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, {notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //StaggeredGrilayoutManager for the recyclerView.
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.allNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotes.adapter = adapter
            })
        }

        binding.btnFilterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, {notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //StaggeredGrilayoutManager for the recyclerView.
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.allNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding.btnFilter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, {notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //StaggeredGrilayoutManager for the recyclerView.
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.allNotes.layoutManager = staggeredGridLayoutManager
//                binding.allNotes.adapter = NotesAdapter(requireContext(), notesList)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotes.adapter = adapter
            })
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {

        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNotes){
            if(i.title!!.contains(p0!!) || i.subtitle!!.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}

