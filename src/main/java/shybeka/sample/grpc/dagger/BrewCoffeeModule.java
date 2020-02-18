package shybeka.sample.grpc.dagger;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.producers.ProducerModule;
import dagger.producers.Produces;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ProducerModule
final class BrewCoffeeModule {

  @Produces
  @IsReady
  static ListenableFuture<Boolean> produceIsBrewed() {
    return Futures.immediateFuture(true);
  }

  @Produces
  @BrewCoffeeResult
  static ListenableFuture<String> lookUpUserData(
      @UserName String userName, @IsReady Boolean brewed) {
    return Futures.immediateFuture(
        brewed ? "Hey, " + userName + ", your coffee is ready!" : " Sorry, come back later :(");
  }

  @Qualifier
  @Retention(RUNTIME)
  public @interface UserName {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface IsReady {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface BrewCoffeeResult {}
}
