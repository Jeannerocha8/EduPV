package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class IdentificarDispositivos extends Fragment {


    public IdentificarDispositivos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_identificar_dispositivos, container, false);

        setHasOptionsMenu(true);
        Button anterio = (Button) v.findViewById(R.id.btnAnteriorIDV);
        Button prox = (Button) v.findViewById(R.id.btnProxIDV);

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().
                        beginTransaction().replace(R.id.frame_container, new IdentificarVeias()).
                        addToBackStack(null).commit();
            }
        });

        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().
                        beginTransaction().replace(R.id.frame_container, new Higienizacao())
                        .addToBackStack(null).commit();
            }
        });

        getActivity().setTitle("Identificação de Dispositivos");
        return v;
    }

    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if (layout != null) {
            layout.removeAllViews();
        }
    }


    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }
}
