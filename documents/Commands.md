# Commands

## Compile a nonmodular program

```sh
javac -cp <classes> -d <output> <package>
javac --class-path <classes> -d <output> <package>
```

## Compile a modular program

```sh
javac -p <modules> -d <output> <classes *.java> <module *.java>
javac --module-path <modules> -d <output> <classes *.java> <module *.java>
```

## Package into a jar

```sh
jar -cvf <output> -C <files> <resources>
jar --create --verbose --file <output> -C <files> <resources>
```

## Run a nonmodular program
```sh
java -cp <jar name> <package>
java --class-path <jar name> <package>
```

## Run a modular program

```sh
java -p <modules> -m <module>/<package>.<class>
java --module-path <modules> -m <module>/<package>.<class>
```

## Describe a particular module with java

```sh
java -p <modules> -d <module>
java --module-path <modules> --describe-module <module>
```

## Describe a particular module with java (supply a specific module)

```sh
java -p <modules> -d <module> -m <module>
java --module-path <modules> --describe-module <module> --module <module>
```

## Describe a particular module with jar

```sh
jar -f <jar name> -d
jar --file <jar name> --describe-module
```

## Produce a short form/summary report a path/jar (will not list dependencies, only modules)

```sh
jdeps -s --module-path <modules> <path/jar name>
jdeps -summary --module-path <path/jar name>
```

## Produce a long form report for a path/jar (will list dependencies and modules)

```sh
jdeps -p <modules> <path/jar name>
jdeps --module-path <modules> <path/jar name>
```

## Produce a long form report for a path/jar (supply a specific module)

```sh
jdeps --module-path <modules> -m <module> <path/jar name>
jdeps --module-path <modules> --module <module> <path/jar name>
```

## List unsupported APIs for a path/jar

```sh
jdeps -jdkinternals <path/jar name>
jdeps --jdk-internals <path/jar name>
```

## List all modules in a path/jar

```sh
java -p <modules> --list-modules
java --module-path <modules> --list-modules
```

## Show module resolution

```sh
java --show-module-resolution -p <modules> -m <module>/<package>.<class>
java --show-module-resolution --module-path <modules> --module <module>/<package>.<class>
```

## Modes jmod to know (native/special files)

```
create, describe, extract, hash, list (CDEHL)
```
