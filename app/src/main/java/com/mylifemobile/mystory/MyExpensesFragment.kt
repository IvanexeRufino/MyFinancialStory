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

class MyExpensesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.expenses_tab, container, false)

        val button = view.findViewById<Button>(R.id.add_expenses_button)
        button.setOnClickListener {
            if (obligatoryFieldsNotNull(view)) {
                createExpense(view)
                Toast.makeText(context, R.string.expenses_successful_add, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, R.string.expenses_required_fields, Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun obligatoryFieldsNotNull(view: View): Boolean {
        return view.findViewById<EditText>(R.id.add_expenses_input).text.toString() != ""
    }

    private fun createExpense(view: View) {

    }
}