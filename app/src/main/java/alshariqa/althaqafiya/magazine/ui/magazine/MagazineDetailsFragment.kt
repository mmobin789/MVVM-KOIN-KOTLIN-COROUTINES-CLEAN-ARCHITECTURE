package alshariqa.althaqafiya.magazine.ui.magazine

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_magazine_details.*
import kotlinx.android.synthetic.main.youtube_layout.*


class MagazineDetailsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_magazine_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycle.addObserver(vYoutubePlayer)

        arguments?.run {
            val magazine = getParcelable<Magazine>(Keys.MAGAZINE_ID)
            ImageLoader.load(magazine?.thumbnail, ivMagazine, R.drawable.ic_ph_home_article)
            tvDate.text = magazine?.issueNo.toString()
            tvTitle.text = magazine?.title
            tvSubTitle.text = magazine?.title


            vYoutubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    try {
                        val videoId = magazine?.youtubeUrl?.split("embed/")?.get(1)
                        if (!videoId.isNullOrBlank())
                            youTubePlayer.loadVideo(videoId, 0f)
                        else showMessage("Video Not Available")
                    } catch (e: IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            })

        }


    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.magazine_detail)
    }

    /*  override fun onError(error: String) {
          showMessage(error)
          hideProgressBar()

      }*/


    /* private fun hideProgressBar(show: Boolean = false) {
         progress.visibility = if (show) View.VISIBLE
         else View.GONE
     }*/

    companion object {
        fun newInstance() = MagazineDetailsFragment()
    }


}