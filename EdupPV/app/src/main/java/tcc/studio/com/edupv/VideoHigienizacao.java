package tcc.studio.com.edupv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import tcc.studio.com.edupv.BD.FullScreenMediaController;

public class VideoHigienizacao extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    int rotation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_higienizacao);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        videoView = findViewById(R.id.videoView);

        setTitle("Organização do Material");



        Button anterio = (Button)findViewById(R.id.btnvideoAnterior);
        Button proximo = (Button)findViewById(R.id.btnvideoprox);

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               chamarTelaAnterior();

            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarProximaTela();
            }
        });



        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }else {
            Toast.makeText(this, "Imprimiu", Toast.LENGTH_LONG);
        }

    }

    private void chamarProximaTela() {
        Intent intent = new Intent(this, VideoExecucaoProcedimento.class);
        intent.putExtra("video", "video");
        startActivity(intent);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if(id==R.id.home){
            Intent intent = new Intent(this, TelaPrincipal.class);
            intent.putExtra("video", "video");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarTelaAnterior() {
        Intent intent = new Intent(this, TelaPrincipal.class);
        intent.putExtra("video", "video");
        startActivity(intent);

    }

    public void onResume() {
        super.onResume();

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.orgmateriais);
        videoView.setVideoURI(videoUri);
        mediaController = new FullScreenMediaController(this);
        mediaController.setAnchorView(this.videoView);
        videoView.setMediaController(this.mediaController);
        videoView.start();

    }

}
