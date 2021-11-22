import org.glassfish.grizzly.http.server.HttpServer;
import student.server.AdventureResource;
import student.server.AdventureServer;

import java.io.IOException;

public class ServerMain {
  public static void main(String[] args) throws IOException {
    //Code for running the server
    HttpServer server = AdventureServer.createServer(AdventureResource.class);
    server.start();
  }
}
