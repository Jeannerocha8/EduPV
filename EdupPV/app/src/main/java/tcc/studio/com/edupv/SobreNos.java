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
public class SobreNos extends Fragment {


    public SobreNos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_sobre_nos, container, false);
        getActivity().setTitle("Sobre Nós");

        Button inicio = (Button) v.findViewById(R.id.btnInicioSonbre);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new TelaInicialFragment()).addToBackStack(null).commit();
            }
        });
        return v;
    }

}
