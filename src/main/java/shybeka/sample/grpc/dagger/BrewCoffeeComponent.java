package shybeka.sample.grpc.dagger;

import com.google.common.util.concurrent.ListenableFuture;
import dagger.BindsInstance;
import dagger.producers.ProductionComponent;
import shybeka.sample.grpc.dagger.BrewCoffeeModule.BrewCoffeeResult;
import shybeka.sample.grpc.dagger.BrewCoffeeModule.UserName;

@ProductionComponent(modules = {BrewCoffeeModule.class, AsyncModule.class})
interface BrewCoffeeComponent {

  @BrewCoffeeResult
  ListenableFuture<String> brew();

  @ProductionComponent.Builder
  interface Builder {
    @BindsInstance
    Builder userName(@UserName String userName);

    BrewCoffeeComponent build();
  }
}
