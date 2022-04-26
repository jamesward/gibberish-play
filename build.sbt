enablePlugins(PlayScala)
disablePlugins(PlayFilters)

name := "gibberish-play"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  ws,
  guice,
)
