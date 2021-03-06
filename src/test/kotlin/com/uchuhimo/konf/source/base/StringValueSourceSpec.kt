/*
 * Copyright 2017-2018 the original author or authors.
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

package com.uchuhimo.konf.source.base

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.sameInstance
import com.natpryce.hamkrest.throws
import com.uchuhimo.konf.source.NoSuchPathException
import com.uchuhimo.konf.source.Source
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object StringValueSourceSpec : Spek({
    given("a string value source") {
        on("get with empty path") {
            it("should return itself") {
                val source: Source = SingleStringValueSource("string")
                assertThat(source.getOrNull("")!!, sameInstance(source))
            }
        }
        on("get with non-empty path") {
            it("should throw NoSuchPathException") {
                assertThat({ SingleStringValueSource("string").getOrNull("a") },
                    throws<NoSuchPathException>())
            }
        }
        it("should contain empty path") {
            assertTrue { SingleStringValueSource("string").contains("") }
        }
        it("should not contain non-empty path") {
            assertFalse { SingleStringValueSource("string").contains("a") }
        }
    }
})
