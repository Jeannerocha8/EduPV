package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeTerapiaIntravenosa3dias extends Fragment {

    int contador = 0;

    public atividadeTerapiaIntravenosa3dias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_atividade_terapia_intravenosa3dias, container, false);

        // alterando titulo da pagina

        getActivity().setTitle("Atividade Terapia Intravenosa");


        Button anterior = (Button) v.findViewById(R.id.Anterioterapia);
        Button proximo = (Button) v.findViewById(R.id.proximoterapia);


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeHigiene2()).addToBackStack(null).commit();
            }
        });


        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contador == 8 ){
                    Pontuacao.pontuacao=Pontuacao.pontuacao+1;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new ResultadoAtvInterativas()).addToBackStack(null).commit();
                }else{
                    final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

                    //Imprimindo mensagem com Alerta
                    alerta.setTitle("ATENÇÃO");
                    alerta.setIcon(R.drawable.atencao);
                    alerta .setMessage("Esta faltando material, pense mais um pouco!")
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();
                }
            }
        });

        //habilitando movimento das imagens
        v.findViewById(R.id.imgLuvaa).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcoverde).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgequipo).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgalgodao).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgseringa).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgTubo).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgGarrote).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgAlcool).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgdispositivo).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgscalpazul).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());
        v.findViewById(R.id.imgEsparadrapo).setOnLongClickListener(new atividadeTerapiaIntravenosa3dias.MyOnLongClickListener());


        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutBandeja).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(1));
        v.findViewById(R.id.atvTerapiaIntravenosa).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(2));
        v.findViewById(R.id.layoutLuvaa).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(3));
        v.findViewById(R.id.layoutJelcoVerde).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(4));
        v.findViewById(R.id.layoutequipo).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(6));
        v.findViewById(R.id.layoutalgodao).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(7));
        v.findViewById(R.id.layoutSeringa).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(9));
        v.findViewById(R.id.layoutTubo).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(10));
        v.findViewById(R.id.layoutGarrote).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(11));
        v.findViewById(R.id.layoutAlcool).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(12));
        v.findViewById(R.id.layoutDispositivo).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(13));
        v.findViewById(R.id.layoutScalpAzul).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(14));
        v.findViewById(R.id.layoutEsparadrapo).setOnDragListener(new atividadeTerapiaIntravenosa3dias.MyOnDragListener(15));

        return  v;
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

                        contador = contador+1;

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
                    }else if (view.getId()==R.id.imgequipo && num==1){

                        contador = contador+1;
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

                    }else if (view.getId()==R.id.imgjelcoverde && num==1){
                        contador = contador+1;

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
                        contador = contador+1;

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
                        contador = contador+1;

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
                        contador = contador+1;

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
                    }else if (view.getId()==R.id.imgEsparadrapo && num==1){
                        contador = contador+1;

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
                    }else if (view.getId()== R.id.imgdispositivo&& num==1){

                        contador = contador+1;

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
                    }else
                    {
                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você errou!");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Este material não é utilizado para esta finalidade")
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
        }
    }
}
