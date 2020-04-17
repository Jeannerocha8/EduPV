package tcc.studio.com.edupv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import tcc.studio.com.edupv.BD.FullScreenMediaController;

public class VideoExecucaoProcedimento extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    int rotation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_execucao_procedimento);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        videoView = findViewById(R.id.videoView);

        setTitle("Identificação dos Jelcos e Scalps");



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
                ChamarProximaTela();
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

    private void ChamarProximaTela() {
        Intent intent = new Intent(this, TelaPrincipal.class);
        intent.putExtra("video", "video");
        startActivity(intent);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if(id==R.id.home){
           this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarTelaAnterior() {
        Intent intent = new Intent(this, VideoHigienizacao.class);
        intent.putExtra("video", "video");
        startActivity(intent);

    }

    public void onResume() {
        super.onResume();

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.materiais);
        videoView.setVideoURI(videoUri);
        mediaController = new FullScreenMediaController(this);
        mediaController.setAnchorView(this.videoView);
        videoView.setMediaController(this.mediaController);
        videoView.start();
    }

}
