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
import kotlinx.android.synthetic.main.fragment_third_image.*

class ThirdImageFragment : Fragment() {

    private val imageUrl: String =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/BlackBerry_KEYone.png/1200px-BlackBerry_KEYone.png"

    companion object {
        fun newInstance() = ThirdImageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    fun loadImageUsingGlide() {
        third_fragment_progress_bar.visibility = View.VISIBLE
        Glide.with(activity!!)
            .asBitmap()
            .load(Uri.parse(imageUrl))
            .into(object : BitmapImageViewTarget(third_fragment_iv) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    third_fragment_progress_bar.visibility = View.INVISIBLE
                }
            })
    }
}