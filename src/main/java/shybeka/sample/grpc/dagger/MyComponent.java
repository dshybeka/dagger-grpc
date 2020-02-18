package shybeka.sample.grpc.dagger;

import io.grpc.Server;

// @Singleton
// @Component(
//    modules = {
//      NettyServerModule.class,
//      DaggerBrewCoffeeServiceUnscopedGrpcServiceModule.class,
//      MyModule.class
//    })
public interface MyComponent {
  //    extends DaggerBrewCoffeeServiceServiceDefinition {
  Server server();
}
