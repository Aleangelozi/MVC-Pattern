package com.aleangelozi.mvcpattern

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CountriesView : AppCompatActivity() {

    private val listValues: MutableList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var list: ListView
    lateinit var controller: CountriesController
    lateinit var retryButton: Button
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        title = "Cities List"

        controller = CountriesController(this)

        list = findViewById(R.id.list)
        retryButton = findViewById(R.id.retryButton)
        progress = findViewById(R.id.progress)
        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.listText, listValues)

        list.adapter = adapter
        list.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    this@CountriesView, "You clicked " +
                            listValues[position], Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun setValues(values: List<String>) {
        listValues.clear()
        listValues.addAll(values)
        retryButton.visibility = View.GONE
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    fun onRetry(view: View) {
        controller.onRefresh()
        list.visibility = View.GONE
        retryButton.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    fun onError() {
        Toast.makeText(
            this@CountriesView, getString(R.string.error_message), Toast.LENGTH_SHORT
        ).show()
        progress.visibility = View.GONE
        list.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
    }
}