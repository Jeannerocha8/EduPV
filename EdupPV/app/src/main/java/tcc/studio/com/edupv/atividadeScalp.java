package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeScalp extends Fragment {

    int contador =0;
    public atividadeScalp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_atividade_scalp, container, false);

        //alteando titulo da tela
        getActivity().setTitle("Atividade Scapl");

        //habilitando movimento das imagens
        v.findViewById(R.id.txt19).setOnLongClickListener(new atividadeScalp.MyOnLongClickListener());
        v.findViewById(R.id.txt21).setOnLongClickListener(new atividadeScalp.MyOnLongClickListener());
        v.findViewById(R.id.txt23).setOnLongClickListener(new atividadeScalp.MyOnLongClickListener());
        v.findViewById(R.id.txt25).setOnLongClickListener(new atividadeScalp.MyOnLongClickListener());
        v.findViewById(R.id.txt27).setOnLongClickListener(new atividadeScalp.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutScalpBege).setOnDragListener(new atividadeScalp.MyOnDragListener(1));
        v.findViewById(R.id.layoutScalpCinza).setOnDragListener(new atividadeScalp.MyOnDragListener(2));
        v.findViewById(R.id.layoutScalpLaranja).setOnDragListener(new atividadeScalp.MyOnDragListener(3));
        v.findViewById(R.id.layoutScalpVerde).setOnDragListener(new atividadeScalp.MyOnDragListener(4));
        v.findViewById(R.id.layoutScaplAzul).setOnDragListener(new atividadeScalp.MyOnDragListener(5));
        v.findViewById(R.id.layoutatvjelco).setOnDragListener(new atividadeScalp.MyOnDragListener(6));
        v.findViewById(R.id.card14).setOnDragListener(new atividadeScalp.MyOnDragListener(7));
        v.findViewById(R.id.card16).setOnDragListener(new atividadeScalp.MyOnDragListener(8));
        v.findViewById(R.id.card18).setOnDragListener(new atividadeScalp.MyOnDragListener(9));
        v.findViewById(R.id.card20).setOnDragListener(new atividadeScalp.MyOnDragListener(10));
        v.findViewById(R.id.card24).setOnDragListener(new atividadeScalp.MyOnDragListener(11));

        //localizando botão
        Button anterio = (Button) v.findViewById(R.id.buttonAnt);
        Button proximo = (Button) v.findViewById(R.id.buttonProx);

        //evento de click no botão
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaResolucao.resolucao=0;
                if (contador==5){
                    Pontuacao.pontuacao = Pontuacao.pontuacao +1;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new atividadeExecucao()).addToBackStack(null)
                            .commit();
                }else{
                    final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                    //Imprimindo mensagem com Alerta
                    alerta.setTitle("ATENÇÃO");
                    alerta.setIcon(R.drawable.atencao);
                    alerta .setMessage("Por favor, arraste todas as numerações aos Scalps correspondentes para continuar")
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

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaResolucao.resolucao=1;
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeCirurgia()).addToBackStack(null)
                        .commit();
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
            return (true);
        }
    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;
        final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

        public MyOnDragListener(int num) {
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
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return (true);
                    } else {
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

                    if (view.getId() == R.id.txt19 && num == 1) {

                        contador = contador +1;

                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        cont.setBackgroundColor(Color.parseColor("#67ae72"));


                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta.setMessage("O Scalp 19 corresponde a cor Bege!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                    } else if (view.getId() == R.id.txt21 && num == 4) {
                        contador = contador +1;
                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.setBackgroundColor(Color.parseColor("#67ae72"));
                        cont.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta.setMessage("O Scalp de numeração 21 possui a cor verde")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    } else if (view.getId() == R.id.txt23 && num == 5) {
                        contador = contador +1;
                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        cont.setBackgroundColor(Color.parseColor("#67ae72"));
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta.setMessage("O Scalp de numeração 23 possui a cor Azul")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    } else if (view.getId() == R.id.txt25 && num == 3) {
                        contador = contador +1;
                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.setBackgroundColor(Color.parseColor("#67ae72"));
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta.setMessage("O Scalp de numeração 25 possui a cor Laranja")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                    } else if (view.getId() == R.id.txt27 && num == 2) {
                        contador = contador +1;
                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.setBackgroundColor(Color.parseColor("#67ae72"));
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta.setMessage("O Scalp de numeração 27 possui a cor Cinza")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    } else {
                        //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta.setMessage("A numeração não corresponde ao jelco")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:

                    break;
            }
            return true;
        }
    }
}