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

    private static final int DATABASE_VERSION = 8;
    // Database Name
    private static final String DATABASE_NAME = "edupvQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public BD(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPT1 +" TEXT, "
                +KEY_OPT2 +" TEXT, "+KEY_OPT3 +" TEXT, "+KEY_OPT4 +" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions()
    {
        Questao q1=new Questao("Ao administrar uma medicação intra-Venosa(IV), devemos faze-lo: ", "Rapidamente, pois estamos com pressa",
                "Lentamente, pois toda e qualquer medicação deve ser feita dessa forma", "A velocidade não é importante e sim a prescrição",
                "Nenhuma das anteriores","Nenhuma das anteriores");
        this.addQuestion(q1);

        Questao q2=new Questao("Na coleta de sangue venoso para exame,o garrote deve ser:", "Retirado após a punção da veia",
                "Mantido durante a punção da veia", "Retirado e mantido alternadamente", "Abolido, pois não há necessidade de seu uso.",
                "Retirado e mantido alternada mente");
        this.addQuestion(q2);
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
        values.put(KEY_ANSWER, quest.getResposta());
        values.put(KEY_OPT1, quest.getOpc1());
        values.put(KEY_OPT2, quest.getOpc2());
        values.put(KEY_OPT3, quest.getOpc3());
        values.put(KEY_OPT4, quest.getOpc4());
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
                quest.setResposta(cursor.getString(2));
                quest.setOpc1(cursor.getString(3));
                quest.setOpc2(cursor.getString(4));
                quest.setOpc3(cursor.getString(5));
                quest.setOpc4(cursor.getString(6));
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
