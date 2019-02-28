package tcc.studio.com.edupv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListagemVideo extends Fragment {

       public ListagemVideo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // setanto titulo do fragment
        getActivity().setTitle("Conteúdo em vídeo");

        //inflando a view

        View view = inflater.inflate(R.layout.fragment_conteudo_video, container, false);

        //definindo a lista
        ListView lista = (ListView) view.findViewById(R.id.ListaVideo);

        //definindo o arraylist
        ArrayList<opcoes> Opcoes = adicionarOpcoes();

        //Instanciando o ArrayAdapter passando como paramentro o fragmento atual e o ArrayList Definido anteriormente
        ArrayAdapter adapter = new opcaoAdapter(getActivity(),Opcoes);

        //Adicionando o Adapter a Lista criada
        lista.setAdapter(adapter);


        //Evento de clik na lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Higienizacao();
                        break;
                    case 1:
                        ManuseioJescoScalp();
                        break;
                    case 2:
                        ExecucaoProcedimento();
                        break;
                    case 3:
                    default:break;
                }
            }
        });

        return view;
    }


    //Metodos para click
    private void Higienizacao() {
        Intent intent = new Intent(getActivity(), VideoHigienizacao.class);
        startActivity(intent);
    }

    private void ManuseioJescoScalp() {
        Intent intent = new Intent(getActivity(), VideoManuseioJelcoScalp.class);
        startActivity(intent);
    }

    private void ExecucaoProcedimento() {
        Intent intent = new Intent(getActivity(), VideoExecucaoProcedimento.class);
        startActivity(intent);
    }


    //Criando os objetos da  lista
    private ArrayList<opcoes> adicionarOpcoes() {

        ArrayList<opcoes> Opcoes = new ArrayList<opcoes>();

        opcoes op = new opcoes("Higienização", "Veja na prática como higienizar as mãos e a pele do paciente.", R.drawable.img_tornneirahigi);
        Opcoes.add(op);

        op = new opcoes("Manuseio Jelco e Scalp", "Dicas de como manusear os dispositivos.", R.drawable.img_dispositivoident);
        Opcoes.add(op);

        op = new opcoes("Execucao do procedimento", "Veja na prática como realizar a execução do procedimento.", R.drawable.img_excutarpunv);
        Opcoes.add(op);

        return Opcoes;
    }

    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if (layout != null) {
            layout.removeAllViews();
        }
    }
}
