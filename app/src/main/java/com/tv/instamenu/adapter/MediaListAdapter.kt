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
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.tv.instamenu.R
import com.tv.instamenu.databinding.RowMediaBinding
import com.tv.instamenu.utils.GlideApp
import com.tv.instamenu.utils.MediaCache


class MediaListAdapter(
    private val mContext: Context,
    private var list: MutableList<com.tv.instamenu.data.modal.MediaItem> = mutableListOf(),
    private val listener : OnItemSelected,
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
        holder.bindData(mContext, data,listener)
    }

    interface OnItemSelected {
       fun videoEnd()

       fun singleVideoEnd()
    }
    class ItemHolder(containerView: RowMediaBinding) :
        RecyclerView.ViewHolder(containerView.root) {
        val binding = containerView
        var simpleExoPlayer: ExoPlayer? = null
        fun initializePlayer(
            context: Context,
            data: com.tv.instamenu.data.modal.MediaItem,
            listener: OnItemSelected,
            list: MutableList<com.tv.instamenu.data.modal.MediaItem>
        ) {

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
            if (list.size==1)  simpleExoPlayer?.setRepeatMode(Player.REPEAT_MODE_ONE)
            else simpleExoPlayer?.setRepeatMode(Player.REPEAT_MODE_OFF);

         simpleExoPlayer!!.addListener(object :
                Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == Player.STATE_ENDED) {
                        Log.e("player==","end")
                        listener.videoEnd()
                        /*simpleExoPlayer?.prepare()
                        simpleExoPlayer?.play()*/

                    }
                }

             override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                 super.onMediaItemTransition(mediaItem, reason)
                 if (reason == Player.MEDIA_ITEM_TRANSITION_REASON_REPEAT) {
                   listener.singleVideoEnd()
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
            listener: OnItemSelected,
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

    fun submitData(updatedList: MutableList<com.tv.instamenu.data.modal.MediaItem>){
        list.clear()
        list.addAll(updatedList)
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
            holder.initializePlayer(mContext, list[holder.absoluteAdapterPosition],listener, list)
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