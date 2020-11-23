import cats.effect.{Blocker, ExitCode, IO, IOApp, Resource}
import fs2.{io, text, Stream}
import java.nio.file.Paths

object HelloWorld extends IOApp {

  val converter: Stream[IO, Unit] = Stream.resource(Blocker[IO]).flatMap { blocker =>
    def convertFahrenheitToCelsius(f: Double): Double = {
      println(s"converting $f")
      (f - 32.0) * (5.0 / 9.0)
    }

    io.file.readAll[IO](Paths.get("src/main/resources/fahrenheit.txt"), blocker, 4096)
      .through(text.utf8Decode)
      .through(text.lines)
      .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
      .map(line => convertFahrenheitToCelsius(line.toDouble).toString)
      .intersperse("\n")
      .through(text.utf8Encode)
      .through(io.file.writeAll(Paths.get("src/main/resources/celsius.txt"), blocker))
  }


  def run(args: List[String]): IO[ExitCode] = {
    println("Running")

    converter.compile.drain.as(ExitCode.Success)
  }

}