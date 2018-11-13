package tcc.studio.com.edupv;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizarMateriais extends Fragment {


    public OrganizarMateriais() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_organizar_materiais, container, false);
        // Inflate the layout for this fragment

        getActivity().setTitle("Organização do Material");

        //atribuindo id a variavel
        ImageView bandeja  =  (ImageView)  v.findViewById(R.id.imgBandeja);
        ImageView garrot  =  (ImageView)  v.findViewById(R.id.imgGarrote);
        ImageView alcool = (ImageView) v.findViewById(R.id.imgAlcool);
        ImageView algodao  =  (ImageView)  v.findViewById(R.id.imgAlgodao);
        ImageView esparadrapo  =  (ImageView)  v.findViewById(R.id.imgEsparadrapo);
        ImageView luvas = (ImageView) v.findViewById(R.id.imgLuvas);
        ImageView tubo  =  (ImageView)  v.findViewById(R.id.imgTubo);
        //ImageView dispositivo  =  (ImageView)  v.findViewById(R.id.imgDispCOnectar);
        ImageView equipo = (ImageView) v.findViewById(R.id.imgEquipo);
        ImageView seringa  =  (ImageView)  v.findViewById(R.id.imgSeringa);
        ImageView soro  =  (ImageView)  v.findViewById(R.id.imgSoro);



        //alterandoImagem

        Button anterio = (Button) v.findViewById(R.id.btnAnterior);
        Button prox = (Button) v.findViewById(R.id.btnProx);

        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.telaBotton, new IdentificarVeias()).
                        addToBackStack(null).commit();

            }
        });

        anterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().
                        replace(R.id.telaBotton, new ListagemEstudo()).
                        addToBackStack(null).commit();
            }
        });
        return v;

    }


    public static Bitmap redimensionarFromResource(Resources res, int resId,
                                                   int larguraDesejada,
                                                   int alturaDesejada){

        final BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calcularTamanho(options, larguraDesejada, alturaDesejada);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource (res, resId, options);
    }



    private static int calcularTamanho(BitmapFactory.Options options, int larguraDesejada, int alturaDesejada) {

        //tamaho original da imagem

        final int altura = options.outHeight;
        final int largura = options.outWidth;
        int fator = 1;

        if(altura>alturaDesejada){
            final int metadeAltura = altura /2;
            final int medateLargura =largura/2;

            while ((metadeAltura/fator)>alturaDesejada && (medateLargura/fator)>larguraDesejada){
                fator*=2;
            }
        }
        return fator;
    }

}
