package com.mnunez.melitest.exensions


import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File



fun ImageView.loadImage(imageUrl: String?) = Picasso.get().load(imageUrl).into(this)

fun ImageView.loadImageWithPlaceholder(imageUrl: String, placeholder: Int, errorImage: Int) =
        Picasso.get().load(imageUrl)
                .placeholder(placeholder)
                .error(errorImage)
                .into(this)


fun ImageView.loadResizeImage(url: String, width: Int, height: Int) =
        Picasso.get().load(url).resize(width, height).centerCrop().into(this)

fun ImageView.loadFitImage(url: String?) =
        Picasso.get().load(url).fit().centerCrop().into(this)

fun ImageView.loadFitImageFromDisk(imageFile: File) =
        Picasso.get().load(imageFile).fit().centerCrop().into(this)

fun ImageView.loadImageFromDisk(imageFile: File) =
        Picasso.get().load(imageFile).into(this)

fun ImageView.loadFromDrawable(drawable: Int) =
        Picasso.get().load(drawable).into(this)
