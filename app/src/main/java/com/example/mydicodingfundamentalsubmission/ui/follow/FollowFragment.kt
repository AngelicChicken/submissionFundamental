package com.example.mydicodingfundamentalsubmission.ui.follow

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
//    private lateinit var followersViewModel: followersViewModel
//    private lateinit var followingViewModel: followingViewModel

    private var position: Int = 0
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            val followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                followersViewModel::class.java)
            followersViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
            followersViewModel.findFollowers(username ?: "")
            followersViewModel.followers.observe(viewLifecycleOwner) { followers ->
                setFollowersData(followers)
            }

        } else {
            val followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                followingViewModel::class.java)
            followingViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }

            followingViewModel.findFollowing(username ?: "")
            followingViewModel.following.observe(viewLifecycleOwner) { following ->
                setFollowingData(following)
        }}
    }
    private fun setFollowersData(followers: List<User?>){
        val adapter = UserAdapter()
        adapter.submitList(followers)
        binding.rvFollow.adapter = adapter

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)
    }

    private fun setFollowingData(following: List<User?>){
        val adapter = UserAdapter()
        adapter.submitList(following)
        binding.rvFollow.adapter = adapter

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "username"
    }
}