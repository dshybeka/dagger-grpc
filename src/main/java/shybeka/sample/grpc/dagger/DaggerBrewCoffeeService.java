package shybeka.sample.grpc.dagger;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.grpc.server.GrpcService;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import shybeka.sample.proto.BrewCoffeeGrpc;
import shybeka.sample.proto.BrewCoffeeRequest;
import shybeka.sample.proto.BrewCoffeeResponse;

//@GrpcService(grpcClass = BrewCoffeeGrpc.class)
public class DaggerBrewCoffeeService extends BrewCoffeeGrpc.BrewCoffeeImplBase {

//  @Inject
//  public DaggerBrewCoffeeService() {}

  @Override
  public void brew(BrewCoffeeRequest request, StreamObserver<BrewCoffeeResponse> responseObserver) {
    ListenableFuture<String> future =
        DaggerBrewCoffeeComponent.builder().userName(request.getName()).build().brew();

    // for local tests only
    Futures.addCallback(
        future,
        new FutureCallback<String>() {
          @Override
          public void onSuccess(String message) {
            responseObserver.onNext(BrewCoffeeResponse.newBuilder().setMessage(message).build());
            responseObserver.onCompleted();
          }

          @Override
          public void onFailure(Throwable throwable) {
            responseObserver.onError(throwable);
          }
        },
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
  }
}
