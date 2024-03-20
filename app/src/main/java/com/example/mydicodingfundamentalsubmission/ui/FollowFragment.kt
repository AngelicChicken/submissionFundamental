package com.example.mydicodingfundamentalsubmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.databinding.FragmentFollowBinding
import com.example.mydicodingsubmissionawal.ui.UserAdapter

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private var position: Int = 0
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_SECTION_NUMBER)
            username = it.getString(ARG_USERNAME)
        }
        if (position == 1){
            binding = FragmentFollowBinding.inflate(layoutInflater)

            val followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(followersViewModel::class.java)
            followersViewModel.findFollowers(username ?: "")
            followersViewModel.followers.observe(viewLifecycleOwner) { followers ->
                setFollowersData(followers)
            }

            val layoutManager = LinearLayoutManager(requireActivity())
            binding.rvFollow.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
            binding.rvFollow.addItemDecoration(itemDecoration)

        } else {
            binding.sectionLabel.text = "Get Following $username"
        }
    }
    private fun setFollowersData(followers: List<User?>){
        val adapter = UserAdapter()
        adapter.submitList(followers)
        binding.rvFollow.adapter = adapter
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "username"
    }
}