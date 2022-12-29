package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.CommonAssertions.assertError;
import static com.spotify.oauth2.utils.CommonAssertions.assertStatusCode;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PlaylistTests extends BaseTest{
    String id;

    @Test(priority = 1)
    public void shouldBeAbleToCreateAPIPlaylist() {
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = PlaylistApi.post(requestPlaylist);

        id = response.path("id");

        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test(priority = 2)
    public void shouldBeAbleToGetAPIPlaylist() {
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = PlaylistApi.get2(id);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

        Playlist responsePlaylist = response.as(Playlist.class);//deserialize a json to responsePlaylist object
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }

    @Test(priority = 3)
    public void shouldBeAbleToUpdateAPIPlaylist() {
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", false);
        Response response = PlaylistApi.update(requestPlaylist, id);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Test(priority = 4)
    public void shouldNotBeAbleToCreateAPIPlaylistWithName() {
        Playlist requestPlaylist = playlistBuilder("", "Updated playlist description", false);
        Response response = PlaylistApi.post(requestPlaylist);

//        assertError(response.as(Error.class), 400, "Missing required field: name");
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Test(priority = 5)
    public void shouldNotBeAbleToCreateAPIPlaylistWithExpiredToken() {
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = PlaylistApi.post(DataLoader.getInstance().getInvalidToken(), requestPlaylist);

        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }

    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().//when we are using Builder pattern
                name(name).
                description(description).
                _public(_public).
                build();
//        Playlist playlist = new Playlist();//For getters and Setters
//        playlist.setName(name);
//        playlist.setDescription(description);
//        playlist.set_public(_public);
//        return playlist;
    }

    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }


}
