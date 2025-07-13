# maven-emergent-parent

### Initializing Maven Wrapper

```shell
mvn wrapper:wrapper -Dmaven=3.9.9
```

### Printing Project Version

```shell
./mvnw help:evaluate -q -DforceStdout -Dexpression=project.version
```

```shell
./mvnw help:evaluate -Dorg.slf4j.simpleLogger.log.me.qoomon.maven.gitversioning=debug -Dexpression=project.version 
```
