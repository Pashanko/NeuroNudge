package ua.pashanko.neuronudge;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class WhiteNoiseActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whitenoise);
    }

    public void stopNoise(View view) {
        isStarted = false;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void startNoise(View view) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        Resources resources = getResources();
        isStarted = true;
        int resourceId = R.raw.whitenoise;
        Uri uriSound = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(resourceId))
                .appendPath(resources.getResourceTypeName(resourceId))
                .appendPath(resources.getResourceEntryName(resourceId))
                .build();

        mediaPlayer.setDataSource(this, uriSound);
        mediaPlayer.prepare();
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }


}