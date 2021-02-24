package com.mylifemobile.mystory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.mylifemobile.R
import com.mylifemobile.api.RestClientManager
import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.ExpensesModel
import com.mylifemobile.session.SessionHandler
import org.jetbrains.anko.doAsync
import java.util.*


class MyExpensesFragment : Fragment() {

    private val restClient: RestClientManager = RestClientManager()
    private lateinit var sessionHandler: SessionHandler
    private lateinit var categories: List<CategoryModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.expenses_tab, container, false)
        sessionHandler = SessionHandler(requireActivity())

        createExpensesButton(view)
        fillCategoriesSpinner(view)

        return view
    }

    private fun createExpensesButton(view: View) {
        val expensesButton = view.findViewById<Button>(R.id.add_expenses_button)

        expensesButton.setOnClickListener {
            val amountInput = view.findViewById<EditText>(R.id.add_expenses_input).text.toString()
            val categoryName = view.findViewById<Spinner>(R.id.add_expenses_category_spinner).selectedItem
            var categoryId = -1

            categories.forEach { categoryModel ->
                if(categoryModel.name == categoryName) {
                   categoryId = categoryModel.id
                }
            }

            if (amountInput != "" && categoryId != -1) {
                doAsync {
                    val expense = ExpensesModel(
                        amount = amountInput.toInt(),
                        categoryId = categoryId,
                        userId = sessionHandler.getUserId(),
                        date = Date()
                    )
                    restClient.createExpense(expense)
                }
            } else {
                Toast.makeText(context, R.string.expenses_required_fields, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun fillCategoriesSpinner(view: View) {
        doAsync {
            val spinner = view.findViewById<Spinner>(R.id.add_expenses_category_spinner)

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