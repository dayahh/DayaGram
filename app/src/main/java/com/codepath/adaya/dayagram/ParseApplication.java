package com.codepath.adaya.dayagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

        // Initializes Parse SDK as soon as the application is created
        @Override
        public void onCreate() {
            super.onCreate();

            // Register your parse models
            ParseObject.registerSubclass(Post.class);

            Parse.initialize(new Parse.Configuration.Builder(this)
                    .applicationId("KtvzMAJ5yfXTjXblIdJeinC7ynlO1NTRZMa3s3T2")
                    .clientKey("YsUvBZYBl3gH5nK1EPbUTqcAWbyihqWMtusokc4g")
                    .server("https://parseapi.back4app.com")
                    .build()
            );
        }
}
