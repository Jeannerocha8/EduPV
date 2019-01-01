package tcc.studio.com.edupv;



import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReproducaoVideo extends Fragment  {


  VideoView videoView;
    public ReproducaoVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_reproducao_video, container, false);

        //Adicionando id ao video
        final VideoView videoView = (VideoView) v.findViewById(R.id.videoView);

        //definindo caminho do video
        String videopath = "android.resource://"+ getActivity().getPackageName()+"/"+R.raw.palavras;

        //setando caminho ao video
        videoView.setVideoURI(Uri.parse(videopath));
        customController vidControl = new customController(getActivity(),videoView);
        videoView.setMediaController(vidControl);
        videoView.start();

        return v;
    }



}
