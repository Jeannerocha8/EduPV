package tcc.studio.com.edupv;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadoQuiz extends Fragment {


    public ResultadoQuiz() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultado_quiz, container, false);

        //alterando o titulo da pagina
        getActivity().setTitle("Resultado");

        int p = Pontuacao.pontuacao;

        int qtd = 14;

        float porcentagem = (p/qtd)*100;

        TextView t=(TextView)v.findViewById(R.id.textResult);
        Button inicio  = (Button)  v.findViewById(R.id.btninicio);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TelaPrincipal.class);
                startActivity(intent);
            }
        });

        //get rating bar object
        RatingBar bar=(RatingBar)v.findViewById(R.id.ratingBar1);
        bar.setIsIndicator(false);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);

        // adicionando estrelas
        LayerDrawable stars = (LayerDrawable) bar.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.rgb(255, 219, 88), PorterDuff.Mode.SRC_ATOP);

        //adicionando mensagem
        t.setText("Você acertou " + p + " de 14 questões");



       switch (p)
        {
            case 0:
                t.setText("Que pena, você não acertou nenhuma questão");
                bar.setNumStars( 0);
                stars.getDrawable(1).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                break;
            case 1:
                bar.setNumStars( 2);
                stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 2:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

            break;
            case 3:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 4:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 5:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 6:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 7:
                bar.setNumStars(2);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 8:
                bar.setNumStars(3);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 9:
                bar.setNumStars(3);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 10:
                bar.setNumStars(3);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 11:
                bar.setNumStars(3);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 12:
                bar.setNumStars(3);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 13:
                bar.setNumStars(5);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;
            case 14:
                bar.setNumStars(5);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                break;

        }

        return v;
    }
}
