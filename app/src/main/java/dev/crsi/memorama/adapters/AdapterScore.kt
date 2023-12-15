package dev.crsi.memorama.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.crsi.memorama.databinding.CardScoreBinding
import dev.crsi.memorama.models.Score

class AdapterScore(context: Context, result: List<Score>) :
    ArrayAdapter<Score>(context, 0, result) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val holder: ViewHolder?

        if (convertView == null) {

            val binding = CardScoreBinding.inflate(inflater, parent, false)
            holder = ViewHolder(binding)
            convertView?.tag ?: holder

        } else {

            holder = convertView.tag as ViewHolder
            holder.binding = CardScoreBinding.bind(convertView)
        }

        val score = getItem(position)
        if (score != null) {
            holder.bind(score)
        }

        return holder.itemView
    }

    private class ViewHolder(var binding: CardScoreBinding) {
        val itemView = binding.root

        fun bind(score: Score) {
            binding.name.text = score.username
            binding.score.text = score.score.toString()
        }
    }
}