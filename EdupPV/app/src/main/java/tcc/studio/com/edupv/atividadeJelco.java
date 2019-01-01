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
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeJelco extends Fragment {


    public atividadeJelco() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_atividade_jelco, container, false);

        //alteando titulo da tela
        getActivity().setTitle("Atividade Jelco");

        //habilitando movimento das imagens
        v.findViewById(R.id.txt14).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.txt18).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.txt20).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.txt16).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.txt24).setOnLongClickListener(new MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutJelcoLaranja).setOnDragListener(new MyOnDragListener(1));
        v.findViewById(R.id.layoutJelcoRosa).setOnDragListener(new MyOnDragListener(2));
        v.findViewById(R.id.layoutJelcoVerde).setOnDragListener(new MyOnDragListener(3));
        v.findViewById(R.id.layoutJelcoCinza).setOnDragListener(new MyOnDragListener(4));
        v.findViewById(R.id.layoutJelcoAmarelo).setOnDragListener(new MyOnDragListener(5));



        //localizando botão
        Button anterio = (Button) v.findViewById(R.id.buttonAnt);
        Button proximo = (Button) v.findViewById(R.id.buttonProx);

        //evento de click no botão
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeCirurgia()).addToBackStack(null)
                        .commit();
            }
        });

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeRN()).addToBackStack(null)
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
            v.setVisibility(View.VISIBLE);
            return(true);
        }
    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;
        final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

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
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
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

                    if(view.getId()==R.id.txt14 && num==1){

                        Pontuacao.pontuacao= Pontuacao.pontuacao+1;

                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("O jelco de numeração 14 possui a cor laranja, é indicado para ser utilizado em adultos e adolescentes durante cirurgias!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                    }else if (view.getId()==R.id.txt18 && num==3)
                    {
                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("O jelco de numeração 18 possui a cor verde, é indicado para ser utilizado em crianças, adultos e adolescentes para administrar sangue e " +
                                "hemoderivados!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if (view.getId()==R.id.txt20 && num==2){

                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("O jelco de numeração 20 possui a cor rosa, este cateter é recomendado para a maioria das infusões venosas de sangue, para crianças " +
                                "adultos e adolescentes!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    } else if (view.getId()==R.id.txt16 && num==4){

                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("O jelco de numeração 16 possui a cor cinza, e assim como o jelco 14 é recomendado para ser utilizado durante processos cirurgicos")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                    }else if (view.getId()==R.id.txt24 && num==5){

                        //adicionando imagem ao layout
                        ViewGroup ow = (ViewGroup) view.getParent();
                        ow.removeView(view);
                        LinearLayout cont = (LinearLayout) v;
                        cont.addView(view);
                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Parabéns, você acertou");
                        alerta.setIcon(R.drawable.certo);
                        alerta .setMessage("O jelco de numeração 24 possui a cor amarela, este cateter é recomendado para infusões em recém nascidos, crianças, adolescentes e adultos " +
                                "a infusão com este cateter deve ser lenta!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else {
                        //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("A numeração não corresponde ao jelco")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");
                    break;
                }
            return true;
        }}
    }