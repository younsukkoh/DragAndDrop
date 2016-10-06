package miscellaneous.younsukkoh.draganddrop;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "YOLO";

    private Button mButton;
    private ImageView mImageView;
    private TextView mTotalDropTextView, mSuccessfulDropTextView;
    private LinearLayout mImagesLinearLayout;
    private int totalDropCount, totalSuccessCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mButton = (Button) findViewById(R.id.ma_b_button);
        mImageView = (ImageView) findViewById(R.id.ma_iv_image);
        mTotalDropTextView = (TextView) findViewById(R.id.ma_tv_totalDrop);
        mSuccessfulDropTextView = (TextView) findViewById(R.id.ma_tv_successfulDrop);
        mImagesLinearLayout = (LinearLayout) findViewById(R.id.ma_ll_images);

        mImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int action = dragEvent.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.i(TAG, "started");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.i(TAG, "enter");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.i(TAG, "exit");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.i(TAG, "drop");
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.i(TAG, "end");
//                        totalDropCount ++;
//                        totalSuccessCount ++;
//                        mTotalDropTextView.setText("Total Drop: " + totalDropCount);
//                        mSuccessfulDropTextView.setText("Succesful Drop: " + totalSuccessCount);
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });


        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(mImageView);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    view.startDragAndDrop(data, shadow, null, 0);
                else
                    view.startDrag(data, shadow, null, 0);
                return false;
            }
        });

        mImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return false;
            }
        });
    }
}
