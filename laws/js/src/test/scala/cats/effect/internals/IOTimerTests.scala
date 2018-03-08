/*
 * Copyright (c) 2017-2018 The Typelevel Cats-effect Project Developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cats.effect
package internals

import scala.util.Success

class IOTimerTests extends BaseTestsSuite {
  test("Timer[IO] default instance") {
    val ref1 = Timer[IO]
    ref1 shouldBe Timer[IO]
  }

  testAsync("Timer[IO] instance based on implicit ExecutionContext") { implicit ec =>
    val timer = Timer[IO]

    val f = timer.shift.map(_ => 1).unsafeToFuture()
    f.value shouldBe None

    ec.tick()
    f.value shouldBe Some(Success(1))
  }
}