package tcc.studio.com.edupv;


import android.app.Activity;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReproducaoVideo extends Fragment {


    public ReproducaoVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_reproducao_video, container, false);
        getActivity().setTitle("Reprodução");


        //Adicionando id ao video
        VideoView videoView = (VideoView) v.findViewById(R.id.videoView);

        //definindo caminho do video
        String videopath = "android.resource://"+ getActivity().getPackageName()+"/"+R.raw.palavras;
        //setando caminho ao video
        videoView.setVideoURI(Uri.parse(videopath));

        customController vidControl = new customController(v.getContext(),videoView);
        videoView.setMediaController(vidControl);
        vidControl.setMediaPlayer(videoView);

        vidControl.setAnchorView(videoView);
        videoView.start();


        return v;
    }
}
