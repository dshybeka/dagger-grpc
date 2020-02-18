package shybeka.sample.grpc.dagger;

import dagger.Module;
import dagger.Provides;
import dagger.producers.Production;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Module
class AsyncModule {

  @Provides
  @Production
  static Executor executor() {
    return Executors.newCachedThreadPool();
  }
}
