package com.mylifemobile.mystory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mylifemobile.R
import com.mylifemobile.api.RestClientManager
import com.mylifemobile.api.model.ExpensesModel
import org.jetbrains.anko.doAsync
import java.util.*

class MyExpensesFragment : Fragment() {

    private val restClient: RestClientManager = RestClientManager()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.expenses_tab, container, false)

        val button = view.findViewById<Button>(R.id.add_expenses_button)
        button.setOnClickListener {
            val amountInput = view.findViewById<EditText>(R.id.add_expenses_input).text.toString()

            if (amountInput != "") {
                doAsync {
                    val expense = ExpensesModel(amount = amountInput.toInt(),
                                                categoryId = 1,
                                                userId = 1,
                                                date = Date())
                    restClient.createExpense(expense)
                }
            } else {
                Toast.makeText(context, R.string.expenses_required_fields, Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}