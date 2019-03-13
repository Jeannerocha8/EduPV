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
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeEscolhaAngulo extends Fragment {


    public atividadeEscolhaAngulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_escolha_angulo, container, false);
        //alterando titulo da pagina
        getActivity().setTitle("Atividade Angulo correto");

        //habilitando movimento das imagens
        v.findViewById(R.id.imgsubcutanea).setOnLongClickListener(new atividadeEscolhaAngulo.MyOnLongClickListener());
        v.findViewById(R.id.imgintravenosa).setOnLongClickListener(new atividadeEscolhaAngulo.MyOnLongClickListener());
        v.findViewById(R.id.imgintradermica).setOnLongClickListener(new atividadeEscolhaAngulo.MyOnLongClickListener());
        v.findViewById(R.id.imgintramuscular).setOnLongClickListener(new atividadeEscolhaAngulo.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutpuncao).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(1));
        v.findViewById(R.id.atividadeescolhaangulo).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(2));
        v.findViewById(R.id.layoutsubcutanea).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(3));
        v.findViewById(R.id.layoutintradermica).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(4));
        v.findViewById(R.id.layoutintramuscular).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(5));
        v.findViewById(R.id.layoutintravenosa).setOnDragListener(new atividadeEscolhaAngulo.MyOnDragListener(6));


        v.findViewById(R.id.btnAntangulo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeTransfusaoSangue())
                        .addToBackStack(null).commit();
            }
        });

        v.findViewById(R.id.proximoangulo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeHigiene1())
                        .addToBackStack(null).commit();
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
                    if(view.getId()==R.id.imgintravenosa && num==1){
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
                        alerta .setMessage("O ângulo correto para iniciar a introdução do cateter varia de 30° a 45°")
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
                        alerta.setTitle("Que Pena, você errou!");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Este ângulo não é indicado para terapias intravenosas")
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
