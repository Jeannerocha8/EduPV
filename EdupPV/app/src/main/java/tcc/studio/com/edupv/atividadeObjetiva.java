package tcc.studio.com.edupv;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    int score=0;
    int qid=0;
    Questao currentQ;
    TextView txtQuestion;
    RadioButton rd1, rd2, rd3, rd4, answer;
    Button butNext;
    RadioGroup grp;


    public atividadeObjetiva() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_atividade_objetiva, container, false);

        BD db=new BD(getActivity());

        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)v.findViewById(R.id.textView1);
        rd1=(RadioButton) v.findViewById(R.id.radio0);
        rd2=(RadioButton)v.findViewById(R.id.radio1);
        rd3=(RadioButton)v.findViewById(R.id.radio2);
        rd4=(RadioButton)v.findViewById(R.id.radio3);

        rd4=(RadioButton)v.findViewById(R.id.radio3);
        butNext=(Button)v.findViewById(R.id.button1);
        setQuestionView();

        grp = (RadioGroup) v.findViewById(R.id.radioGroup1);


        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                answer = (RadioButton) getActivity().findViewById(grp.getCheckedRadioButtonId());

                if (currentQ.getResposta().equals(answer.getText().toString())) {

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
                    score++;
                    Log.d("score", "Your score" + score);
                }else{
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
                    grp.clearCheck();
                    currentQ = quesList.get(qid);
                    setQuestionView();
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

}