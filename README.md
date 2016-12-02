This works at the commandline build.

1. in the scala project use the maven-build with `package -DskipTests` to generate a jar fast

In Eclipse (every build):

1. build scalaproject with maven (generates jar)
2. build spoofax project with spoofax (cmd+alt+b), reloads language with new jar. (or right click the language project -> Spoofax Meta -> Load Language)


Currently the project uses a bleeding edge version of scalaterms, so you have to build it yourself. You have two options:

1. Add the scalaterms project to eclipse and use a relative path in `pixiedust.lang/editor/Main.esv` (breaks commandline build)
2. Build scalaterms independently and include the artifact in `pixiedust.scala/src/assembly/assembly.xml`. This will bundle it along with the scala standard library and the `pixiedust.scala` project.


Suggestion:
- bind `Run Maven Build` to shortkey, for example `Ctrl+Alt+B` (on mac)