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
public class atividadeRN extends Fragment {
    View.DragShadowBuilder sb;
    View texto;
    public atividadeRN() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_rn, container, false);

        // alterado titulo da pagina
        getActivity().setTitle("Atividade RN");
        //evento de click longo nas imagens


        v.findViewById(R.id.imgjelcoamarelo).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.imgjelcoazul).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.imgjelcocinza).setOnLongClickListener(new MyOnLongClickListener());


        //permitindo receber imagem
        v.findViewById(R.id.layoutbb).setOnDragListener(new MyOnDragListener(1));

        Button proximo = (Button) v.findViewById(R.id.buttonProx);
        Button anterior = (Button) v.findViewById(R.id.buttonAnt);


        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerView();
                getFragmentManager().
                        beginTransaction().replace(R.id.frame_container, new atividadeJelco()).
                        addToBackStack(null).commit();
            }
        });


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              removerView();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new Atividade()).addToBackStack(null)
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
                    if(view.getId()==R.id.imgjelcoamarelo && num==1){

                        Pontuacao.pontuacao=1;
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
                               alerta .setMessage("O jelco amarelo é utilizado em RN, crianças e adultos, podendo ser utilizado em veias finas e frágeis " +
                                       "sendo adequado para a maioria das infusões!")
                                       .setCancelable(true)
                                       .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                           }
                                       });
                               AlertDialog alertDialog = alerta.create();
                               alertDialog.show();
                    }else if(view.getId()==R.id.imgjelcocinza && num==1)
                    {

                        //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("O jelco cinza não pode ser utilizado em recém nascidos, sua recomendação é para que " +
                                "seja utilizado em adultos e adolescentes, durante procedimentos cirurgicos, exige veia calibrosa")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();


                        getFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new atividadeRN()).addToBackStack(null)
                                .commit();

                    }else if(view.getId()==R.id.imgjelcoazul && num==1) {
                         //imprimindo mensagem
                        alerta.setTitle("Que pena, você errou");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("O jelco azul pode ser utilizado em bebês, mas não é recomendado para recém nascidos!")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new atividadeRN()).addToBackStack(null)
                                .commit();

                    }else if((view.getId()==R.id.imgjelcoazul != (num== 0)) ||
                            (view.getId()==R.id.imgjelcoazul != (num==0)) ||
                            (view.getId()==R.id.imgjelcocinza != (num==0)) || (view.getId()==R.id.imgjelcocinza != (num==0)) ||
                            (view.getId()==R.id.imgjelcoamarelo != (num==0))  ||(view.getId()==R.id.imgjelcoamarelo != (num==0)) ) {
                        //imprimindo mensagem
                        alerta.setTitle("Opção Inválida");
                        alerta.setIcon(R.drawable.errado);
                        alerta .setMessage("Por favor, arraste o jelco até a imagem do recém nascido")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new atividadeRN()).addToBackStack(null)
                                .commit();

                    }

                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");

                    break;
            }
            return true;
        }}
}
