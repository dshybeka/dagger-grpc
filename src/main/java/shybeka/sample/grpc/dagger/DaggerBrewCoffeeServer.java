package shybeka.sample.grpc.dagger;

import static java.util.concurrent.TimeUnit.SECONDS;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

class DaggerBrewCoffeeServer {
  private final int port;
  private final Server server;

  DaggerBrewCoffeeServer(int port) {
    this.port = port;
    this.server = ServerBuilder.forPort(port).addService(new DaggerBrewCoffeeService()).build();
  }

  void start() throws IOException {
    server.start();
    System.out.println("Server started on port " + port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  System.out.println("--------------------------------");
                  System.out.println("--------Start shut down---------");
                  System.out.println("--------------------------------");
                  try {
                    DaggerBrewCoffeeServer.this.stop();
                  } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                  }
                  System.out.println("--------------------------------");
                  System.out.println("--------End shut down-----------");
                  System.out.println("--------------------------------");
                }));
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(10, SECONDS);
    }
  }

  /** Await termination on the main thread since the grpc library uses daemon threads. */
  void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
}
