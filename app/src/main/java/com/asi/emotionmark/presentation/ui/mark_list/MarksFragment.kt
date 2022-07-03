package com.asi.emotionmark.presentation.ui.mark_list

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asi.emotionmark.R
import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.presentation.ui.mark_list.MarksViewModel
import com.asi.emotionmark.presentation.ui.mark_list.MarksViewModelFactory
import java.util.*

private const val TAG = "MarksFragment"

class MarksFragment : Fragment() {
    private val marksViewModel: MarksViewModel by lazy {
        ViewModelProvider(this, MarksViewModelFactory())
            .get(MarksViewModel::class.java)
    }
    interface Callbacks {
        fun onMarkSelected(markId: UUID?)
    }
    private var callbacks: Callbacks? = null

    private lateinit var markRecyclerView: RecyclerView
    private var adapter: MarkAdapter? = MarkAdapter()

    private lateinit var addButton: Button
    private lateinit var addTextView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marks_list, container, false)
        markRecyclerView = view.findViewById(R.id.mark_recycler_view) as RecyclerView
        markRecyclerView.layoutManager = LinearLayoutManager(context)
        markRecyclerView.adapter = adapter
        addButton = view.findViewById(R.id.new_mark_btn)
        addTextView = view.findViewById(R.id.new_mark_tv)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marksViewModel.markList.observe(
            viewLifecycleOwner,
            Observer { marks ->
                adapter?.submitList(marks)
                if(marks.isEmpty()) updateUI(true)
                else updateUI(false)
            }
        )

    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_mark_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.new_mark -> {
                callbacks?.onMarkSelected(null)
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(isMarkListEmpty: Boolean){
        if(isMarkListEmpty){
            addTextView.visibility = View.VISIBLE
            addButton.visibility = View.VISIBLE
            addButton.setOnClickListener {
                callbacks?.onMarkSelected(null)
            }
        }
        else{
            addTextView.visibility = View.INVISIBLE
            addButton.visibility = View.INVISIBLE
        }
    }

    companion object {
        fun newInstance(): MarksFragment {
            return MarksFragment()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<EmotionMark>(){
        override fun areItemsTheSame(oldItem: EmotionMark, newItem: EmotionMark): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmotionMark, newItem: EmotionMark): Boolean {
            return oldItem == newItem
        }
    }

    private inner class MarkHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var mark: EmotionMark

        val dateTextView: TextView = itemView.findViewById(R.id.mark_date)
        val emotionTextView: TextView = itemView.findViewById(R.id.mark_emotion)
        val ratingTextView: TextView = itemView.findViewById(R.id.mark_rating)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            callbacks?.onMarkSelected(mark.id)
        }

        fun bind(mark: EmotionMark) {
            this.mark = mark

            val emotionText = "Эмоция: ${this.mark.emotion}"
            val ratingText = "Оценка: ${this.mark.rating}"

            dateTextView.text = DateFormat.format("dd.MM.yyyy", this.mark.date)
            emotionTextView.text = emotionText
            ratingTextView.text = ratingText
        }


    }

    private inner class MarkAdapter:
        ListAdapter<EmotionMark, MarkHolder>(DiffCallback) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkHolder {
            val view = layoutInflater.inflate(R.layout.list_item_mark, parent, false)
            return MarkHolder(view)
        }

        override fun getItemCount() = currentList.size

        override fun onBindViewHolder(holder: MarkHolder, position: Int) {
            val mark = currentList[position]
            holder.bind(mark)
        }
    }


}