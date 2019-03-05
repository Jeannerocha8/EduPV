package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeColetaSangue extends Fragment {

    public atividadeColetaSangue() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_coleta_sangue, container, false);

        //alterando titulo da pagina
        getActivity().setTitle("Atividade Coleta de Sangue");

        //habilitando movimento das imagens
        v.findViewById(R.id.imgLuvaa).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcolaranjaa).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgscalpverdeee).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgequipo).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgalgodao).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imjelcoamarelo).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgseringa).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgTubo).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgGarrote).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgAlcool).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgdispositivo).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgscalpazul).setOnLongClickListener(new atividadeColetaSangue.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutBandeja).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(1));
        v.findViewById(R.id.atvcoletasangue).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(2));
        v.findViewById(R.id.layoutLuvaa).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(3));
        v.findViewById(R.id.layoutJelcoLaranjaa).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(4));
        v.findViewById(R.id.layoutScalpVerdeee).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(5));
        v.findViewById(R.id.layoutequipo).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(6));
        v.findViewById(R.id.layoutalgodao).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(7));
        v.findViewById(R.id.layoutjelcoamarelo).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(8));
        v.findViewById(R.id.layoutSeringa).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(9));
        v.findViewById(R.id.layoutTubo).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(10));
        v.findViewById(R.id.layoutGarrote).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(11));
        v.findViewById(R.id.layoutAlcool).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(12));
        v.findViewById(R.id.layoutDispositivo).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(13));
        v.findViewById(R.id.layoutScalpAzul).setOnDragListener(new atividadeColetaSangue.MyOnDragListener(14));

        //evento de click nos botoes
        v.findViewById(R.id.btnAnteriocoleta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeExecucao()).addToBackStack(null).commit();
            }
        });

        v.findViewById(R.id.proximocoleta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeIdentificacaoVeias()).addToBackStack(null).commit();
            }
        });

        return v;
    }


    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            v.setVisibility(View.INVISIBLE);

            return(true);
        }
    }


    class MyOnDragListener implements View.OnDragListener {
        private int num;

        public MyOnDragListener(int num){
            super();
            this.num = num;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

            switch (action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("Script", num + " - ACTION_DRAG_STARTED");
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                    {
                        return (true);
                    }else{

                        return (false);

                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Script", num + " - ACTION_DRAG_ENTERED");
                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", num + " - ACTION_DRAG_LOCATION");
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script", num + " - ACTION_DRAG_EXITED");
                    break;

                case DragEvent.ACTION_DROP:

                    Log.i("Script", num + " - ACTION_DROP");
                    View view = (View) event.getLocalState();

                    //verificando layout e imagem
                    if(view.getId()==R.id.imgLuvaa && num==1){
                        Pontuacao.pontuacao= Pontuacao.pontuacao+1;

                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);


                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgseringa && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgscalpverdeee && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);


                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("Tanto o scalp verde(número 21) quanto azul (número 23)  são recomendados para coleta de sangue")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgscalpazul && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgGarrote && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgalgodao && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgAlcool && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.imgTubo && num==1){
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()== R.id.imgjelcolaranjaa){

                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você Errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Os jelcos são recomendados apenas para terapias intravenosas com duração média de 72 horas!!!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()== R.id.imjelcoamarelo){

                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você Errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Os jelcos são recomendados apenas para terapias intravenosas com duração média de 72 horas!!!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()== R.id.imgequipo){

                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você Errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("O equipo é utilizado para administração de medicamentos, como por exemplo o soro!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()== R.id.imgdispositivo){

                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você Errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Este dispositivo é recomendado aplicação de medicamentos em terapias intra-venosas contínuas e intermitentes")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else
                    {
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Erro");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("contate o desenvolvedor do sistema!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");
                    break;
            }
            return true;
        }}



}
