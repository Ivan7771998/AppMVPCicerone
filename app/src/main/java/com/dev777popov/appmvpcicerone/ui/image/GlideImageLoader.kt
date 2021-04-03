package com.dev777popov.appmvpcicerone.ui.image

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dev777popov.appmvpcicerone.mvp.model.cache.IImageCache
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.io.ByteArrayOutputStream

class GlideImageLoader(val cache: IImageCache, private val networkStatus: INetworkStatus) :
    IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        networkStatus.isOnlineSingle().observeOn(AndroidSchedulers.mainThread())
            .subscribe { isOnline ->
                if (isOnline) {
                    Glide.with(container.context)
                        .asBitmap()
                        .load(url)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return true
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                val compressFormat =
                                    if (url.contains(".jpg")) Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG
                                val stream = ByteArrayOutputStream()
                                resource?.compress(compressFormat, 100, stream)
                                val bytes = stream.use { it.toByteArray() }
                                cache.saveImage(url, bytes)
                                return false
                            }

                        }).into(container)
                } else {
                    cache.getBytes(url).observeOn(AndroidSchedulers.mainThread()).subscribe({
                        Glide.with(container.context)
                            .load(it)
                            .into(container)
                    }, {
                        Log.e("TAG", "ERROR $it")
                    })
                }
            }
    }
}