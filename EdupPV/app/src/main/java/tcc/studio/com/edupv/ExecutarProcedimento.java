package tcc.studio.com.edupv;


import android.content.Intent;
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
public class ExecutarProcedimento extends Fragment {


    public ExecutarProcedimento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_executar_procedimento, container, false);

        setHasOptionsMenu(true);
        getActivity().setTitle("Execução do procedimento");

        Button anterior = (Button) v.findViewById(R.id.btnAnteriorEx);
        Button home = (Button) v.findViewById(R.id.btnhome);


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new Higienizacao()).
                        addToBackStack(null)
                        .commit();
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), TelaPrincipal.class);
                startActivity(intent);
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

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }
}
