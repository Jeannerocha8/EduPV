package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
    int questaon=0;
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


        //intanciando banco de dados
        BD db = new BD(getActivity());
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);

        //chamando metodo questão
        setQuestionView();

        //evento de click no botão proximo
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(rd1.isChecked()||rd2.isChecked()||rd3.isChecked()||rd4.isChecked()){

                        VerificarResposta();

                    } else{
                        final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                        alerta.setTitle("Erro");
                        alerta.setIcon(R.drawable.errado);
                        alerta.setMessage("Por favor, selecione uma opção para continuar! ")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alertDialog = alerta.create();
                        alertDialog.show();
                    }
            }
        });
        return v;
    }

    private void VerificarResposta() {
        //pegando a resposta do usuario
        answer = (RadioButton) getActivity().findViewById(grp.getCheckedRadioButtonId());

        if (currentQ.getResposta().equals(answer.getText().toString())) {

            //incrementando ponto e numero da questão
            score = score + 1;

            //setando questao
            ponto.setText("Pontos: " + score);
        }

        MostrarSolucao();
    }

    private void MostrarSolucao() {
        if (currentQ.getResposta().equals(answer.getText().toString())) {
            answer.setTextColor(Color.GREEN);
        }else {
            answer.setTextColor(Color.RED);
            if (currentQ.getResposta().equals(rd1.getText().toString())) {
                rd1.setTextColor(Color.GREEN);
            } else if (currentQ.getResposta().equals(rd2.getText().toString())) {
                rd2.setTextColor(Color.GREEN);
            } else if (currentQ.getResposta().equals(rd3.getText().toString())) {
                rd3.setTextColor(Color.GREEN);
            } else if (currentQ.getResposta().equals(rd4.getText().toString())) {
                rd4.setTextColor(Color.GREEN);
            } else {
                Toast.makeText(getActivity(), "Erro, contate o desenvolvedor do sistema", Toast.LENGTH_LONG);
            }

            if (qid < 2) {
                butNext.setText("Próximo");

            } else {
                butNext.setText("Finalizar");
            }

        }

        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                setQuestionView();
            }
        }.start();
    }

    private void LimparOP(){
        grp.clearCheck();
        rd1.setTextColor(Color.BLACK);
        rd2.setTextColor(Color.BLACK);
        rd3.setTextColor(Color.BLACK);
        rd4.setTextColor(Color.BLACK);
    }

    private void setQuestionView(){
        LimparOP();

        if(qid<2){
            currentQ = quesList.get(qid);
            txtQuestion.setText(currentQ.getEnunciado());
            rd1.setText(currentQ.getOpc1());
            rd2.setText(currentQ.getOpc2());
            rd3.setText(currentQ.getOpc3());
            rd4.setText(currentQ.getOpc4());
            qid++;
            questaon++;
            numquestao.setText("Questao " + questaon+"/ 2");
        }else {
            Finalizar();
        }
    }

    private void Finalizar() {
        removerView();
        Pontuacao.qtdQuest = 2;
        Pontuacao.pontuacao = score;
        butNext.setText("finalizar");
        getFragmentManager().
                beginTransaction().replace(R.id.frame_container, new ResultadoQuiz()).
                addToBackStack(null).commit();
    }
    
    private void removerView() {
        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_container);
        if (layout != null) {
            layout.removeAllViews();
        }
    }
}