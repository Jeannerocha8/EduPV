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
public class IdentificarVeias extends Fragment {


    public IdentificarVeias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_identificar_veias, container, false);

        getActivity().setTitle("Identificar Veias");

        Button anterio = (Button) v.findViewById(R.id.btnAntveia);
        Button prox = (Button) v.findViewById(R.id.btnProxVeia);

        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  getFragmentManager().beginTransaction().replace(R.id.telaBotton, new IdentificarVeias()).
                        addToBackStack(null).commit();*/

            }
        });

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().
                        replace(R.id.telaBotton, new OrganizarMateriais()).
                        addToBackStack(null).commit();
            }
        });
        return v;
    }

}
