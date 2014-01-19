name := "Uriza"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += javaJdbc

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.6"

libraryDependencies += javaEbean

libraryDependencies += "org.apache.commons" % "commons-email" % "1.3.2"

play.Project.playJavaSettings

