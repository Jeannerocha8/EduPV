package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExecutarProcedimento extends Fragment {


    public ExecutarProcedimento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_executar_procedimento, container, false);

        getActivity().setTitle("Execução do procedimento");

        Button anterior = (Button) v.findViewById(R.id.btnAnteriorEx);


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.telaListaEstudo, new Higienizacao()).
                        addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

}
