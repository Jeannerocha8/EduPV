package tcc.studio.com.edupv.BD;

import android.provider.BaseColumns;

public class QuizNegocio {



    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_QUEST = "quest";
        // tasks Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_QUES = "questao";
        public static final String KEY_ANSWER = "resposta"; //correct option
        public static final String KEY_OPT1= "opc1"; //option a
        public static final String KEY_OPT2= "opc2"; //option b
        public static final String KEY_OPT3= "opc3"; //option c
        public static final String KEY_OPT4= "opc4"; //option d


    }

}
