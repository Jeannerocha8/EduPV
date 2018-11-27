package tcc.studio.com.edupv;


import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class atividadeArrastar extends Fragment {


    public atividadeArrastar() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_atividade_arrastar, container, false);

        v.findViewById(R.id.img1).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.img2).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.img3).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.img4).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.img1botomcenter).setOnLongClickListener(new MyOnLongClickListener());
        v.findViewById(R.id.imgtopcenter).setOnLongClickListener(new MyOnLongClickListener());

        v.findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1));
        v.findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));
        v.findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(3));
        v.findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(4));
        v.findViewById(R.id.topcenter).setOnDragListener( new MyOnDragListener(5));
        v.findViewById(R.id.bottomtcenter).setOnDragListener( new MyOnDragListener(6));


        return v;
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            v.setVisibility(View.INVISIBLE);
            return(true);
        }
    }


    class MyOnDragListener implements View.OnDragListener {
        private int num;

        public MyOnDragListener(int num){
            super();
            this.num = num;
            }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("Script", num + " - ACTION_DRAG_STARTED");
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return (true);
                    }else{
                        return (false);
                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Script", num + " - ACTION_DRAG_ENTERED");
                    v.setBackgroundColor(Color.YELLOW);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", num + " - ACTION_DRAG_LOCATION");

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script", num + " - ACTION_DRAG_EXITED");
                    v.setBackgroundColor(Color.BLUE);
                    break;
                case DragEvent.ACTION_DROP:

                    Log.i("Script", num + " - ACTION_DROP");
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    if(view.getId()==R.id.img1botomcenter && num==1){
                        view.setVisibility(View.VISIBLE);
                      Toast.makeText(getActivity(), "Voce acertou!!", Toast.LENGTH_LONG).show();
                    }else
                    {
                        new MyOnLongClickListener();
                        Toast.makeText(getActivity(), "Voce errou!!", Toast.LENGTH_LONG).show();
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");
                    v.setBackgroundColor(Color.BLUE);
                    break;
                }
            return true;
        }}
    }
