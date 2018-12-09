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

public class atividadeCirurgia extends Fragment {
    View.DragShadowBuilder sb;
    View texto;

    public atividadeCirurgia() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_cirurgia, container, false);

        // alterando titulo da tela
        getActivity().setTitle("Atividade Cirurgia");

        //evento de click longo nas imagens
        v.findViewById(R.id.imgjelcoverde).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.imgjelcorosa).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.imgjelcolaranja).setOnLongClickListener(new MyOnLongClickListener());

        //permitindo receber imagem
        v.findViewById(R.id.layoutbb).setOnDragListener(new MyOnDragListener(1));

        Button proximo = (Button) v.findViewById(R.id.buttonProxC);
        Button anterior = (Button) v.findViewById(R.id.buttonAntC);

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* getFragmentManager().beginTransaction()
                        .replace(R.id.telaBotton, new atividadeCirurgia()).addToBackStack(null)
                        .commit();*/

            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.telaBotton, new atividadeJelco()).addToBackStack(null)
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
                    if(view.getId()==R.id.imgjelcolaranja && num==1){

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
                        alerta .setMessage("O jelco laranja é recomendado para ser utilizado em adultos e adolescentes para grande volume de infusões" +
                                " durante processos cirurgicos!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }else if(view.getId()==R.id.imgjelcoverde && num==1)
                    {
                        //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("O jelco verde pode ser utilizado em crianças, adultos e adolescentes para infusão de hemoderivados " +
                                "a utilização deste cateter exige veia calibrosa, porém, não é recomendado para cirurgias, onde é necessário " +
                                "grande volume de sangue ou hemoderivados!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.telaBotton, new atividadeCirurgia()).addToBackStack(null)
                                .commit();

                    }else if(view.getId()==R.id.imgjelcorosa && num==1) {
                        //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("O jelco rosa pode ser utilizado em crianças, adultos e adolescentes,sendo recomendado para a maioria das infusões " +
                                "exceto em grandes volumes, como em cirurgias!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.telaBotton, new atividadeCirurgia()).addToBackStack(null)
                                .commit();

                    }else
                    {
                        alerta.setTitle("ERRO");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Contate o desenvolvedor do sistema")
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
