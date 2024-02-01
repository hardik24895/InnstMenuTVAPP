package com.tv.instamenu.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.tv.instamenu.R
import com.tv.instamenu.databinding.RowMediaBinding
import com.tv.instamenu.utils.GlideApp
import com.tv.instamenu.utils.MediaCache


class MediaListAdapter(
    private val mContext: Context,
    private var list: MutableList<com.tv.instamenu.data.modal.MediaItem> = mutableListOf(),
) : RecyclerView.Adapter<MediaListAdapter.ItemHolder>() {

    var binding: RowMediaBinding? = null

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        binding = RowMediaBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )
        return ItemHolder(
            binding!!
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val data = list[position]
        holder.bindData(mContext, data)
    }

    interface OnItemSelected {
       fun videoEnd()
    }
    class ItemHolder(containerView: RowMediaBinding) :
        RecyclerView.ViewHolder(containerView.root) {
        val binding = containerView
        var simpleExoPlayer: ExoPlayer? = null
        fun initializePlayer(context: Context, data: com.tv.instamenu.data.modal.MediaItem) {

          //  val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(context)
            val mediaSourceFactory =
                ProgressiveMediaSource.Factory(MediaCache.getInstance(context).cacheFactory)
            val mediaSource = mediaSourceFactory
               // .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///video.mp4")))
             .createMediaSource(MediaItem.fromUri(data.url.toString()))
             //.createMediaSource(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))


            //val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

            simpleExoPlayer = ExoPlayer.Builder(context)
                .setMediaSourceFactory(mediaSourceFactory)
                .build()

            simpleExoPlayer?.addMediaSource(mediaSource)
            // simpleExoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);

            simpleExoPlayer?.playWhenReady = true
            binding.playerView.player = simpleExoPlayer
            binding.playerView.requestFocus()
            simpleExoPlayer?.prepare()
            simpleExoPlayer?.play()
            simpleExoPlayer?.setRepeatMode(Player.REPEAT_MODE_ONE);

         simpleExoPlayer!!.addListener(object :
                Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == Player.STATE_ENDED) {
                        Log.d("player==","end")
                        simpleExoPlayer?.prepare()
                        simpleExoPlayer?.play()
                    }
                }
            })
        }

        fun releasePlayer() {
            simpleExoPlayer?.release()
        }

        fun bindData(
            context: Context,
            data: com.tv.instamenu.data.modal.MediaItem,
        ) {

            /*if (data.type == "image") {
                binding.image.visibility = View.VISIBLE
                binding.playerView.visibility = View.GONE
                GlideApp.with(context)
                   .load(data.url)
                    //.load("https://images.unsplash.com/photo-1606481021733-5e269f7d87f6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8MTkyMCUyMHglMjAxMDgwfGVufDB8fDB8fHww")
                    .error(R.drawable.logo)
                    .placeholder(R.drawable.logo)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.image)
            } else {
                binding.image.visibility = View.GONE
                binding.playerView.visibility = View.VISIBLE
                initializePlayer(context, data)
            }*/

        }
    }

    override fun onViewDetachedFromWindow(holder: ItemHolder) {
        super.onViewDetachedFromWindow(holder)
        // holder.simpleExoPlayer.release()
        holder.releasePlayer()
        //  holder.releasePlayer()
    }

    override fun onViewAttachedToWindow(holder: ItemHolder) {
        super.onViewAttachedToWindow(holder)
        if (list[holder.absoluteAdapterPosition].type == "video") {
            holder.binding.image.visibility = View.GONE
            holder.binding.playerView.visibility = View.VISIBLE
            holder.simpleExoPlayer?.prepare()
            holder.initializePlayer(mContext, list[holder.absoluteAdapterPosition])
        } else {
            holder.binding.image.visibility = View.VISIBLE
            holder.binding.playerView.visibility = View.GONE
            val requestOptions: RequestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
            GlideApp.with(holder.binding.root.context)
                .load(list[holder.absoluteAdapterPosition].url)
                .placeholder(R.drawable.food)
                .apply(requestOptions)
                .into(holder.binding.image)

        }

    }
}