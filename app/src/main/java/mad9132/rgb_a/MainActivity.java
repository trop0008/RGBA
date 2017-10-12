package mad9132.rgb_a;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.RGBAModel;

/**
 * The Controller for RGBAModel.
 *
 * As the Controller:
 *   a) event handler for the View
 *   b) observer of the Model (RGBAModel)
 *
 * Features the Update / React Strategy.
 *
 * @author Gerald.Hurdle@AlgonquinCollege.com
 * @version 1.0
 */
public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {
    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG          = "RGBA";

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'
    private AboutDialogFragment mAboutDialog;
    private TextView            mColorSwatch;
    private RGBAModel           mModel;
    private SeekBar             mRedSB;
    private SeekBar             mGreenSB;
    private SeekBar             mBlueSB;
    private SeekBar             mAlphaSB;
    //TODO: declare private members for mGreenSB, mBlueSB, and mAlphaSB
    private TextView            mRedTV;
    private TextView            mGreenTV;
    private TextView            mBlueTV;
    private TextView            mAlphaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new RGBA model
        // Initialize the model red (max), green (min), blue (min), and alpha (max)
        mModel = new RGBAModel();
        mModel.setRed( RGBAModel.MAX_RGB );
        mModel.setGreen( RGBAModel.MIN_RGB );
        mModel.setBlue( RGBAModel.MIN_RGB );
        mModel.setAlpha( RGBAModel.MAX_ALPHA );
        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver( this );

        // reference each View
        mColorSwatch = (TextView) findViewById( R.id.colorSwatch );
        mRedSB = (SeekBar) findViewById( R.id.redSB );
        mGreenSB = (SeekBar) findViewById( R.id.greenSB );
        mBlueSB = (SeekBar) findViewById( R.id.blueSB );
        mAlphaSB = (SeekBar) findViewById( R.id.alphaSB );
        //TODO: reference the remaining <SeekBar>s: green, blue and alpha
        mRedTV = (TextView) findViewById( R.id.red );
        mGreenTV = (TextView) findViewById( R.id.green );
        mBlueTV = (TextView) findViewById( R.id.blue );
        mAlphaTV = (TextView) findViewById( R.id.alpha );
        // set the domain (i.e. max) for each component

        //TODO: setMax() for the remaining <SeekBar>s: green, blue and alpha
        mRedSB.setMax( RGBAModel.MAX_RGB );
        mGreenSB.setMax( RGBAModel.MAX_RGB );
        mBlueSB.setMax( RGBAModel.MAX_RGB );
        mAlphaSB.setMax( RGBAModel.MAX_RGB );
        // register the event handler for each <SeekBar>

        //TODO: register the remaining <SeekBar>s: green, blue and alpha
        mRedSB.setOnSeekBarChangeListener( this );
        mGreenSB.setOnSeekBarChangeListener( this );
        mBlueSB.setOnSeekBarChangeListener( this );
        mAlphaSB.setOnSeekBarChangeListener( this );
        // initialize the View to the values of the Model
        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch ( item.getItemId() ) {

            case R.id.action_about:
                mAboutDialog.show( getFragmentManager(), ABOUT_DIALOG_TAG );
                return true;


            case R.id.action_black:
                mModel.asBlack();
                return true;

            case R.id.action_blue:
                mModel.asBlue();
                return true;

            case R.id.action_cyan:
                mModel.asCyan();
                return true;

            case R.id.action_green:
                mModel.asGreen();
                return true;


            case R.id.action_magenta:
                mModel.asMagenta();
                return true;

            case R.id.action_red:
                mModel.asRed();
                return true;

            case R.id.action_yellow:
                mModel.asYellow();
                return true;

            case R.id.action_white:
                mModel.asWhite();
                return true;






            //TODO: handle the remaining menu items

            default:
                Toast.makeText(this, "MenuItem: " + item.getTitle(), Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Event handler for the <SeekBar>s: red, green, blue, and alpha.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if ( fromUser == false ) {
            return;
        }

        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
        switch ( seekBar.getId() ) {
            case R.id.redSB:
                mModel.setRed( mRedSB.getProgress() );
                mRedTV.setText( getResources().getString(R.string.redProgress, progress).toUpperCase() );
                break;

            //TODO: case R.id.greenSB
            case R.id.greenSB:
                mModel.setGreen( mGreenSB.getProgress() );
                mGreenTV.setText( getResources().getString(R.string.greenProgress, progress).toUpperCase() );
                break;

            //TODO: case R.id.blueSB

            case R.id.blueSB:
                mModel.setBlue( mBlueSB.getProgress() );
                mBlueTV.setText( getResources().getString(R.string.blueProgress, progress).toUpperCase() );
                break;

            //TODO: case R.id.alphaSB

            case R.id.alphaSB:
                mModel.setAlpha( mAlphaSB.getProgress() );
                mAlphaTV.setText( getResources().getString(R.string.alphaProgress, progress).toUpperCase() );
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.redSB:
                mRedTV.setText( getResources().getString(R.string.red) );
                break;
        }
    }

    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        Log.i( LOG_TAG, "The color (int) is: " + mModel.getColor() + "" );

        this.updateView();
    }

    private void updateBlueSB() {
        //TODO: set the blueSB's progress to the model's blue value
    }

    private void updateColorSwatch() {
        //GET the model's r,g,b,a values, and SET the background colour of the swatch <TextView>
        mColorSwatch.setBackgroundColor(Color.argb(mModel.getAlpha()
                , mModel.getRed()
                , mModel.getGreen()
                , mModel.getBlue()));
    }

    private void updateGreenSB() {
        //TODO: set the greenSB's progress to the model's green value
    }

    private void updateRedSB() {
        //GET the model's red value, and SET the red <SeekBar>
        mRedSB.setProgress( mModel.getRed() );
    }

    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateRedSB();
        this.updateGreenSB();
        this.updateBlueSB();
    }
}   // end of class
