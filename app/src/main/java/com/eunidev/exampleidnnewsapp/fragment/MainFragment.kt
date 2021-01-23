package com.eunidev.exampleidnnewsapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eunidev.exampleidnnewsapp.R
import com.eunidev.exampleidnnewsapp.adapter.RVMainAdapter
import com.eunidev.exampleidnnewsapp.data.NewsResponse
import com.eunidev.exampleidnnewsapp.database.NewsArticles
import com.eunidev.exampleidnnewsapp.other.Injection
import com.eunidev.exampleidnnewsapp.viewModel.NewsViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var TAG = "MainFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: RVMainAdapter
    private lateinit var activity: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        this.activity = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = v.findViewById(R.id.recyclerView_MainFragment)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupUI()
    }

    fun setupUI() {
        adapter = RVMainAdapter(activity, viewModel.news.value ?: emptyList())
        Log.i(TAG, "viewModel.news.value: ${viewModel.news.value}")
        //Toast.makeText(activity, viewModel.news.value?.size.toString(), Toast.LENGTH_SHORT).show();
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }
        })
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(NewsViewModel::class.java)
        viewModel.loadData()

        viewModel.news.observe(this, renderNews)
        viewModel.onMsgError.observe(this, onMsgErrorObserver)
        viewModel.isListEmpty.observe(this, listEmptyObserver)
    }

    private val listEmptyObserver = Observer<Boolean> {
        Log.i(TAG, "isListEmpty? $it")
        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show();
    }

    private val onMsgErrorObserver = Observer<Any> {
        Log.i(TAG, "onMessageError $it")
        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show();
    }

    private val renderNews = Observer<List<NewsArticles>> {
        Log.i(TAG, "data updated $it")
        adapter.update(it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}