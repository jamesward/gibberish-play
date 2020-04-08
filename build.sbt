enablePlugins(PlayScala)
disablePlugins(PlayFilters)

name := "gibberish-play"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  ws,
  guice
)
