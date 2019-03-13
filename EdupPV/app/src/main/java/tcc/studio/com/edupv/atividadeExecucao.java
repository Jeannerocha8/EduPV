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
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeExecucao extends Fragment {

    int contador = 0;

    public atividadeExecucao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_execucao, container, false);

        //alterando titulo do fragment
        getActivity().setTitle("Atividade Execução");

        //habilitando movimento das imagens
        v.findViewById(R.id.txt1).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());
        v.findViewById(R.id.txt2).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());
        v.findViewById(R.id.txt3).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());
        v.findViewById(R.id.txt4).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());
        v.findViewById(R.id.txt5).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());
        v.findViewById(R.id.txt6).setOnLongClickListener(new atividadeExecucao.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutEtapa1).setOnDragListener(new atividadeExecucao.MyOnDragListener(1));
        v.findViewById(R.id.layoutEtapa2).setOnDragListener(new atividadeExecucao.MyOnDragListener(2));
        v.findViewById(R.id.layoutEtapa3).setOnDragListener(new atividadeExecucao.MyOnDragListener(3));
        v.findViewById(R.id.layoutEtapa4).setOnDragListener(new atividadeExecucao.MyOnDragListener(4));
        v.findViewById(R.id.layoutEtapa5).setOnDragListener(new atividadeExecucao.MyOnDragListener(5));
        v.findViewById(R.id.layoutEtapa6).setOnDragListener(new atividadeExecucao.MyOnDragListener(6));
        v.findViewById(R.id.cardtxt1).setOnDragListener(new atividadeExecucao.MyOnDragListener(7));
        v.findViewById(R.id.cardtxt2).setOnDragListener(new atividadeExecucao.MyOnDragListener(8));
        v.findViewById(R.id.cardtxt3).setOnDragListener(new atividadeExecucao.MyOnDragListener(9));
        v.findViewById(R.id.cardtxt4).setOnDragListener(new atividadeExecucao.MyOnDragListener(10));
        v.findViewById(R.id.cardtxt5).setOnDragListener(new atividadeExecucao.MyOnDragListener(11));
        v.findViewById(R.id.cardtxt6).setOnDragListener(new atividadeExecucao.MyOnDragListener(12));
        v.findViewById(R.id.layoutatvexecucao).setOnDragListener(new atividadeExecucao.MyOnDragListener(13));

        //evento de click no botão
        v.findViewById(R.id.anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeScalp())
                        .addToBackStack(null).commit();
            }
        });

        v.findViewById(R.id.proximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contador==6){
                    Pontuacao.pontuacao = Pontuacao.pontuacao+1;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new atividadeColetaSangue())
                            .addToBackStack(null).commit();
                }else{
                    final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                    //Imprimindo mensagem com Alerta
                    alerta.setTitle("ATENÇÃO");
                    alerta.setIcon(R.drawable.atencao);
                    alerta .setMessage("Por favor, arraste todas as numerações para a sequência correspondente para continuar")
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

        return v;
    }


    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if (layout != null) {
            layout.removeAllViews();
        }
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
                    if(view.getId()==R.id.txt1 && num==1){

                        contador = contador +1;

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
                    }else if (view.getId()==R.id.txt2 && num==2){
                        contador = contador +1;
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
                    }else if (view.getId()==R.id.txt3 && num==3){

                        contador = contador +1;

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
                    }else if (view.getId()==R.id.txt4 && num==4){

                        contador = contador +1;

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
                    }else if (view.getId()==R.id.txt5 && num==5){

                        contador = contador +1;

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
                    }else if (view.getId()==R.id.txt6 && num==6){

                        contador = contador +1;

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
                    }else{

                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você Errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("")
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
