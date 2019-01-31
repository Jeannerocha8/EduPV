package tcc.studio.com.edupv;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class Higienizacao extends Fragment {


    public Higienizacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_higienizacao, container, false);

        setHasOptionsMenu(true);
        getActivity().setTitle("Higienização");

       /*ImageView gif = (ImageView) v.findViewById(R.id.imgGif);
       Glide.with(getActivity()).load(R.drawable.gifmao1).asGif().crossFade().
               into(gif).onStart();

        ImageView gif2 = (ImageView) v.findViewById(R.id.imgGif2);
        Glide.with(getActivity()).load(R.drawable.gifmao2).asGif().crossFade().
                into(gif).onStart();*/

       Button proximo = (Button) v.findViewById(R.id.btnProxHi);
       Button anterior = (Button) v.findViewById(R.id.btnAnteriorHi);

       proximo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       anterior.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               getFragmentManager().beginTransaction()
                       .replace(R.id.frame_container, new IdentificarDispositivos())
                       .addToBackStack(null).
                       commit();
           }
       });

       proximo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               getFragmentManager().beginTransaction().
                       replace(R.id.frame_container, new ExecutarProcedimento()).
                       addToBackStack(null).
                       commit();
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
