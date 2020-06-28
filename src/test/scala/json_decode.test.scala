package json

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers

class JsonDecodeTest extends AnyFreeSpecLike with Matchers {
  "json decode" - {
    val expected = model.InlineQuery("123", "1+1")
    "jsonp" - {
      "stream" in {
        new JsonDecode().jsonpStream(new JsonDecodeStates.JsonpStreamState()) shouldBe expected
      }
      "pointer" in {
        new JsonDecode().jsonpPointer(new JsonDecodeStates.JsonpPointerState()) shouldBe expected
      }
    }
    "nashorn" in {
      new JsonDecode().nashorn(new JsonDecodeStates.NashornState()) shouldBe expected
    }
    "jsoniter" in {
      new JsonDecode().jsoniter(new JsonDecodeStates.JsoniterState()) shouldBe expected
    }
  }
}