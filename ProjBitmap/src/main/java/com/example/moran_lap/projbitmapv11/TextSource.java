package com.example.moran_lap.projbitmapv11;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;

/**
 * Created by Gili on 25/04/2016.
 */
public class TextSource extends ImageSource {

    private String text;
    private Bitmap bitmap;
    private Position position;

    public TextSource(String text, Position pos){
        sourceName = "Text";
        this.text = text;
        position = pos;
        bitmap = Bitmap.createBitmap(position.getWidth(),position.getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    public Bitmap getImage() {

        Resources resources = ApplicationContext.getActivity().getResources();
        float scale = resources.getDisplayMetrics().density;
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE); // Text Color
        paint.setStrokeWidth(12); // Text Size

        paint.setTextSize((int) (14 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        //int x = (originalBitmap.getWidth() - bounds.width())/2;
        //int y = (originalBitmap.getHeight() + bounds.height())/2;

        canvas.drawText(text, position.getxStart(), position.getyStart(), paint);
        //canvas.drawRect(position.getxStart(),position.getyStart(),position.getxEnd(),position.getyEnd(),paint);
        return bitmap;
    }

    @Override
    public void OpenSource() {

    }

    @Override
    public void CloseSource() {

    }

    @Override
    public void SetupSource() {

    }

    @Override
    public void EditSource() {
        Builder builder = new Builder(ApplicationContext.getActivity());
        builder.setTitle("Enter your text here");

        // Set up the input
        final EditText input = new EditText(ApplicationContext.getActivity());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text = input.getText().toString();
                bitmap = Bitmap.createBitmap(position.getWidth(),position.getHeight(), Bitmap.Config.ARGB_8888);
                ((MainActivity)ApplicationContext.getActivity()).refreshSurfaceComponentsOnBitmap();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
