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
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeIdentiVeiaMao extends Fragment {


    public atividadeIdentiVeiaMao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_identi_veia_mao, container, false);




        //alterando titulo da pagina
        getActivity().setTitle("Atividade Identificação das Veias");


        //habilitando movimento das imagens
        v.findViewById(R.id.cefalicamao).setOnLongClickListener(new atividadeIdentiVeiaMao.MyOnLongClickListener());
        v.findViewById(R.id.metacarpiais).setOnLongClickListener(new atividadeIdentiVeiaMao.MyOnLongClickListener());
        v.findViewById(R.id.basilicamao).setOnLongClickListener(new atividadeIdentiVeiaMao.MyOnLongClickListener());
        v.findViewById(R.id.arcovenoso).setOnLongClickListener(new atividadeIdentiVeiaMao.MyOnLongClickListener());


        //habilitando recebimento das imagens
        v.findViewById(R.id.identveia1mao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(1));
        v.findViewById(R.id.identveia2mao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(2));
        v.findViewById(R.id.identveia3mao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(3));
        v.findViewById(R.id.identveia4mao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(4));
        v.findViewById(R.id.cefalicamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(6));
        v.findViewById(R.id.metacarpiais).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(7));
        v.findViewById(R.id.basilicamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(8));
        v.findViewById(R.id.arcovenoso).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(9));
        v.findViewById(R.id.cardatvveiamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(10));
        v.findViewById(R.id.cardcefalicamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(11));
        v.findViewById(R.id.cardbasilicamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(12));
        v.findViewById(R.id.cardarcovenoso).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(13));
        v.findViewById(R.id.cardmetacarpiais).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(14));
        v.findViewById(R.id.frameopc1).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(15));
        v.findViewById(R.id.frameopc2).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(16));
        v.findViewById(R.id.layoutatvveiamao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(17));
        v.findViewById(R.id.atvmao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(18));
        v.findViewById(R.id.framebotao).setOnDragListener(new atividadeIdentiVeiaMao.MyOnDragListener(19));


        v.findViewById(R.id.btnanterioridentmao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeIdentificacaoVeias()).addToBackStack(null)
                        .commit();
            }
        });



        v.findViewById(R.id.btnproxidentmao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new ResultadoAtvInterativas()).addToBackStack(null)
                        .commit();
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
                    if (view.getId()==R.id.metacarpiais && num==2){

                        Pontuacao.pontuacao=Pontuacao.pontuacao+1;
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        container.setBackgroundColor(Color.WHITE);

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

                    }else if (view.getId()==R.id.arcovenoso && num==4){

                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        container.setBackgroundColor(Color.WHITE);

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
                    }else if (view.getId()==R.id.cefalicamao && num==3){

                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        container.setBackgroundColor(Color.WHITE);

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
                    }else if (view.getId()==R.id.basilicamao && num==1){

                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        container.setBackgroundColor(Color.WHITE);

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
                    }else
                    {
                        view.setVisibility(View.VISIBLE);
                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Que pena, você errou!");
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
