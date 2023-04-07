package com.example.laba2ops

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba2ops.Animals.Animal
import com.example.laba2ops.databinding.FragmentFirstBinding


class FragmentFirst : Fragment(), AnimalsAdapters.Listener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var animals = ArrayList<Animal>()
    lateinit var adapter: AnimalsAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var lion = Animal(
            "Cat",
            "Real little king",
            getString(R.string.urlCat),
            getString(R.string.allDescriptionCat)
        )
        var zebra = Animal(
            "Dog",
            "Faithful friend",
            getString(R.string.urlDog),
            getString(R.string.allDescriptionDog)
        )
        var mouse = Animal(
            "Owl",
            "Symbol of wisdom",
            getString(R.string.urlOwl),
            getString(R.string.allDescriptionOwl)
        )

        animals.add(lion)
        animals.add(zebra)
        animals.add(mouse)
        adapter = AnimalsAdapters(this, animals, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(itemView: Int) {
        val bundle = bundleOf(
            FragmentSecond.numberInArray to itemView,
            FragmentSecond.name to animals.get(itemView).name,
            FragmentSecond.desc to animals.get(itemView).shortDescription,
            FragmentSecond.url to animals.get(itemView).urlPhoto,
            FragmentSecond.allDescription to animals.get(itemView).allDescription
        )
        findNavController().navigate(R.id.action_fragmentFirst_to_fragmentSecond, bundle)
    }


}