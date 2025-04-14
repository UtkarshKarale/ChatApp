name := "chat-application"
version := "1.0"
scalaVersion := "2.13.8"

val springBootVersion = "2.7.0"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-thymeleaf" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-websocket" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % springBootVersion,
  "com.h2database" % "h2" % "2.1.214",
  "org.scala-lang" % "scala-library" % "2.13.8",
  "org.springframework.boot" % "spring-boot-starter-test" % springBootVersion % Test
)

enablePlugins(JavaAppPackaging) 