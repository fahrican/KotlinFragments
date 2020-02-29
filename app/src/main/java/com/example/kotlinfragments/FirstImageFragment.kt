package com.example.kotlinfragments

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_first_image.*

class FirstImageFragment : Fragment() {

    private val imageUrl: String =
        "https://www.notebookcheck.com/fileadmin/Notebooks/News/_nc3/AndroidLogo52.jpg"

    companion object {
        fun newInstance() = FirstImageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    fun loadImageUsingGlide() {
        first_fragment_progress_bar.visibility = View.VISIBLE
        Glide.with(activity!!)
            .asBitmap()
            .load(Uri.parse(imageUrl))
            .into(object : BitmapImageViewTarget(first_fragment_iv) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    first_fragment_progress_bar.visibility = View.INVISIBLE
                }
            })
    }
}