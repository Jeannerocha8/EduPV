package tcc.studio.com.edupv.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tcc.studio.com.edupv.Questao;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_ANSWER;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_ID;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_OPT1;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_OPT2;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_OPT3;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_OPT4;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.KEY_QUES;
import static tcc.studio.com.edupv.BD.QuizNegocio.MovieEntry.TABLE_QUEST;


public class BD  extends  SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "bdedupv";
    // tasks table name

    private SQLiteDatabase dbase;
    public BD(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, "+KEY_OPT1 +" TEXT, "
                +KEY_OPT2 +" TEXT, "+KEY_OPT3 +" TEXT, "+KEY_OPT4 +" TEXT, " + KEY_ANSWER+ " TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions()
    {
        Questao q1=new Questao("É correto afirmar em relação à Terapia Intravenosa:                    ",
                "A punção venosa periférica é um procedimento frequente, realizado pelos" +
                        " profissionais   de   enfermagem, exigindo   competência técnica   para" +
                        "   sua realização.",
                "Para a realização da punção venosa periférica destaca-se a destreza manua" +
                        "l e o domínio de anatomia e fisiologia da rede venosa e arterial.",
                "avaliação do acesso intravascular faz parte do cuidado de enfermagem.",
                "Todas as afirmativas acima estão corretas.",
                "Todas as afirmativas acima estão corretas.");
        this.addQuestion(q1);

        Questao q2=new Questao("Cabe    ao    enfermeiro    conhecimento, habilidade    e    prática    no procedimento adequado de inserção do CCIP.  Para tanto," +
                " assinale as veias mais indicadas como primeiras escolhas na punção venosa.       ",
                "Jugular externa e cefálica",
                "Cefálica e basílica.",
                "Jugular interna e basílica",
                "Cefálica e pulmonar",
                "Jugular interna e basílica");
        this.addQuestion(q2);


        Questao q3=new Questao("É correto afirmar que as artérias e veias possuem três camadas de tecido que formam a parede. São elas as túnicas:",
                "Adventícia, média, íntima",
                "Longa, média, superficial",
                "Superficial, média, curta",
                "Adventícia, longa, média",
                "Adventícia, média, íntima");
        this.addQuestion(q3);

        Questao q4=new Questao("Assinale a alternativa correta, identificando as veias superficiais do dorso da mão.              ",
                "Metacarpianas, basílica",
                "Digitais, metacarpianas.",
                "Digitais cefálicas.",
                "Metacarpianas, antecubital",
                "Metacarpianas, basílica");
        this.addQuestion(q4);

        Questao q5=new Questao("Identifique, nas   opções   abaixo, o   que   o   enfermeiro   leva   em consideração ao selecionar uma região de punção:       ",
                "A fossa antecubital deve ser a primeira opção",
                "Utilizar primeiro o local mais distal do braço ou da mão",
                "osicionar em 45º ângulo para punção",
                "Utilizar a veia jugular externa para inserção de cateteres periféricos.",
                "Utilizar primeiro o local mais distal do braço ou da mão");
        this.addQuestion(q5);

        Questao q6=new Questao("Sobre a seleção dos sítios de inserção no cuidado de enfermagem, o enfermeiro tem a responsabilidade de:               ",
                "Realizar uma assistência de qualidade, a fim de evitar ou minimizar potenciais complicações tendo em vista a segurança dos clientes.",
                "Ter conhecimento dos cuidados necessários para a inserção e manutenção dos cateteres vasculares, riscos e complicações.",
                "Atuar com outros profissionais na construção e implantação dos protocolos: médico, farmacêutico, CCIH e a direção da Instituição.",
                "Todas as opções estão corretas.",
                "Todas as opções estão corretas.");
        this.addQuestion(q6);

        Questao q7=new Questao("É correto ressaltar a importância de cuidados na seleção das veias. Dentre esses, destaca-se:                       ",
                "O enfermeiro não precisa ter habilidade para inserir o cateter",
                "O conforto do cliente é considerado em segundo plano",
                "O tamanho do vaso e sua condição prioritariamente",
                "A rede venosa deve ser examinada apenas pela observação",
                "O tamanho do vaso e sua condição prioritariamente");
        this.addQuestion(q7);

        Questao q8=new Questao("Ao realizar uma punção venosa, o uso de luvas de procedimentos é obrigatório para prevenir contaminação do:                        ",
                "Ambiente",
                "Paciente",
                "Material Utilizado",
                "Profissional",
                "Profissional");
        this.addQuestion(q8);

        Questao q9=new Questao("A realização da punção venosa é uma técnica importante que requer conhecimento e habilidade. Assinale a alternativa correta em relação ao assunto.   ",
                "Sempre utilizar luvas estéreis durante a punção venosa periférica, pois trata-se de um procedimento de assepsia cirúrgica",
                "As veias de membros inferiores para punção podem ser utilizadas uma vez que não há risco de embolias e tromboflebites.",
                "É indicado desconectar o equipo de soro para troca de roupas, banho, transporte do paciente, entre outros",
                "Os locais de escolha para a punção devem respeitar os vasos distais e depois proximais",
                "Os locais de escolha para a punção devem respeitar os vasos distais e depois proximais");
        this.addQuestion(q9);


        Questao q10=new Questao("Na coleta de sangue venoso para exame, o garrote deve ser:                 :             ",
                "Mantido durante a punção da veia",
                "Retirado e mantido alternada mente",
                "Retirado após a punção da veia",
                "Abolido, pois não há necessidade de seu uso.",
                "Retirado após a punção da veia");
        this.addQuestion(q10);

        Questao q11=new Questao("Sobre as medidas que facilitam o aparecimento da veia na punção venosa, marque V para as afirmativas verdadeiras e F para as falsas. ( ) Massagem local. ( ) Compressas quentes. ( ) Abrir e fechar a mão. ( ) Pancadas sobre a veia. A sequência está correta em: ",
                "V, F, V, F",
                "F, V, F, V",
                "V, V, V, F",
                "V, V, F, F",
                "V, V, V, F");
        this.addQuestion(q11);

        Questao q12=new Questao("A punção venosa deve ser realizada de preferência por dois profissionais. As principais complicações da punção venosa são:     ",
                "tromboflebite, sangramento e infiltração.",
                "infiltração, extravasamento e flebite.",
                "Infecção, sepse e obstrução.",
                "formação de hematomas, sangramento e flebite.",
                "infiltração, extravasamento e flebite.");
        this.addQuestion(q12);

        Questao q13=new Questao("Imediatamente após o contato direto com sangue nas mãos, durante punção venosa em paciente sem histórico de doença infecciosa, feita inadvertidamente sem luvas, cabe ao profissional de saúde:",
                "aplicar nas mãos preparação alcóolica gel a 70% por 10 segundos.",
                "fazer antissepsia das mãos com solução gel de Triclosan a 5%.",
                "higienizar as mãos com água e sabão.",
                "aplicar nas mãos solução à base de PVPI e aguardar a ação por 5 minutos.",
                "higienizar as mãos com água e sabão.");
        this.addQuestion(q13);

        Questao q14=new Questao("Qual a posição adequada do bisel da agulha quando da realização de uma punção venosa no dorso da mão direita, com técnica asséptica e escolha do cateter?    ",
                "O bisel da agulha deverá estar ângulo de 90 graus.",
                "O bisel da agulha deverá estar voltado para cima.",
                "O bisel da agulha deverá estar voltado para baixo.",
                "O bisel da agulha deverá estar esterilizado.",
                "O bisel da agulha deverá estar voltado para cima.");
        this.addQuestion(q14);

        Questao q15=new Questao("Ao utilizar álcool a 70% antes da realização de uma punção venosa, o auxiliar de enfermagem está realizando um processo de:       ",
                "assepsia.",
                "desinfecção.",
                "esterilização.",
                "antissepsia.",
                "antissepsia.");
        this.addQuestion(q15);


    }


    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // excluindo caso a tabela exista
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // adicionando questão
    public void addQuestion(Questao quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(KEY_ID, quest.getId());
        values.put(KEY_QUES, quest.getEnunciado());
        values.put(KEY_OPT1, quest.getOpc1());
        values.put(KEY_OPT2, quest.getOpc2());
        values.put(KEY_OPT3, quest.getOpc3());
        values.put(KEY_OPT4, quest.getOpc4());
        values.put(KEY_ANSWER, quest.getResposta());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Questao> getAllQuestions() {
        List<Questao> quesList = new ArrayList<Questao>();
        // Selecionando todas as linhas

        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Questao quest = new Questao();
                quest.setId(cursor.getInt(0));
                quest.setEnunciado(cursor.getString(1));
                quest.setOpc1(cursor.getString(2));
                quest.setOpc2(cursor.getString(3));
                quest.setOpc3(cursor.getString(4));
                quest.setOpc4(cursor.getString(5));
                quest.setResposta(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
