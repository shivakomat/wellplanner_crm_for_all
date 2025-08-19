file://<WORKSPACE>/app/services/PaymentService.scala
### scala.reflect.internal.FatalError: 
  ThisType(method getPaymentIntentByStripeId) for sym which is not a class
     while compiling: file://<WORKSPACE>/app/services/PaymentService.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -deprecation -encoding utf8 -unchecked -Wconf:cat=unchecked:w -Wconf:cat=deprecation:w -Wconf:cat=deprecation:ws -Wconf:cat=feature:ws -Wconf:cat=optimizer:ws -classpath <WORKSPACE>/.bloop/wellplanner/bloop-bsp-clients-classes/classes-Metals-xeotakETSrOQAORHVekLXw==:<HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.0/semanticdb-javac-0.11.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/twirl-api_2.13/1.6.2/twirl-api_2.13-1.6.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-server_2.13/2.9.0/play-server_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-akka-http-server_2.13/2.9.0/play-akka-http-server_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-logback_2.13/2.9.0/play-logback_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-filters-helpers_2.13/2.9.0/play-filters-helpers_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-guice_2.13/2.9.0/play-guice_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ehcache_2.13/2.9.0/play-ehcache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws_2.13/2.9.0/play-ahc-ws_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-evolutions_2.13/2.9.0/play-jdbc-evolutions_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-caffeine-cache_2.13/2.9.0/play-caffeine-cache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/h2database/h2/1.4.197/h2-1.4.197.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm_2.13/2.7.0/anorm_2.13-2.7.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick_2.13/5.1.0/play-slick_2.13-5.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick-evolutions_2.13/5.1.0/play-slick-evolutions_2.13-5.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-codegen_2.13/3.4.1/slick-codegen_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_2.13/10.2.10/akka-http-core_2.13-10.2.10.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jsoup/jsoup/1.11.3/jsoup-1.11.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json-joda_2.13/2.10.0/play-json-joda_2.13-2.10.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play_2.13/9.4.4/jwt-play_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-core_2.13/9.4.4/jwt-core_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/auth0/jwks-rsa/0.6.1/jwks-rsa-0.6.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/java/dev/jna/jna/5.13.0/jna-5.13.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play_2.13/2.9.0/play_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.13/1.2.0/scala-xml_2.13-1.2.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-streams_2.13/2.9.0/play-streams_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.4.11/logback-classic-1.4.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/guice/6.0.0/guice-6.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/extensions/guice-assistedinject/6.0.0/guice-assistedinject-6.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-cache_2.13/2.9.0/play-cache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/sf/ehcache/ehcache/2.10.9.2/ehcache-2.10.9.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/ehcache/jcache/1.0.1/jcache-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/cache/cache-api/1.1.1/cache-api-1.1.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws_2.13/2.9.0/play-ws_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws-standalone_2.13/2.2.4/play-ahc-ws-standalone_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-asynchttpclient/2.2.4/shaded-asynchttpclient-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-oauth/2.2.4/shaded-oauth-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-api_2.13/2.9.0/play-jdbc-api_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/caffeine/3.1.8/caffeine-3.1.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/jcache/3.1.8/jcache-3.1.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm-tokenizer_2.13/2.7.0/anorm-tokenizer_2.13-2.7.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/joda-time/joda-time/2.12.5/joda-time-2.12.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/joda/joda-convert/2.2.2/joda-convert-2.2.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick_2.13/3.4.1/slick_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-hikaricp_2.13/3.4.1/slick-hikaricp_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_2.13/10.2.10/akka-parsing_2.13-10.2.10.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json_2.13/2.10.2/play-json_2.13-2.10.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play-json_2.13/9.4.4/jwt-play-json_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-codec/commons-codec/1.11/commons-codec-1.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-io/commons-io/2.6/commons-io-2.6.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.14.3/jackson-databind-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-build-link/2.9.0/play-build-link-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-configuration_2.13/2.9.0/play-configuration_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/2.0.9/jul-to-slf4j-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/2.0.9/jcl-over-slf4j-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor_2.13/2.6.21/akka-actor_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor-typed_2.13/2.6.21/akka-actor-typed_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-slf4j_2.13/2.6.21/akka-slf4j_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-serialization-jackson_2.13/2.6.21/akka-serialization-jackson_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.14.3/jackson-core-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.14.3/jackson-annotations-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.14.3/jackson-datatype-jdk8-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.14.3/jackson-datatype-jsr310-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/dataformat/jackson-dataformat-cbor/2.14.3/jackson-dataformat-cbor-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-parameter-names/2.14.3/jackson-module-parameter-names-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-scala_2.13/2.14.3/jackson-module-scala_2.13-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-api/0.11.5/jjwt-api-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-impl/0.11.5/jjwt-impl-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-jackson/0.11.5/jjwt-jackson-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/ssl-config-core_2.13/0.6.1/ssl-config-core_2.13-0.6.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-stream_2.13/2.6.21/akka-stream_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.4.11/logback-core-1.4.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/inject/jakarta.inject-api/2.0.1/jakarta.inject-api-2.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.21.1/error_prone_annotations-2.21.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone_2.13/2.2.4/play-ws-standalone_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-xml_2.13/2.2.4/play-ws-standalone-xml_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-json_2.13/2.2.4/play-ws-standalone-json_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/cachecontrol_2.13/2.3.1/cachecontrol_2.13-2.3.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.service.component.annotations/1.5.1/org.osgi.service.component.annotations-1.5.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-collection-compat_2.13/2.8.1/scala-collection-compat_2.13-2.8.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/4.0.3/HikariCP-4.0.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-functional_2.13/2.10.2/play-functional_2.13-2.10.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-json-common_2.13/9.4.4/jwt-json-common_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/2.8/j2objc-annotations-2.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-exceptions/2.9.0/play-exceptions-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-java8-compat_2.13/1.0.0/scala-java8-compat_2.13-1.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/lz4/lz4-java/1.8.0/lz4-java-1.8.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-protobuf-v3_2.13/2.6.21/akka-protobuf-v3_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/osgi.annotation/8.1.0/osgi.annotation-8.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.namespace.extender/1.0.1/org.osgi.namespace.extender-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.function/1.0.0/org.osgi.util.function-1.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.promise/1.0.0/org.osgi.util.promise-1.0.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: ApplyToImplicitArgs(method toJsFieldJsValueWrapper)
       tree position: line 259 of file://<WORKSPACE>/app/services/PaymentService.scala
            tree tpe: play.api.libs.json.Json.JsValueWrapper
              symbol: implicit method toJsFieldJsValueWrapper in object Json
   symbol definition: implicit def toJsFieldJsValueWrapper[T](field: T)(implicit w: play.api.libs.json.Writes[T]): play.api.libs.json.Json.JsValueWrapper (a MethodSymbol)
      symbol package: play.api.libs.json
       symbol owners: method toJsFieldJsValueWrapper -> object Json
           call site: <none> in <none>

== Source file context for tree position ==

   256             _CURSOR_stripePaymentIntentId = paymentIntentId,
   257             status = PaymentIntentStatus.Succeeded
   258           )
   259         } yield Json.obj("status" -> "processed", "event" -> eventType)
   260 
   261       case "charge.succeeded" =>
   262         val chargeId = (eventData \ "charge_id").as[String]

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>/.bloop/wellplanner/bloop-bsp-clients-classes/classes-Metals-xeotakETSrOQAORHVekLXw== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.0/semanticdb-javac-0.11.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/twirl-api_2.13/1.6.2/twirl-api_2.13-1.6.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-server_2.13/2.9.0/play-server_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-akka-http-server_2.13/2.9.0/play-akka-http-server_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-logback_2.13/2.9.0/play-logback_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-filters-helpers_2.13/2.9.0/play-filters-helpers_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-guice_2.13/2.9.0/play-guice_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ehcache_2.13/2.9.0/play-ehcache_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws_2.13/2.9.0/play-ahc-ws_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-evolutions_2.13/2.9.0/play-jdbc-evolutions_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-caffeine-cache_2.13/2.9.0/play-caffeine-cache_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/h2database/h2/1.4.197/h2-1.4.197.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm_2.13/2.7.0/anorm_2.13-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick_2.13/5.1.0/play-slick_2.13-5.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick-evolutions_2.13/5.1.0/play-slick-evolutions_2.13-5.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-codegen_2.13/3.4.1/slick-codegen_2.13-3.4.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_2.13/10.2.10/akka-http-core_2.13-10.2.10.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jsoup/jsoup/1.11.3/jsoup-1.11.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json-joda_2.13/2.10.0/play-json-joda_2.13-2.10.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play_2.13/9.4.4/jwt-play_2.13-9.4.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-core_2.13/9.4.4/jwt-core_2.13-9.4.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/auth0/jwks-rsa/0.6.1/jwks-rsa-0.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/java/dev/jna/jna/5.13.0/jna-5.13.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play_2.13/2.9.0/play_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.13/1.2.0/scala-xml_2.13-1.2.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-streams_2.13/2.9.0/play-streams_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.4.11/logback-classic-1.4.11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/guice/6.0.0/guice-6.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/extensions/guice-assistedinject/6.0.0/guice-assistedinject-6.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-cache_2.13/2.9.0/play-cache_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/sf/ehcache/ehcache/2.10.9.2/ehcache-2.10.9.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/ehcache/jcache/1.0.1/jcache-1.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/cache/cache-api/1.1.1/cache-api-1.1.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws_2.13/2.9.0/play-ws_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws-standalone_2.13/2.2.4/play-ahc-ws-standalone_2.13-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-asynchttpclient/2.2.4/shaded-asynchttpclient-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-oauth/2.2.4/shaded-oauth-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-api_2.13/2.9.0/play-jdbc-api_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/caffeine/3.1.8/caffeine-3.1.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/jcache/3.1.8/jcache-3.1.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm-tokenizer_2.13/2.7.0/anorm-tokenizer_2.13-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/joda-time/joda-time/2.12.5/joda-time-2.12.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/joda/joda-convert/2.2.2/joda-convert-2.2.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick_2.13/3.4.1/slick_2.13-3.4.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-hikaricp_2.13/3.4.1/slick-hikaricp_2.13-3.4.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_2.13/10.2.10/akka-parsing_2.13-10.2.10.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json_2.13/2.10.2/play-json_2.13-2.10.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play-json_2.13/9.4.4/jwt-play-json_2.13-9.4.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-codec/commons-codec/1.11/commons-codec-1.11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-io/commons-io/2.6/commons-io-2.6.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.14.3/jackson-databind-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-build-link/2.9.0/play-build-link-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-configuration_2.13/2.9.0/play-configuration_2.13-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/2.0.9/jul-to-slf4j-2.0.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/2.0.9/jcl-over-slf4j-2.0.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor_2.13/2.6.21/akka-actor_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor-typed_2.13/2.6.21/akka-actor-typed_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-slf4j_2.13/2.6.21/akka-slf4j_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-serialization-jackson_2.13/2.6.21/akka-serialization-jackson_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.14.3/jackson-core-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.14.3/jackson-annotations-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.14.3/jackson-datatype-jdk8-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.14.3/jackson-datatype-jsr310-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/dataformat/jackson-dataformat-cbor/2.14.3/jackson-dataformat-cbor-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-parameter-names/2.14.3/jackson-module-parameter-names-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-scala_2.13/2.14.3/jackson-module-scala_2.13-2.14.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-api/0.11.5/jjwt-api-0.11.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-impl/0.11.5/jjwt-impl-0.11.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-jackson/0.11.5/jjwt-jackson-0.11.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/ssl-config-core_2.13/0.6.1/ssl-config-core_2.13-0.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-stream_2.13/2.6.21/akka-stream_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.4.11/logback-core-1.4.11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/inject/jakarta.inject-api/2.0.1/jakarta.inject-api-2.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/aopalliance/aopalliance/1.0/aopalliance-1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.21.1/error_prone_annotations-2.21.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone_2.13/2.2.4/play-ws-standalone_2.13-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-xml_2.13/2.2.4/play-ws-standalone-xml_2.13-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-json_2.13/2.2.4/play-ws-standalone-json_2.13-2.2.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/cachecontrol_2.13/2.3.1/cachecontrol_2.13-2.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.service.component.annotations/1.5.1/org.osgi.service.component.annotations-1.5.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-collection-compat_2.13/2.8.1/scala-collection-compat_2.13-2.8.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/4.0.3/HikariCP-4.0.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-functional_2.13/2.10.2/play-functional_2.13-2.10.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-json-common_2.13/9.4.4/jwt-json-common_2.13-9.4.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/2.8/j2objc-annotations-2.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-exceptions/2.9.0/play-exceptions-2.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-java8-compat_2.13/1.0.0/scala-java8-compat_2.13-1.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/lz4/lz4-java/1.8.0/lz4-java-1.8.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-protobuf-v3_2.13/2.6.21/akka-protobuf-v3_2.13-2.6.21.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/osgi.annotation/8.1.0/osgi.annotation-8.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.namespace.extender/1.0.1/org.osgi.namespace.extender-1.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.function/1.0.0/org.osgi.util.function-1.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.promise/1.0.0/org.osgi.util.promise-1.0.0.jar [exists ]
Options:
-deprecation -unchecked -encoding utf8 -Yrangepos -Xplugin-require:semanticdb


action parameters:
offset: 9329
uri: file://<WORKSPACE>/app/services/PaymentService.scala
text:
```scala
package services

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import databases.PaymentAPI
import datamodels._
import play.api.libs.json._
import java.time.LocalDateTime
import java.util.UUID

@Singleton
class PaymentService @Inject()(
  paymentAPI: PaymentAPI,
  stripeService: StripeService
)(implicit ec: ExecutionContext) {

  case class CreatePaymentIntentRequest(
    businessId: Int,
    amount: Long,
    currency: String = "USD",
    description: Option[String] = None,
    customerEmail: Option[String] = None,
    invoiceId: Option[Int] = None,
    formResponseId: Option[Int] = None,
    metadata: Map[String, String] = Map.empty
  )

  case class PaymentIntentResponse(
    id: String,
    clientSecret: String,
    amount: Long,
    currency: String,
    status: String,
    description: Option[String] = None
  )

  case class ProcessPaymentRequest(
    paymentIntentId: String,
    paymentMethodId: Option[String] = None
  )

  case class PaymentHistoryResponse(
    transactions: List[Transaction],
    totalCount: Int,
    totalAmount: Long
  )

  // JSON formatters
  implicit val createPaymentIntentRequestFormat: OFormat[CreatePaymentIntentRequest] = Json.format[CreatePaymentIntentRequest]
  implicit val paymentIntentResponseFormat: OFormat[PaymentIntentResponse] = Json.format[PaymentIntentResponse]
  implicit val processPaymentRequestFormat: OFormat[ProcessPaymentRequest] = Json.format[ProcessPaymentRequest]
  implicit val paymentHistoryResponseFormat: OFormat[PaymentHistoryResponse] = Json.format[PaymentHistoryResponse]

  def createPaymentIntent(request: CreatePaymentIntentRequest): Future[PaymentIntentResponse] = {
    for {
      // Create Stripe payment intent
      stripeResult <- stripeService.createPaymentIntent(
        amount = request.amount,
        currency = request.currency,
        description = request.description,
        customerEmail = request.customerEmail,
        metadata = request.metadata + ("business_id" -> request.businessId.toString)
      )

      // Save payment intent to database
      paymentIntent = PaymentIntent(
        id = 0, // Will be auto-generated
        businessId = request.businessId,
        stripePaymentIntentId = stripeResult.id,
        amount = request.amount,
        currency = request.currency,
        status = PaymentIntentStatus.withName(stripeResult.status),
        description = request.description,
        customerEmail = request.customerEmail,
        invoiceId = request.invoiceId,
        formResponseId = request.formResponseId,
        metadata = if (request.metadata.nonEmpty) Some(Json.toJson(request.metadata)) else None,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      )

      savedPaymentIntent <- paymentAPI.createPaymentIntent(paymentIntent)

    } yield PaymentIntentResponse(
      id = stripeResult.id,
      clientSecret = stripeResult.clientSecret,
      amount = stripeResult.amount,
      currency = stripeResult.currency,
      status = stripeResult.status,
      description = stripeResult.description
    )
  }

  def getPaymentIntent(businessId: Int, paymentIntentId: String): Future[Option[PaymentIntentResponse]] = {
    for {
      paymentIntentOpt <- paymentAPI.getPaymentIntentByStripeId(businessId, paymentIntentId)
      result <- paymentIntentOpt match {
        case Some(_) =>
          stripeService.retrievePaymentIntent(paymentIntentId).map { stripeResult =>
            Some(PaymentIntentResponse(
              id = stripeResult.id,
              clientSecret = stripeResult.clientSecret,
              amount = stripeResult.amount,
              currency = stripeResult.currency,
              status = stripeResult.status,
              description = stripeResult.description
            ))
          }
        case None => Future.successful(None)
      }
    } yield result
  }

  def processPayment(businessId: Int, request: ProcessPaymentRequest): Future[PaymentIntentResponse] = {
    for {
      // Confirm payment intent with Stripe
      stripeResult <- stripeService.confirmPaymentIntent(
        paymentIntentId = request.paymentIntentId,
        paymentMethodId = request.paymentMethodId
      )

      // Update payment intent status in database
      _ <- paymentAPI.updatePaymentIntentStatus(
        businessId = businessId,
        stripePaymentIntentId = request.paymentIntentId,
        status = PaymentIntentStatus.withName(stripeResult.status)
      )

      // If payment succeeded, create transaction record
      _ <- if (stripeResult.status == "succeeded") {
        val transaction = Transaction(
          id = 0, // Will be auto-generated
          businessId = businessId,
          stripeChargeId = "", // Will be updated via webhook
          paymentIntentId = Some(request.paymentIntentId),
          amount = stripeResult.amount,
          currency = stripeResult.currency,
          status = TransactionStatus.Completed,
          transactionType = TransactionType.Payment,
          description = stripeResult.description,
          customerEmail = stripeResult.customerEmail,
          processingFee = None, // Will be updated via webhook
          netAmount = None, // Will be updated via webhook
          metadata = None,
          createdAt = LocalDateTime.now(),
          updatedAt = LocalDateTime.now()
        )
        paymentAPI.createTransaction(transaction)
      } else {
        Future.successful(0)
      }

    } yield PaymentIntentResponse(
      id = stripeResult.id,
      clientSecret = stripeResult.clientSecret,
      amount = stripeResult.amount,
      currency = stripeResult.currency,
      status = stripeResult.status,
      description = stripeResult.description
    )
  }

  def cancelPayment(businessId: Int, paymentIntentId: String): Future[PaymentIntentResponse] = {
    for {
      // Cancel payment intent with Stripe
      stripeResult <- stripeService.cancelPaymentIntent(paymentIntentId)

      // Update payment intent status in database
      _ <- paymentAPI.updatePaymentIntentStatus(
        businessId = businessId,
        stripePaymentIntentId = paymentIntentId,
        status = PaymentIntentStatus.withName(stripeResult.status)
      )

    } yield PaymentIntentResponse(
      id = stripeResult.id,
      clientSecret = stripeResult.clientSecret,
      amount = stripeResult.amount,
      currency = stripeResult.currency,
      status = stripeResult.status,
      description = stripeResult.description
    )
  }

  def getPaymentHistory(businessId: Int, limit: Int = 50, offset: Int = 0): Future[PaymentHistoryResponse] = {
    for {
      transactions <- paymentAPI.getTransactionsByBusiness(businessId, limit, offset)
      totalCount <- paymentAPI.getTransactionCountByBusiness(businessId)
      totalAmount <- paymentAPI.getTotalAmountByBusiness(businessId)
    } yield PaymentHistoryResponse(
      transactions = transactions,
      totalCount = totalCount,
      totalAmount = totalAmount
    )
  }

  def savePaymentMethod(businessId: Int, customerId: String, paymentMethodId: String, isDefault: Boolean = false): Future[PaymentMethod] = {
    val paymentMethod = PaymentMethod(
      id = 0, // Will be auto-generated
      businessId = businessId,
      stripeCustomerId = customerId,
      stripePaymentMethodId = paymentMethodId,
      methodType = PaymentMethodType.Card, // Default to card
      isDefault = isDefault,
      createdAt = LocalDateTime.now(),
      updatedAt = LocalDateTime.now()
    )

    for {
      // Attach payment method to customer in Stripe
      _ <- stripeService.attachPaymentMethodToCustomer(paymentMethodId, customerId)
      
      // Save payment method to database
      savedPaymentMethod <- paymentAPI.createPaymentMethod(paymentMethod)

      // If this is the default method, update others to not be default
      _ <- if (isDefault) {
        paymentAPI.updateDefaultPaymentMethod(businessId, customerId, savedPaymentMethod.id)
      } else {
        Future.successful(())
      }

    } yield savedPaymentMethod
  }

  def getPaymentMethods(businessId: Int, customerId: String): Future[List[PaymentMethod]] = {
    paymentAPI.getPaymentMethodsByCustomer(businessId, customerId)
  }

  def removePaymentMethod(businessId: Int, paymentMethodId: Int): Future[Boolean] = {
    for {
      paymentMethodOpt <- paymentAPI.getPaymentMethod(businessId, paymentMethodId)
      result <- paymentMethodOpt match {
        case Some(paymentMethod) =>
          for {
            // Detach from Stripe
            _ <- stripeService.detachPaymentMethod(paymentMethod.stripePaymentMethodId)
            // Delete from database
            deleted <- paymentAPI.deletePaymentMethod(businessId, paymentMethodId)
          } yield deleted
        case None => Future.successful(false)
      }
    } yield result
  }

  def handleWebhookEvent(eventType: String, eventData: JsValue): Future[JsValue] = {
    eventType match {
      case "payment_intent.succeeded" =>
        val paymentIntentId = (eventData \ "payment_intent_id").as[String]
        val amount = (eventData \ "amount").as[Long]
        val currency = (eventData \ "currency").as[String]

        // Update payment intent status
        for {
          _ <- paymentAPI.updatePaymentIntentStatusByStripeId(
            @@stripePaymentIntentId = paymentIntentId,
            status = PaymentIntentStatus.Succeeded
          )
        } yield Json.obj("status" -> "processed", "event" -> eventType)

      case "charge.succeeded" =>
        val chargeId = (eventData \ "charge_id").as[String]
        val paymentIntentId = (eventData \ "payment_intent_id").as[String]
        val amount = (eventData \ "amount").as[Long]
        val currency = (eventData \ "currency").as[String]

        // Get charge details from Stripe to get fees
        for {
          chargeDetails <- stripeService.retrieveCharge(chargeId)
          _ <- paymentAPI.updateTransactionWithChargeDetails(
            paymentIntentId = paymentIntentId,
            stripeChargeId = chargeId,
            processingFee = chargeDetails.fee,
            netAmount = chargeDetails.netAmount
          )
        } yield Json.obj("status" -> "processed", "event" -> eventType)

      case "payment_intent.payment_failed" =>
        val paymentIntentId = (eventData \ "payment_intent_id").as[String]

        for {
          _ <- paymentAPI.updatePaymentIntentStatusByStripeId(
            stripePaymentIntentId = paymentIntentId,
            status = PaymentIntentStatus.RequiresPaymentMethod
          )
        } yield Json.obj("status" -> "processed", "event" -> eventType)

      case _ =>
        Future.successful(Json.obj("status" -> "ignored", "event" -> eventType))
    }
  }

  def createRefund(businessId: Int, transactionId: Int, amount: Option[Long] = None, reason: Option[String] = None): Future[JsValue] = {
    for {
      transactionOpt <- paymentAPI.getTransaction(businessId, transactionId)
      result <- transactionOpt match {
        case Some(transaction) =>
          for {
            refundResult <- stripeService.createRefund(
              chargeId = transaction.stripeChargeId,
              amount = amount,
              reason = reason
            )
            
            // Create refund transaction record
            refundTransaction = Transaction(
              id = 0,
              businessId = businessId,
              stripeChargeId = (refundResult \ "id").as[String],
              paymentIntentId = transaction.paymentIntentId,
              amount = -(refundResult \ "amount").as[Long], // Negative for refund
              currency = transaction.currency,
              status = TransactionStatus.Completed,
              transactionType = TransactionType.Refund,
              description = reason.orElse(transaction.description),
              customerEmail = transaction.customerEmail,
              processingFee = None,
              netAmount = None,
              metadata = Some(Json.obj("original_transaction_id" -> transaction.id)),
              createdAt = LocalDateTime.now(),
              updatedAt = LocalDateTime.now()
            )
            
            _ <- paymentAPI.createTransaction(refundTransaction)
            
          } yield refundResult
          
        case None =>
          Future.successful(Json.obj("error" -> "Transaction not found"))
      }
    } yield result
  }
}

```



#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:70)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:66)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.reflect.internal.Types$ThisType.<init>(Types.scala:1394)
	scala.reflect.internal.Types$UniqueThisType.<init>(Types.scala:1414)
	scala.reflect.internal.Types$ThisType$.apply(Types.scala:1418)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:75)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:60)
	scala.collection.immutable.List.collect(List.scala:267)
	scala.meta.internal.pc.AutoImportsProvider.autoImports(AutoImportsProvider.scala:60)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$autoImports$1(ScalaPresentationCompiler.scala:384)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  ThisType(method getPaymentIntentByStripeId) for sym which is not a class
     while compiling: file://<WORKSPACE>/app/services/PaymentService.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -deprecation -encoding utf8 -unchecked -Wconf:cat=unchecked:w -Wconf:cat=deprecation:w -Wconf:cat=deprecation:ws -Wconf:cat=feature:ws -Wconf:cat=optimizer:ws -classpath <WORKSPACE>/.bloop/wellplanner/bloop-bsp-clients-classes/classes-Metals-xeotakETSrOQAORHVekLXw==:<HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.0/semanticdb-javac-0.11.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/twirl-api_2.13/1.6.2/twirl-api_2.13-1.6.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-server_2.13/2.9.0/play-server_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-akka-http-server_2.13/2.9.0/play-akka-http-server_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-logback_2.13/2.9.0/play-logback_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-filters-helpers_2.13/2.9.0/play-filters-helpers_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-guice_2.13/2.9.0/play-guice_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ehcache_2.13/2.9.0/play-ehcache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws_2.13/2.9.0/play-ahc-ws_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-evolutions_2.13/2.9.0/play-jdbc-evolutions_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-caffeine-cache_2.13/2.9.0/play-caffeine-cache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/h2database/h2/1.4.197/h2-1.4.197.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm_2.13/2.7.0/anorm_2.13-2.7.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick_2.13/5.1.0/play-slick_2.13-5.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-slick-evolutions_2.13/5.1.0/play-slick-evolutions_2.13-5.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-codegen_2.13/3.4.1/slick-codegen_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_2.13/10.2.10/akka-http-core_2.13-10.2.10.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jsoup/jsoup/1.11.3/jsoup-1.11.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json-joda_2.13/2.10.0/play-json-joda_2.13-2.10.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play_2.13/9.4.4/jwt-play_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-core_2.13/9.4.4/jwt-core_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/auth0/jwks-rsa/0.6.1/jwks-rsa-0.6.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/java/dev/jna/jna/5.13.0/jna-5.13.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play_2.13/2.9.0/play_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.13/1.2.0/scala-xml_2.13-1.2.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-streams_2.13/2.9.0/play-streams_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.4.11/logback-classic-1.4.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/guice/6.0.0/guice-6.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/inject/extensions/guice-assistedinject/6.0.0/guice-assistedinject-6.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-cache_2.13/2.9.0/play-cache_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/sf/ehcache/ehcache/2.10.9.2/ehcache-2.10.9.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/ehcache/jcache/1.0.1/jcache-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/cache/cache-api/1.1.1/cache-api-1.1.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws_2.13/2.9.0/play-ws_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ahc-ws-standalone_2.13/2.2.4/play-ahc-ws-standalone_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-asynchttpclient/2.2.4/shaded-asynchttpclient-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/shaded-oauth/2.2.4/shaded-oauth-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-jdbc-api_2.13/2.9.0/play-jdbc-api_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/caffeine/3.1.8/caffeine-3.1.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/ben-manes/caffeine/jcache/3.1.8/jcache-3.1.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/playframework/anorm/anorm-tokenizer_2.13/2.7.0/anorm-tokenizer_2.13-2.7.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/joda-time/joda-time/2.12.5/joda-time-2.12.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/joda/joda-convert/2.2.2/joda-convert-2.2.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick_2.13/3.4.1/slick_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/slick/slick-hikaricp_2.13/3.4.1/slick-hikaricp_2.13-3.4.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_2.13/10.2.10/akka-parsing_2.13-10.2.10.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json_2.13/2.10.2/play-json_2.13-2.10.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-play-json_2.13/9.4.4/jwt-play-json_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-codec/commons-codec/1.11/commons-codec-1.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-io/commons-io/2.6/commons-io-2.6.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.14.3/jackson-databind-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-build-link/2.9.0/play-build-link-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-configuration_2.13/2.9.0/play-configuration_2.13-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/2.0.9/jul-to-slf4j-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/2.0.9/jcl-over-slf4j-2.0.9.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor_2.13/2.6.21/akka-actor_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor-typed_2.13/2.6.21/akka-actor-typed_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-slf4j_2.13/2.6.21/akka-slf4j_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-serialization-jackson_2.13/2.6.21/akka-serialization-jackson_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.14.3/jackson-core-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.14.3/jackson-annotations-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.14.3/jackson-datatype-jdk8-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.14.3/jackson-datatype-jsr310-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/dataformat/jackson-dataformat-cbor/2.14.3/jackson-dataformat-cbor-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-parameter-names/2.14.3/jackson-module-parameter-names-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-scala_2.13/2.14.3/jackson-module-scala_2.13-2.14.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-api/0.11.5/jjwt-api-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-impl/0.11.5/jjwt-impl-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/jsonwebtoken/jjwt-jackson/0.11.5/jjwt-jackson-0.11.5.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/ssl-config-core_2.13/0.6.1/ssl-config-core_2.13-0.6.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-stream_2.13/2.6.21/akka-stream_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.4.11/logback-core-1.4.11.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/inject/jakarta.inject-api/2.0.1/jakarta.inject-api-2.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.21.1/error_prone_annotations-2.21.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone_2.13/2.2.4/play-ws-standalone_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-xml_2.13/2.2.4/play-ws-standalone-xml_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-ws-standalone-json_2.13/2.2.4/play-ws-standalone-json_2.13-2.2.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/cachecontrol_2.13/2.3.1/cachecontrol_2.13-2.3.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.service.component.annotations/1.5.1/org.osgi.service.component.annotations-1.5.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-collection-compat_2.13/2.8.1/scala-collection-compat_2.13-2.8.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/4.0.3/HikariCP-4.0.3.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-functional_2.13/2.10.2/play-functional_2.13-2.10.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/jwt-scala/jwt-json-common_2.13/9.4.4/jwt-json-common_2.13-9.4.4.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/2.8/j2objc-annotations-2.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-exceptions/2.9.0/play-exceptions-2.9.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-java8-compat_2.13/1.0.0/scala-java8-compat_2.13-1.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/lz4/lz4-java/1.8.0/lz4-java-1.8.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-protobuf-v3_2.13/2.6.21/akka-protobuf-v3_2.13-2.6.21.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/osgi.annotation/8.1.0/osgi.annotation-8.1.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.namespace.extender/1.0.1/org.osgi.namespace.extender-1.0.1.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.function/1.0.0/org.osgi.util.function-1.0.0.jar:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/osgi/org.osgi.util.promise/1.0.0/org.osgi.util.promise-1.0.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: ApplyToImplicitArgs(method toJsFieldJsValueWrapper)
       tree position: line 259 of file://<WORKSPACE>/app/services/PaymentService.scala
            tree tpe: play.api.libs.json.Json.JsValueWrapper
              symbol: implicit method toJsFieldJsValueWrapper in object Json
   symbol definition: implicit def toJsFieldJsValueWrapper[T](field: T)(implicit w: play.api.libs.json.Writes[T]): play.api.libs.json.Json.JsValueWrapper (a MethodSymbol)
      symbol package: play.api.libs.json
       symbol owners: method toJsFieldJsValueWrapper -> object Json
           call site: <none> in <none>

== Source file context for tree position ==

   256             _CURSOR_stripePaymentIntentId = paymentIntentId,
   257             status = PaymentIntentStatus.Succeeded
   258           )
   259         } yield Json.obj("status" -> "processed", "event" -> eventType)
   260 
   261       case "charge.succeeded" =>
   262         val chargeId = (eventData \ "charge_id").as[String]