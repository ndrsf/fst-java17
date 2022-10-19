# What is this?

A small example implementation how to use 
[FST](https://github.com/RuedigerMoeller/fast-serialization) 
with strong encapsulation (since Java 17).

It shows which add-opens commands are required and how
to configure them so at least IntelliJ and Maven including
Surefire and Failsafe use them.

# Why?
FST uses JDK-internal code, which can only be executed
when the JDK opens to the FST library (which is unlikely
to happen), or when you explicitly allow the access
by passing parameters to the Java process (we use this
mechanism in this example).

# What to do?
* Add the currently not merged FST version 
`de.ruedigermoeller:fst:3.0.3-jdk17` (released on 
[Maven Central](https://mvnrepository.com/artifact/de.ruedigermoeller/fst/3.0.3-jdk17))
* Add this to your Maven `properties`:

  `<argLine>--add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens
  java.base/java.math=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens
  java.base/java.util.concurrent=ALL-UNNAMED --add-opens java.base/java.net=ALL-UNNAMED --add-opens
  java.base/java.text=ALL-UNNAMED --add-opens java.sql/java.sql=ALL-UNNAMED
  </argLine>`
  * Why this helps:
    * Maven Failsafe and Surefire plugins will be able to 
    execute the tests containing FST code
    * IntelliJ will also pull this configuration to
    execute tests
* Add the same string to your VM arguments when starting
  the Java process. Yes, this is annoying and yes
  this is a deliberate decision by the JDK developers.
  Here is an example how to run this example Jar:
 
  `java.exe --add-opens java.base/java.time=ALL-UNNAMED 
  --add-opens java.base/java.lang=ALL-UNNAMED 
  --add-opens java.base/java.math=ALL-UNNAMED
  --add-opens java.base/java.util=ALL-UNNAMED 
  --add-opens java.base/java.util.concurrent=ALL-UNNAMED
  --add-opens java.base/java.net=ALL-UNNAMED 
  --add-opens java.base/java.text=ALL-UNNAMED 
  --add-opens java.sql/java.sql=ALL-UNNAMED 
  -jar target\fst-java17-1.0.0-SNAPSHOT-jar-with-dependencies.jar`
* Sadly, IntelliJ doesn't pull these arguments when
  executing main methods, so you have to add these parameters
  manually in your launch configs (see IntelliJ-Run.xml
  as example launch config)

# How can I avoid the hassle?
* Either, FST will switch to a way supported by the JDK
(so not using [setAccessible](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Field.html#setAccessible(boolean))
and Unsafe)  
* Or you use some [dubious](https://github.com/Moderocky/Overlord)
and [brutal](https://burningwave.github.io/core/)
libraries to break the Java module system  
* Or you just never migrate to Java 17 (it's a bit
  easier to disable the check)
* Or you switch to a different serialization mechanism
* Or you know some other way and let me know :)