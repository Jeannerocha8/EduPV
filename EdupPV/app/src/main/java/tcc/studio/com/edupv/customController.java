package tcc.studio.com.edupv;

import android.content.Context;
import android.view.View;
import android.widget.MediaController;

public class customController extends MediaController {
    public customController(Context context, View anchor) {

        super(context);
        super.setAnchorView(anchor);
    }

    public void setAnchorView(){

    }
}
