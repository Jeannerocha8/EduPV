package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        getActivity().setTitle("Conteúdo para estudo");
        View view = inflater.inflate(R.layout.fragment_listagem_estudo, container, false);
        ListView lista = (ListView) view.findViewById(R.id.ListaEstudo);
        ArrayList<opcoes> Opcoes = adicionarOpcoes();
        ArrayAdapter adapter = new opcaoAdapter(getActivity(),Opcoes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        OrganizarMaterial();
                        break;
                    case 1:
                        IdentificarVeias();
                        break;
                    case 2:
                        IdentidicarDispositivos();
                        break;
                    case 3:
                        Higienizacao();
                        break;
                    case 4:
                        ExecutarProcedimento();
                        break;
                    case 5:
                        default:break;
                }
            }
        });



        return view;
    }

    private void ExecutarProcedimento() {
        Toast.makeText(getActivity(),"voce cliclou em um item", Toast.LENGTH_SHORT).show();

    }

    private void Higienizacao() {
        Toast.makeText(getActivity(),"voce cliclou em um item", Toast.LENGTH_SHORT).show();
    }

    private void IdentidicarDispositivos() {
        Toast.makeText(getActivity(),"voce cliclou em um item", Toast.LENGTH_SHORT).show();
    }

    private void IdentificarVeias() {
        Toast.makeText(getActivity(),"voce cliclou em um item", Toast.LENGTH_SHORT).show();
    }

    private void OrganizarMaterial() {
        Toast.makeText(getActivity(),"voce cliclou em um item", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<opcoes> adicionarOpcoes() {

        ArrayList<opcoes> Opcoes = new ArrayList<opcoes>();

        opcoes op = new opcoes("Organização dos materiais", "Saiba quais os materiais são necessários", R.drawable.or);
        Opcoes.add(op);

        op = new opcoes("Identificação das veias", "Saiba em quais veias o procedimento pode ser executado", R.drawable.iconveia);
        Opcoes.add(op);

        op = new opcoes("Identificação dos dispositivos", "Saiba identificar jelcos e scalps", R.drawable.agulha);
        Opcoes.add(op);

        op = new opcoes("Higienização", "Saiba como higienizar corretamente", R.drawable.torneita);
        Opcoes.add(op);

        op = new opcoes("Execucao do procedimento", "Saiba passo a passo da execução do procedimento", R.drawable.punc);
        Opcoes.add(op);
        return Opcoes;
    }

}