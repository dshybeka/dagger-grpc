package shybeka.sample.grpc;

import java.io.IOException;

public class App {
    public static void main(String args[]) {
        BrewCoffeeServer server = new BrewCoffeeServer(8080);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException | InterruptedException e) {
            System.err.println("Cannot start server... ");
            e.printStackTrace();
        }

    }
}
