package com.jlopez.mathtrainer.MainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jlopez.mathtrainer.R
import com.jlopez.mathtrainer.databinding.FragmentMainScreenBinding
import com.jlopez.mathtrainer.util.Constants.OPERATOR_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationListeners()
    }

    private fun setNavigationListeners() {
        val navController: NavController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
        binding.subtractionBtn.setOnClickListener{
            navController.navigate(MainScreenDirections.actionMainScreenToProblemFragment().setOperatorRequested("-"))
        }
        binding.additionBtn.setOnClickListener{
            navController.navigate(MainScreenDirections.actionMainScreenToProblemFragment().setOperatorRequested("+"))
        }
        binding.multiplyBtn.setOnClickListener{
            navController.navigate(MainScreenDirections.actionMainScreenToProblemFragment().setOperatorRequested("*"))
        }
        binding.divideBtn.setOnClickListener{
            navController.navigate(MainScreenDirections.actionMainScreenToProblemFragment().setOperatorRequested("/"))
        }
    }
}