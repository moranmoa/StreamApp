package com.example.moran_lap.projbitmapv11;

/**
 * Created by moran on 07/05/2016.
 */
public class StreamSettings {
    private String server ;
    private String protocol ;
    //private String port ;
    private String stream ;
    private String streamKey ;
    private String url ;

    public StreamSettings(){
        server = new String("live-fra.twitch.tv");
        protocol= new String("rtmp://");
        stream = new String("app");
        streamKey = new String("live_65885373_T8EwyWUsW1OrI7wDfNY0CocJg0UJq7");
        url = protocol+server +"/"+ stream +"/"+ streamKey;
    }
}