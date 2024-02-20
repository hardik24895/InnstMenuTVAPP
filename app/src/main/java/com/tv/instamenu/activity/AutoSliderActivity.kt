package com.tv.instamenu.activity


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.Player
import com.hardik.newsapp.ui.base.BaseActivity
import com.hardik.newsapp.utils.Util
import com.tv.instamenu.R
import com.tv.instamenu.adapter.MediaListAdapter
import com.tv.instamenu.data.modal.MediaItem
import com.tv.instamenu.data.modal.MediaListModal
import com.tv.instamenu.databinding.ActivityAutosliderBinding
import com.tv.instamenu.utils.GlideApp
import com.tv.instamenu.utils.Logger
import com.tv.instamenu.viewmodal.AutoSliderViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class AutoSliderActivity : BaseActivity() {


    companion object {

        private const val EXTRAS_TITLE = "EXTRAS_TITLE"

        fun getStartIntent(context: Context, screen: String): Intent {
            return Intent(context, AutoSliderActivity::class.java)
                .apply {
                    putExtra(EXTRAS_TITLE, screen)
                }
        }

    }

    private lateinit var mediaListAdapter: MediaListAdapter
    private var medeaList = mutableListOf<MediaItem>()
    private lateinit var binding: ActivityAutosliderBinding


    val menuListViewModal : AutoSliderViewModal by viewModels()

    private var mTimer1: Timer? = null
    private var mTt1: TimerTask? = null
    private val mTimerHandler = Handler()

    // val scope = MainScope() // could also use an other scope such as viewModelScope if available
    // var job: Job? = null

    private fun goNextPage() {
        if (binding.viewPager.getCurrentItem() === mediaListAdapter.itemCount - 1) { //adapter is your custom ViewPager's adapter
            binding.viewPager.setCurrentItem(0)
        } else {
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1, true)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutosliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

       showProgressbar()
       subscribeToEvents()
        val id = intent.getStringExtra(EXTRAS_TITLE)
        id?.let {
            menuListViewModal.menuList(session.user.data?.id.toString(),id )
           // menuListViewModal.menuList("7","1" )
        }

    }
    /**
     * Get Flow Event
     */
    private fun subscribeToEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                menuListViewModal.screenEvent.collect { event ->
                    when (event) {
                        is AutoSliderViewModal.ScreenEvent.OnSuccess -> onSuccess(event.response)
                        is AutoSliderViewModal.ScreenEvent.OnUpdateMenu -> onUpdateMenu(event.response)
                        is AutoSliderViewModal.ScreenEvent.OnError -> onFailed(event.error)
                    }

                }
            }
        }
    }

    private fun onSuccess(response: MediaListModal) {
        hideProgressbar()
        if (response.status == 1) {
            medeaList = response.data
            mediaListAdapter = MediaListAdapter(this, medeaList)
            setUpViewPager()
            binding.viewPager.isUserInputEnabled = false
            val requestOptions: RequestOptions = RequestOptions.diskCacheStrategyOf(
                DiskCacheStrategy.ALL)
            for (i in medeaList){
                if (i.type=="image"){
                    GlideApp.with(this)
                        .load(i.url)
                        .placeholder(R.drawable.food)
                        .apply(requestOptions)
                        .submit()
                }

            }

        } else {
            Util.showInfoAlert(this, response.data.toString(), false)
        }

    }
    private fun onUpdateMenu(response: MediaListModal) {
        hideProgressbar()
        if (response.status == 1) {
            medeaList = response.data
            mediaListAdapter.submitData(medeaList)
            Log.d("updateMenu APi call", response.data.toString())
            mediaListAdapter.notifyDataSetChanged()

        } else {
            Util.showInfoAlert(this, response.data.toString(), false)
        }

    }

    private fun onFailed(response: String) {
        hideProgressbar()
        Util.showInfoAlert(this, response, false)
    }


   /* private fun setupDummyData() {
        val imageUrl =
            "https://img.freepik.com/free-vector/organic-flat-rustic-restaurant-menu-template-with-photo_52683-62703.jpg?w=1380&t=st=1704340719~exp=1704341319~hmac=7d5e0321a6537f636413d6c99ec6ff3987afcc4ee652bcc1090b4e2392adf879"
        val imageUrl2 =
            "https://img.freepik.com/free-vector/modern-restaurant-menu-fast-food_52683-48982.jpg?w=1380&t=st=1704340991~exp=1704341591~hmac=81eeb4a1c0ad8e29d075d68e8666231381995e0298232db620fb8c7baaf6b371"
        val imageUrl3 =
            "https://img.freepik.com/free-vector/flat-design-indian-menu_52683-67288.jpg?w=1380&t=st=1704341039~exp=1704341639~hmac=74007fd3fa390eeeceefd910dc995aebd7c856a29f838f4e864faf25712a9370"
        val modal1 = MediaModal(imageUrl, "Image")
        val modal2 = MediaModal(STREAM_URL, "Video")
        val modal3 = MediaModal(imageUrl2, "Image")
        val modal4 = MediaModal(imageUrl3, "Image")

        medeaList.add(modal1)
        medeaList.add(modal2)
        medeaList.add(modal3)

        medeaList.add(modal4)


        viewPagerAdapter = ViewPagerAdapter(this, medeaList)
        setUpViewPager()
        binding.viewPager.isUserInputEnabled = false





    }
*/

    private fun setUpViewPager() {

        binding.viewPager.adapter = mediaListAdapter

        //set the orientation of the viewpager using ViewPager2.orientation
        //binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 0
        binding.viewPager.currentItem = currentPageIndex
        //  binding.viewPager.offscreenPageLimit = 1


        //binding.viewPager.setInterval(5000)
        /* binding.viewPager.setDirection(AutoScrollViewPager.Direction.RIGHT)
         binding.viewPager.setCycle(true)
         binding.viewPager.setBorderAnimation(true)
        // binding.viewPager.setSlideBorderMode(AutoScrollViewPager.SlideBorderMode.TO_PARENT)
         binding.viewPager.startAutoScroll()*/

        // registering for page change callback
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPage(position)

                }
            }
        )

        /*   binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

               override fun onPageScrollStateChanged(state: Int) {
               }

               override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

               }
               override fun onPageSelected(position: Int) {
                 currentPage(position)
               }

           })*/

        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()

        // unregistering the onPageChangedCallback
        binding.viewPager.unregisterOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {}
        )
    }


    private fun currentPage(position: Int) {
        Log.d("page", position.toString())
        /*if (medeaList.get(position).type == "video") {
             stopTimer()
            if (mediaListAdapter?.binding?.playerView?.player==null) return
            mediaListAdapter?.binding?.playerView?.player!!.addListener(object :
                Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == Player.STATE_ENDED) {
                        Log.d("player==","end")
                        goNextPage()
                        startTimer()

                    }
                }
            })
        }*/
    }

    /* private fun imageViewPagerSwiping() {
         val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
         val PERIOD_MS: Long = 5000 // time in milliseconds between successive task executions.
         val handler = Handler()
         val update = Runnable {
             goNextPage()
         }
         imageTimer.schedule(object : TimerTask() {
             // task to be scheduled
             override fun run() {
                 handler.post(update)
             }
         }, DELAY_MS, PERIOD_MS)
     }*/

    private fun stopTimer() {
        if (mTimer1 != null) {
            mTimer1?.cancel()
            mTimer1?.purge()
            Log.d("timer==", "stop")
        }
    }

    private fun startTimer() {
        mTimer1 = Timer()
        mTt1 = object : TimerTask() {
            override fun run() {
                mTimerHandler.post {
                    goNextPage()
                    val id = intent.getStringExtra(EXTRAS_TITLE)
                    id?.let {
                        menuListViewModal.updateMenuList(session.user.data?.id.toString(),id )
                        // menuListViewModal.menuList("7","1" )
                    }
                }
            }
        }
        Log.d("timer==", "start")
        mTimer1?.schedule(mTt1, 20000, 20000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mediaListAdapter?.binding?.playerView?.player?.release()
    }
}