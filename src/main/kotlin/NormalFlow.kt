import arrow.core.Either
import arrow.core.Option
import arrow.core.left
import arrow.core.right
import arrow.core.some
import domain.Charge
import domain.VirtualCard
import java.time.Instant
import java.time.LocalDate
import kotlin.math.exp

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 7/3/19
 */

fun main(args: Array<String>) {
    val customer = Customer(
        "Julio",
        Instant.now(),
        Book("Calleguinhas", "Molang").some()
    )

    val customer2 = Customer(
        "Caique",
        Instant.now(),
        Book("Um timmy", "Padritos magicos").some()
    )

    val card = VirtualCard(
        id = 563345,
        chargeList = listOf(Charge(10, 200.0, LocalDate.now())),
        cvc = "333",
        expireDate = LocalDate.now().plusDays(5)
    )

    val expiredCard = card.copy(expireDate = LocalDate.now().minusDays(1))

    val operations = listOf(customer to card, customer2 to expiredCard)
        .map { (customer, card) -> validateCustomerCard(customer, card) }
        .map { it.map { (customer, card) -> callExternalService(customer, card) } }
        .map {
            when (it) {
                is Either.Right -> it.b
                is Either.Left -> "Operation error: ${it.a}"
            }
        }
        .forEach(::println)

}

data class Customer(
    val name: String,
    val birthday: Instant,
    val book: Option<Book>
) {
    companion object
}

data class Book(
    val name: String,
    val character: String
) {
    companion object
}

fun callExternalService(customer: Customer, card: VirtualCard) =
    "Success http response!! $customer, $card"

fun validateCustomerCard(customer: Customer, card: VirtualCard) =
    when {
        card.expireDate < LocalDate.now() -> ExpiredCardError.left()
        card.chargeList.isEmpty() -> NoChargesError.left()
        else -> (customer to card).right()
    }

sealed class CardError
object ExpiredCardError : CardError()
object NoChargesError : CardError()