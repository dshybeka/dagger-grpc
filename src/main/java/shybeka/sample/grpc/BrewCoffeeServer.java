package shybeka.sample.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import shybeka.sample.proto.BrewCoffeeGrpc;
import shybeka.sample.proto.BrewCoffeeRequest;
import shybeka.sample.proto.BrewCoffeeResponse;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BrewCoffeeServer {

  private final int port;
  private final Server server;

  BrewCoffeeServer(int port) {
    this.port = port;
    this.server = ServerBuilder.forPort(port).addService(new BrewCoffeeService()).build();
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
                    BrewCoffeeServer.this.stop();
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

  private static class BrewCoffeeService extends BrewCoffeeGrpc.BrewCoffeeImplBase {
    @Override
    public void brew(
        BrewCoffeeRequest request, StreamObserver<BrewCoffeeResponse> responseObserver) {
      responseObserver.onNext(
          BrewCoffeeResponse.newBuilder().setMessage(request.getName() + " is ready!").build());
      responseObserver.onCompleted();
    }
  }
}
