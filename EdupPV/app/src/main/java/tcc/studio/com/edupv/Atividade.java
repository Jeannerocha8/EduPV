package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Atividade extends Fragment {

    String tela;

    public Atividade() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade, container, false);


        //alterando titulo da Tela
        getActivity().setTitle("Atividades");

        //definindo a lista
        ListView lista = (ListView) v.findViewById(R.id.listaAtv);

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
                        Objetivas();
                        break;
                    case 1:
                        Interativas();
                        break;
                    case 2:
                    default:break;
                }
            }
        });

        return v;
    }

    private void Objetivas() {

        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);

        if(layout != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new atividadeObjetiva()).addToBackStack(null)
                    .commit();
        }
    }

    private void Interativas(){

        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);

        if(layout != null) {
            removerView();
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new atividadeRN()).addToBackStack(null)
                    .commit();
        }
    }

    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        layout.removeAllViews();

    }

    private ArrayList<opcoes> adicionarOpcoes() {
        ArrayList<opcoes> Opcoes = new ArrayList<opcoes>();

        opcoes op = new opcoes("Objetivas", "Resolva questões fechadas sobre punção venosa", R.drawable.objetiva);
        Opcoes.add(op);

        op = new opcoes("Interativas", "Resolva exercícios interativos para fixar o              conteúdo", R.drawable.interativa);
        Opcoes.add(op);

        return Opcoes;
    }

}
