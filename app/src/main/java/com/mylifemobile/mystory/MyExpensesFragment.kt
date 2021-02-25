package com.mylifemobile.mystory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.AdapterView.VISIBLE
import androidx.fragment.app.Fragment
import com.mylifemobile.R
import com.mylifemobile.api.RestClientManager
import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.TransactionModel
import com.mylifemobile.session.SessionHandler
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class MyExpensesFragment : Fragment() {

    private val restClient: RestClientManager = RestClientManager()
    private lateinit var sessionHandler: SessionHandler
    private lateinit var categories: List<CategoryModel>
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.expenses_tab, container, false)
        sessionHandler = SessionHandler(requireActivity())

        fillTypesSpinner(view)
        createExpensesButton(view)
        fillCategoriesSpinner(view)

        return view
    }

    private fun fillTypesSpinner(view: View) {
        val spinner = view.findViewById<Spinner>(R.id.add_transactions_type_spinner)

        val types = arrayListOf<String>("", "Ingreso", "Egreso")
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, types
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View,
                position: Int, id: Long) {
                if(parentView!!.getItemAtPosition(position) != "") {
                    type = parentView.getItemAtPosition(position) as String
                    adapter.remove("")
                    makeViewVisible()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    private fun makeViewVisible() {
        requireView().findViewById<TextView>(R.id.add_transactions_amount_label).visibility = VISIBLE
        requireView().findViewById<EditText>(R.id.add_transactions_input).visibility = VISIBLE
        requireView().findViewById<TextView>(R.id.add_transactions_category_label).visibility = VISIBLE
        requireView().findViewById<Spinner>(R.id.add_transactions_category_spinner).visibility = VISIBLE
        requireView().findViewById<Button>(R.id.add_transactions_button).visibility = VISIBLE
    }


    private fun createExpensesButton(view: View) {
        val expensesButton = view.findViewById<Button>(R.id.add_transactions_button)

        expensesButton.setOnClickListener {
            val amountInput = view.findViewById<EditText>(R.id.add_transactions_input).text.toString()
            val categoryName = view.findViewById<Spinner>(R.id.add_transactions_category_spinner).selectedItem
            var categoryId = -1

            categories.forEach { categoryModel ->
                if(categoryModel.name == categoryName) {
                   categoryId = categoryModel.id
                }
            }

            if (amountInput != "" && categoryId != -1) {
                doAsync {
                    val expense = TransactionModel(
                        amount = amountInput.toBigDecimal(),
                        categoryId = categoryId,
                        userId = sessionHandler.getUserId(),
                        date = Date(),
                        type = type
                    )
                    restClient.createExpense(expense)
                    uiThread {
                        Toast.makeText(context, R.string.transactions_successful_add, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(context, R.string.transaction_required_fields, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun fillCategoriesSpinner(view: View) {
        doAsync {
            val spinner = view.findViewById<Spinner>(R.id.add_transactions_category_spinner)

            categories = restClient.getCategories()

            val categoriesName = categories.map { categoryModel -> categoryModel.name }
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, categoriesName
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            requireActivity().runOnUiThread(Runnable {
                spinner.adapter = adapter
            })
        }
    }
}