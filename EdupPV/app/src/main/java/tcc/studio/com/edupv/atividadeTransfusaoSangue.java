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
public class atividadeTransfusaoSangue extends Fragment {


    public atividadeTransfusaoSangue() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_transfusao_sangue, container, false);

        //alterando titulo da pagina
        getActivity().setTitle("Atividade Transfusão Sanquínea");

        //habilitando movimento das imagens
        v.findViewById(R.id.imgjelcoamarelotransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcocinzatransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcolaranjatransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcoverdetransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcorosatransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());
        v.findViewById(R.id.imgjelcoazultransf).setOnLongClickListener(new atividadeTransfusaoSangue.MyOnLongClickListener());

        //habilitando recebimento das imagens
        v.findViewById(R.id.layouttransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(1));
        v.findViewById(R.id.linear1).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(2));
        v.findViewById(R.id.linear2).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(3));
        v.findViewById(R.id.cardjelcoazultransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(4));
        v.findViewById(R.id.cardjelcolaranjatransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(5));
        v.findViewById(R.id.cardjelcoverdetransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(6));
        v.findViewById(R.id.cardjelcocinzatransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(7));
        v.findViewById(R.id.cardjelcorosatransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(8));
        v.findViewById(R.id.cardjelcoamarelotransf).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(9));
        v.findViewById(R.id.layoutatvtransfsangue).setOnDragListener(new atividadeTransfusaoSangue.MyOnDragListener(10));

        v.findViewById(R.id.btnAnteriotransf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new atividadeIdentiVeiaMao()).addToBackStack(null)
                        .commit();
            }
        });



        v.findViewById(R.id.proximotransf).setOnClickListener(new View.OnClickListener() {
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
                   if (view.getId()==R.id.imgjelcorosatransf && num==1){
                        Pontuacao.pontuacao= Pontuacao.pontuacao+1;
                        //adicionando imagem ao layout
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        //tornando a imagem visivel
                        view.setVisibility(View.VISIBLE);

                        //Imprimindo mensagem com Alerta
                        alerta.setTitle("Este Jelco corresponde ao número 20, e é recomendado para transfusão sanguínea, lembre-se de atentar-se para as\" +\n" +
                                "                                \"caracteristicas fisicas do paciente ao escolher este jelco");
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
                    }else if (view.getId()==R.id.imgjelcoazultransf && num==1){
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
                        alerta .setMessage("Este Jelco corresponde ao número 20, e é recomendado para transfusão sanguínea, lembre-se de atentar-se para as"
                                +"caracteristicas fisicas do paciente ao escolher este jelco")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                    }else if (view.getId()==R.id.imgjelcoamarelotransf && num==1){
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
                        alerta .setMessage("Este Jelco corresponde ao número 20, e é recomendado para transfusão sanguínea, lembre-se de atentar-se para as" +
                                " caracteristicas fisicas do paciente ao escolher este jelco")
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
                        alerta .setMessage("Este Jelco não é recomendado para transfusão sanguínea")
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
