package com.spotify.oauth2.utils;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.pojo.Error;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CommonAssertions {
public CommonAssertions(){}
    public static void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
//        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public static void assertError(Error responseError, StatusCode statusCode){
        assertThat(responseError.getInnerError().getStatus(), equalTo(statusCode.code));
        assertThat(responseError.getInnerError().getMessage(), equalTo(statusCode.message));
    }
}
