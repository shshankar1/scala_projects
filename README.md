# scala_projects
Requirement to run projects locally:
1. SBT : Install version 0.13.15 (Create environment system variable SBT_HOME: C:\Program Files (x86)\sbt\)
2. Scala: Install version 2.11.8 (set SCALA_HOME environment system variable, add it to path also)
3. Checkt if sbt setup went fine by running sbt command on prompt.
   Few example commands:
   a. sbt compile
   b. sbt clean
   c. sbt run
   d. sbt test
4. Check if scala setup went fine by running scala REPL using command prompt.

Important points to create sbt project:
1. Create project directory manually
2. Under the project directory run below commands:
   a. mkdir -p src/{main,test}/{java,resources,scala}
   b. mkdir lib
3. Under project directory create a file called build.sbt and add miminal entry as below:
   a. scalaVersion := "2.11.8"
   b. name := "Cognitive Scala Project"
4. Now run command "sbt compile"
5. Once project get compiled successfully, import the project in intellij.

Cygwin tool is good to have unix like environment on windows system.
