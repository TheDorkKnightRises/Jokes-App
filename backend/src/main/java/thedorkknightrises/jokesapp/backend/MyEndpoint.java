/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package thedorkknightrises.jokesapp.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import thedorkknightrises.java.libjokes.JokeText;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.jokesapp.thedorkknightrises",
    ownerName = "backend.jokesapp.thedorkknightrises",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "sayHi")
    public MyBean sayHi() {
        MyBean response = new MyBean();
        response.setData(new JokeText().getJokeText());

        return response;
    }

}
