package de.jensklingenberg.test
fun test(given: () -> Unit, whenever: () -> Unit, then: () -> Unit) {
    given()
    whenever()
    then()
}
