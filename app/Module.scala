import com.google.inject.AbstractModule
import java.time.Clock

import services.{ApplicationTimer, AtomicCounter, Counter, StripeService, PaymentService}
import databases.{IntakeFormsAPI, IntakeFormsAPIImpl}

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure(): Unit = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[AtomicCounter])
    // Bind database API implementations
    bind(classOf[IntakeFormsAPI]).to(classOf[IntakeFormsAPIImpl])
    // TODO: Add other API bindings when they are implemented

    // Bind services
    bind(classOf[StripeService]).asEagerSingleton()
    bind(classOf[PaymentService]).asEagerSingleton()
    // TODO: Add other service bindings when they are implemented
  }
}
