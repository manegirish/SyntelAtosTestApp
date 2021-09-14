package com.test.syntelatostestapp.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.databinding.DialogFragmentPeopleDetailsBinding
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.utils.SnackResponse
import com.test.syntelatostestapp.variables.JsonNames

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */
internal class DialogFragmentPeopleDetails private constructor() : DialogFragment(), View.OnClickListener {

    private lateinit var peopleDetailsBinding: DialogFragmentPeopleDetailsBinding
    private var peopleItem: People? = null

    companion object {
        fun newInstance(people: People): DialogFragmentPeopleDetails {
            val args = Bundle()
            args.putParcelable(JsonNames.PEOPLE, people)
            val fragment = DialogFragmentPeopleDetails()
            fragment.arguments = args
            return fragment
        }
    }

    private fun applyListeners() {
        peopleDetailsBinding.onClick = this
    }

    private fun init() {
        peopleDetailsBinding.peopleItem = peopleItem
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        peopleDetailsBinding = DialogFragmentPeopleDetailsBinding.inflate(inflater, container, false)

        val args = requireArguments()
        if (args.containsKey(JsonNames.PEOPLE)) {
            peopleItem = args.getParcelable(JsonNames.PEOPLE)
        }
        if (peopleItem == null) {
            SnackResponse.somethingWentWrong(peopleDetailsBinding.root)
            dismissAllowingStateLoss()
        } else {
            init()
            applyListeners()
        }
        return peopleDetailsBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = FrameLayout(requireActivity())
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val dialog = Dialog(requireActivity())
        dialog.setContentView(root)
        val window = dialog.window
        if (window != null) {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window.setDimAmount(0.4f)
        }
        dialog.setCancelable(true)
        return dialog
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_people_details_dismiss -> {
                dismissAllowingStateLoss()
            }
            R.id.tv_people_details_email -> {
                peopleDetailsBinding.tvPeopleDetailsCall.highlightColor = ContextCompat.getColor(requireContext(), R.color.transparent)
            }
            R.id.tv_people_details_call -> {
                peopleDetailsBinding.tvPeopleDetailsEmail.highlightColor = ContextCompat.getColor(requireContext(), R.color.transparent)
            }
        }
    }

}