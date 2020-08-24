package com.example.primetime.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.androiddata.ui.main.RecyclerAdapter
import com.example.primetime.R
import com.example.primetime.databinding.FragmentMoviesBinding
import com.example.primetime.model.Content
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment(), RecyclerAdapter.ItemClickListner {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: RecyclerAdapter
    private val viewModel  by viewModel<MoviesViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        viewModel.moviesData.observe(this, Observer {
            activity?.title = it.page.title
            adapter = RecyclerAdapter(requireContext(), it.page.contentitems.content, this)
            binding.apply {
                this.recyclerView.adapter = adapter
               // this.recyclerView.addItemDecoration(CustomDecoorator(30))
            }

        })

        return binding.root


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
          if(item.itemId== R.id.search) {
              val searchView: SearchView = item.actionView as SearchView
              Log.e("TAG", "onQueryTextSubmit: ")
              searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                  override fun onQueryTextSubmit(query: String?): Boolean {
                      item.collapseActionView()
                      return false
                  }

                  override fun onQueryTextChange(newText: String?): Boolean {
                      if (newText?.length ?: 0 > 2) {
                          adapter.filter.filter(newText)
                      } else {
                          adapter.filter.filter(null)
                      }

                      return false
                  }
              })
          }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(monster: Content) {
        TODO("Not yet implemented")
    }


   /* var mScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            val visibleItemCount: Int? = recyclerView.layoutManager?.getChildCount()
            val totalItemCount: Int? = recyclerView.layoutManager?.getItemCount()
            val pastVisibleItems: Int? = recyclerView.layoutManager?.findFirstVisibleItemPosition()
            if (pastVisibleItems != null) {
                if (pastVisibleItems + visibleItemCount!! >= totalItemCount!!) {
                    //End of list
                }
            }
        }
    }
*/
}