package tcc.studio.com.edupv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class opcaoAdapter extends ArrayAdapter<opcoes> {


    private   Context context;
    private  ArrayList<opcoes> elementos;


    public opcaoAdapter (Context context,  ArrayList<opcoes> elementos)  {
        super(context,  R.layout.linha,  elementos);
        this.context =  context;
        this.elementos = elementos;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =  (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = convertView;

        itemView = inflater.inflate(R.layout.linha, parent, false);

        TextView titulo=  (TextView)  itemView.findViewById(R.id.titulo);
        TextView descricao=  (TextView)  itemView.findViewById(R.id.descricao);
        ImageView imagem  =  (ImageView)  itemView.findViewById(R.id.imagem);

        titulo.setText(elementos.get(position).getTitulo());
        descricao.setText(elementos.get(position).getDescricao());
        imagem.setImageResource(elementos.get(position).getImagem());

        return itemView;
    }


}
