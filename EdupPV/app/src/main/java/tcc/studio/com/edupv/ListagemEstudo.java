package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.EOFException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListagemEstudo extends Fragment {


    public ListagemEstudo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //alterando titulo do fragment
        getActivity().setTitle("Conteúdo para estudo");

        //instanciando o view do fragment
        View view = inflater.inflate(R.layout.fragment_listagem_estudo, container, false);

        //criando objeto lista e atribuindo id a ele
        ListView lista = (ListView) view.findViewById(R.id.ListaEstudo);

        //criando array list de opções
        ArrayList<opcoes> Opcoes = adicionarOpcoes();
        ArrayAdapter adapter = new opcaoAdapter(getActivity(), Opcoes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        try {
                            OrganizarMaterial();
                            break;
                        } catch (Error e) {
                            Toast.makeText(getContext(), "erro" + e, Toast.LENGTH_LONG);
                        }
                    case 1:
                        try {
                            IdentificarVeias();
                            break;
                        } catch (Error e) {
                            Toast.makeText(getContext(), "erro" + e, Toast.LENGTH_LONG);
                        }
                    case 2:
                        try {
                            IdentidicarDispositivos();
                            break;
                        } catch (Error e) {
                            Toast.makeText(getContext(), "erro" + e, Toast.LENGTH_LONG);
                        }
                    case 3:
                        try {
                            Higienizacao();
                            break;
                        } catch (Error e) {
                            Toast.makeText(getContext(), "erro" + e, Toast.LENGTH_LONG);
                        }
                    case 4:
                        try {
                            ExecutarProcedimento();
                            break;
                        } catch (Error e) {
                            Toast.makeText(getContext(), "erro" + e, Toast.LENGTH_LONG);
                        }
                    case 5:
                    default:
                        break;

                }
            }
        });
        return view;
    }


    //chamando telas
    private void ExecutarProcedimento() {
        removerView();
        getFragmentManager().beginTransaction().
                replace(R.id.frame_container, new ExecutarProcedimento()).
                addToBackStack(null).commit();
    }

    private void Higienizacao() {
        removerView();
        getFragmentManager().beginTransaction().
                replace(R.id.frame_container, new Higienizacao()).
                addToBackStack(null).commit();
    }

    private void IdentidicarDispositivos() {
        removerView();
        getFragmentManager().beginTransaction().
                replace(R.id.frame_container, new IdentificarDispositivos()).
                addToBackStack(null).commit();
    }

    private void IdentificarVeias() {
        removerView();
        getFragmentManager().
                beginTransaction().
                replace(R.id.frame_container, new IdentificarVeias()).addToBackStack(null).
                commit();
    }

    private void OrganizarMaterial() {
        removerView();
        getFragmentManager().
                beginTransaction().
                replace(R.id.frame_container, new OrganizarMateriais()).addToBackStack(null).
                commit();
    }


    //removendo view


    //populando lista de opções
    private ArrayList<opcoes> adicionarOpcoes() {

        ArrayList<opcoes> Opcoes = new ArrayList<opcoes>();

        opcoes op = new opcoes("Organização dos materiais", "Saiba quais os materiais são necessários para executar o procedimento", R.drawable.img_orgmateriais);
        Opcoes.add(op);

        op = new opcoes("Identificação das veias", "Saiba em quais veias o procedimento pode ser executado em adultos", R.drawable.img_veiaaa);
        Opcoes.add(op);

        op = new opcoes("Identificação dos dispositivos", "Saiba identificar os Jelcos e Scalps", R.drawable.img_dispositivoident);
        Opcoes.add(op);

        op = new opcoes("Higienização", "Saiba como higienizar as mãos e a pele do paciente", R.drawable.img_tornneirahigi);
        Opcoes.add(op);

        op = new opcoes("Execucao do procedimento", "Passo a passo de como executar o procedimento", R.drawable.img_excutarpunv);
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