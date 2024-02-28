
package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.collections.ArrayList
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = arrayOf(
            R.drawable.h1,
            R.drawable.apple,
            R.drawable.amazon,
            R.drawable.whatsapp,
            R.drawable.insta,
            R.drawable.lamo,
            R.drawable.bcc,
            R.drawable.bugatti,
            R.drawable.csk,
            R.drawable.tata,
            R.drawable.colgate,
            R.drawable.netflix,
            R.drawable.britannia,
            R.drawable.tesla,
            R.drawable.mrf,
            R.drawable.sbi,
            R.drawable.indigo)

        titleList = arrayOf(
            "12112682",
            "APPLE",
            "AMAZON",
            "WHATSAPP",
            "INSTAGRAM",
            "LAMO",
            "BCC",
            "BUGATTI",
            "CSK",
            "TATA",
            "COLGATE",
            "NETFLIX",
            "BRITANNIA",
            "TESLA",
            "MRF",
            "SBI",
            "INDIGO")

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
    }

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}