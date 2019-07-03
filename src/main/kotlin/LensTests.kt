//import arrow.optics.Lens
//import arrow.optics.Optional
//import arrow.optics.optics
//import java.time.Instant
//
///**
// * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
// * @since 7/3/19
// */
//
//fun main(args: Array<String>) {
//    val person = Person(
//        "Julio",
//        Instant.now(),
//        Child(
//            "Calleguinhas",
//            "Molang"
//        )
//    )
//
//    //val optional: Optional<Person, Child> = Person.child
//
//}
//
//@optics
//data class Person(
//    val name: String,
//    val birthday: Instant,
//    val child: Child
//) {
//    companion object
//}
//
//@optics
//data class Child(
//    val name: String,
//    val favoriteCharacter: String
//) {
//    companion object
//}