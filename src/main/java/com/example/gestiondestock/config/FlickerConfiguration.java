package com.example.gestiondestock.config;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.flickr4java.flickr.Flickr;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

//Apres la premiere exectuion il faut soit commenter l'annotation @Configuration soit supprimer la totalité de classe
@Configuration
public class FlickerConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    // ******* 2eme methodes pour connecter l'application a flicker ( methode 1 implemneté deja dans FlickerServiceImpl) ********
    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;
    //***********************************


   /* @Bean // pour au demarrage de l'application Spring va demarer cette application
    public Flickr getFlicker() throws InterruptedException, ExecutionException, IOException, FlickrException {

        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
        // *** Permissions ***
        // Write --> Read and write
        // READ Read only
        // Delete read, write and delete

        final Scanner scanner = new Scanner(System.in);
        final OAuth1RequestToken request = service.getRequestToken();
        final String authUrl = service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");
        final String authVerifier = scanner.nextLine();
        OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("------------------------------------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());
        return flickr;
    } */


    /******* Suite 2eme methode ************/
    @Bean
    public Flickr getFlicker(){
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);

        return flickr;
    }

    /***********/
}
