package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);
    }

//    public static Response get(String playlistId) {
//        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());
//    }

    public static Response get2(String playlistId) {
        return RestResource.get(PLAYLISTS + "/",playlistId, getToken());
    }

    public static Response update(Playlist requestPlaylist, String id) {
        return RestResource.update(PLAYLIST_ID, getToken(), requestPlaylist, id);
    }
}
