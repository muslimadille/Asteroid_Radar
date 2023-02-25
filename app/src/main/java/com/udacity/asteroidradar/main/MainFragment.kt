package com.udacity.asteroidradar.main
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    lateinit var  asteroidsAdapter:AsteroidListAdaptor

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        // init items recyclerview
        val recyclerView = binding.asteroidRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        asteroidsAdapter = AsteroidListAdaptor(emptyList())
        viewModel.objects.observe(viewLifecycleOwner) { objects ->
            if(objects!=null) asteroidsAdapter.setItems(objects)
        }
        viewModel.todayImage.observe(viewLifecycleOwner) { objects ->
            // set today image title
            binding.textView.text=objects.title
            // set today image url
            Picasso.with(context).load(objects.url).fit().centerCrop()
                .placeholder(R.drawable.placeholder_picture_of_day)
                .error(R.drawable.placeholder_picture_of_day)
                .into(binding.activityMainImageOfTheDay)

        }
        lifecycleScope.launch {
            try {
                viewModel.getTodayImage()
            } catch (e: Exception) {
                // handle error
            }
        }
        lifecycleScope.launch {
            try {
                viewModel.getWeeksAsteroid()
            } catch (e: Exception) {
                // handle error
            }
        }
            asteroidsAdapter.setOnItemClickListener { astorid->
            val action = MainFragmentDirections.actionShowDetail(astorid)
            findNavController().navigate(action)
        }
        recyclerView.adapter =asteroidsAdapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "View week asteroids"-> println("")
            "View today asteroids"->lifecycleScope.launch {
                try {
                    viewModel.getWeeksAsteroid()
                } catch (e: Exception) {
                    // handle error
                }
            }
            "View saved asteroids"-> println("")
        }
        return true
    }
}
