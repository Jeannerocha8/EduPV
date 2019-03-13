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
public class atividadeHigiene1 extends Fragment {

    int contador = 0;
    public atividadeHigiene1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_atividade_higiene1, container, false);

        Button anterior = (Button) v.findViewById(R.id.anteriorhigiene1);
        Button proximo = (Button) v.findViewById(R.id.proximohigiene1);

        //alterando titulo do fragment
        getActivity().setTitle("Atividade Higienização 1");

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new atividadeEscolhaAngulo())
                        .addToBackStack(null).commit();
            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contador==5){
                    getFragmentManager().beginTransaction().replace(R.id.frame_container, new atividadeHigiene2())
                            .addToBackStack(null).commit();
                }else{
                    final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                    //Imprimindo mensagem com Alerta
                    alerta.setTitle("ATENÇÃO");
                    alerta.setIcon(R.drawable.atencao);
                    alerta .setMessage("Arraste todos os numeros as etapas correspondentes para prosseguir")
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
        v.findViewById(R.id.txt1).setOnLongClickListener(new atividadeHigiene1.MyOnLongClickListener());
        v.findViewById(R.id.txt2).setOnLongClickListener(new atividadeHigiene1.MyOnLongClickListener());
        v.findViewById(R.id.txt3).setOnLongClickListener(new atividadeHigiene1.MyOnLongClickListener());
        v.findViewById(R.id.txt4).setOnLongClickListener(new atividadeHigiene1.MyOnLongClickListener());
        v.findViewById(R.id.txt5).setOnLongClickListener(new atividadeHigiene1.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layoutmao1).setOnDragListener(new atividadeHigiene1.MyOnDragListener(1));
        v.findViewById(R.id.layoutmao2).setOnDragListener(new atividadeHigiene1.MyOnDragListener(2));
        v.findViewById(R.id.layoutmao3).setOnDragListener(new atividadeHigiene1.MyOnDragListener(3));
        v.findViewById(R.id.layoutmao4).setOnDragListener(new atividadeHigiene1.MyOnDragListener(4));
        v.findViewById(R.id.layoutmao5).setOnDragListener(new atividadeHigiene1.MyOnDragListener(5));
        v.findViewById(R.id.cardimgmao1).setOnDragListener(new atividadeHigiene1.MyOnDragListener(6));
        v.findViewById(R.id.cardimgmao2).setOnDragListener(new atividadeHigiene1.MyOnDragListener(7));
        v.findViewById(R.id.cardimgmao3).setOnDragListener(new atividadeHigiene1.MyOnDragListener(8));
        v.findViewById(R.id.cardimgmao4).setOnDragListener(new atividadeHigiene1.MyOnDragListener(9));
        v.findViewById(R.id.cardimgmao5).setOnDragListener(new atividadeHigiene1.MyOnDragListener(10));
        v.findViewById(R.id.atvhigiene1).setOnDragListener(new atividadeHigiene1.MyOnDragListener(11));

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
        }
    }
}
