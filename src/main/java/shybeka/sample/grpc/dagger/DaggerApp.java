package shybeka.sample.grpc.dagger;

import java.io.IOException;

public class DaggerApp {
  public static void main(String[] args) {
    DaggerBrewCoffeeServer server = new DaggerBrewCoffeeServer(8080);
    try {
      server.start();
      server.blockUntilShutdown();
    } catch (IOException | InterruptedException e) {
      System.err.println("Cannot start server... ");
      e.printStackTrace();
    }
  }
}
