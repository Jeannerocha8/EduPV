package tcc.studio.com.edupv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaInicialFragment extends Fragment {


    public TelaInicialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View v =  inflater.inflate(R.layout.fragment_tela_inicial, container, false);

            getActivity().setTitle("EDUPV");
        //setando os botoes
        Button tutorial = (Button) v.findViewById(R.id.btnEstudo);
        Button multimidia = (Button) v.findViewById(R.id.btnVideo);
        Button atividades = (Button) v.findViewById(R.id.btnAtividade);
        Button sobrenos = (Button) v.findViewById(R.id.btnSobre);

        //click nos botoes
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    removerView();
                    getFragmentManager().
                            beginTransaction().
                            replace(R.id.frame_container, new ListagemEstudo()).addToBackStack(null).
                            commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        multimidia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    removerView();
                    getFragmentManager().
                            beginTransaction().
                            replace(R.id.frame_container, new ListagemVideo()).addToBackStack(null).
                            commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerView();
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.frame_container, new Atividade()).addToBackStack(null).
                        commit();
            }
        });

        sobrenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerView();
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.frame_container, new SobreNos()).addToBackStack(null).
                        commit();
            }
        });
        return v;
    }


    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if(layout!= null){
            layout.removeAllViews();
        }


    }


}
