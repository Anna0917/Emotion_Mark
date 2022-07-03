package com.asi.emotionmark.presentation.ui.mark_detail

import android.os.Bundle
import android.text.format.DateFormat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.asi.emotionmark.R
import com.asi.emotionmark.data.model.EmotionMark
import java.util.*

private const val TAG = "MarkDetailFragment"
private const val ARG_MARK_ID = "mark_id"

// возможно нужно всплывающее диалоговое окно, если пользователь не сохранял текущие изменения?...
class MarkDetailFragment : Fragment() {

    private val markDetailViewModel: MarkDetailViewModel by lazy {
        ViewModelProvider(this, MarkDetailViewModelFactory())
            .get(MarkDetailViewModel::class.java)
    }


    private var isNewMark: Boolean = true
    private lateinit var mark: EmotionMark

    private lateinit var markDateText: TextView
    private lateinit var markRating: RatingBar
    private lateinit var markEmotionField: EditText
    private lateinit var markDescriptionField: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mark = EmotionMark()
        if(!isNewMark){
            val markId: UUID = arguments?.getSerializable(ARG_MARK_ID) as UUID
            markDetailViewModel.loadMark(markId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mark_detail, container, false)

        markDateText = view.findViewById(R.id.mark_date)
        markRating = view.findViewById(R.id.mark_rating)
        markEmotionField = view.findViewById(R.id.mark_emotion) as EditText
        markDescriptionField = view.findViewById(R.id.mark_description) as EditText
        saveButton = view.findViewById(R.id.save_button)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        markDetailViewModel.markLiveData.observe(
            viewLifecycleOwner,
            Observer { mark ->
                mark?.let {
                    this.mark = mark
                    updateUI(mark)
                }
            }
        )
    }


    override fun onStart() {
        super.onStart()
        markDateText.apply {
            text = DateFormat.format("dd.mm.yyyy, EE  HH:mm", mark.date)
        }
        saveButton.setOnClickListener {
            getDataFromUI()
            if (isNewMark){
                markDetailViewModel.addMark(mark)
                isNewMark = false
            }
            else
                markDetailViewModel.saveMark(mark)
        }
    }

    private fun updateUI(mark: EmotionMark) {
        markDateText.text = DateFormat.format("dd.mm.yyyy, EE | HH:mm", mark.date)
        markRating.rating = mark.rating.toFloat()
        markEmotionField.setText(mark.emotion)
        markDescriptionField.setText(mark.description)
    }

    private fun getDataFromUI() {
        mark.rating = markRating.rating.toInt()
        mark.emotion = markEmotionField.text.toString()
        mark.description = markDescriptionField.text.toString()
    }

    companion object {
        fun newInstance(markId: UUID?): MarkDetailFragment {
            return if (markId != null) {
                val args = Bundle().apply {
                    putSerializable(ARG_MARK_ID, markId)
                }
                MarkDetailFragment().apply {
                    arguments = args
                    isNewMark = false
                }
            } else
                MarkDetailFragment()
        }
    }

}