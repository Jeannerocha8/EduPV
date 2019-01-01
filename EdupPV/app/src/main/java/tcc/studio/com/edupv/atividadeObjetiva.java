package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import tcc.studio.com.edupv.BD.BD;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeObjetiva extends Fragment {
    List<Questao> quesList;
    int score=0 ;
    int questaon=1;
    int qid=0;
    Questao currentQ;
    TextView txtQuestion, ponto, numquestao;
    RadioButton rd1, rd2, rd3, rd4, answer;
    Button butNext;
    RadioGroup grp;
    Fragment fragment;

    public atividadeObjetiva() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_objetiva, container, false);


        //alterando titulo da Pagina
        getActivity().setTitle("Atividade Objetiva");


        //intanciando banco de dados
        BD db=new BD(getActivity());
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);

        //localizando ids
        txtQuestion=(TextView)v.findViewById(R.id.textView1);
        rd1=(RadioButton) v.findViewById(R.id.radio0);
        rd2=(RadioButton)v.findViewById(R.id.radio1);
        rd3=(RadioButton)v.findViewById(R.id.radio2);
        rd4=(RadioButton)v.findViewById(R.id.radio3);
        ponto = (TextView) v.findViewById(R.id.ponto);
        numquestao = (TextView) v.findViewById(R.id.numquestao);
        ponto.setText("Pontos: " + score);
        numquestao.setText("Questao" + questaon+"/ 2");
        butNext=(Button)v.findViewById(R.id.button1);
        grp = (RadioGroup) v.findViewById(R.id.radioGroup1);


        //chamando metodo questão
        setQuestionView();



        //evento de click no botão proximo

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instanciando o allert
                final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

                //pegando a resposta do usuario
                answer = (RadioButton) getActivity().findViewById(grp.getCheckedRadioButtonId());

                if (currentQ.getResposta().equals(answer.getText().toString())) {

                    //incrementando ponto e numero da questão
                    score = score + 1;
                    questaon = questaon + 1;

                    //setando questao
                    ponto.setText("Pontos: " + score);
                    numquestao.setText("Questao " + questaon+"/ 2");


                    //Imprimindo mensagem com Alerta
                    alerta.setTitle("Parabéns, você acertou");
                    alerta.setIcon(R.drawable.certo);
                    alerta .setMessage("")
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();

                }else{

                    //imprimindo alerta
                    alerta.setTitle("Que pena, você errou");
                    alerta.setIcon(R.drawable.errado);
                    alerta .setMessage("")
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();
                }

                if (qid < 2) {

                    //limpando check box
                    grp.clearCheck();
                    currentQ = quesList.get(qid);

                    setQuestionView();

                }else{

                    removerView();
                    Pontuacao.qtdQuest = 2;
                    Pontuacao.pontuacao = score;
                    butNext.setText("finalizar");
                    getFragmentManager().
                            beginTransaction().replace(R.id.frame_container, new ResultadoQuiz()).
                            addToBackStack(null).commit();
                }
            }
        });
        return v;
    }



    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getEnunciado());
        rd1.setText(currentQ.getOpc1());
        rd2.setText(currentQ.getOpc2());
        rd3.setText(currentQ.getOpc3());
        rd4.setText(currentQ.getOpc4());
        qid++;
    }
    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if (layout != null) {
            layout.removeAllViews();
        }
    }
}