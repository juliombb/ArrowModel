import arrow.core.andThen
import arrow.core.curry
import logic.concreteMerge

fun main(args : Array<String>) {
    val plus2 = { a: Int -> a + 2 }
    val dividedBy3 = { a: Int -> a / 3 }

    val plus2Div3 = plus2 andThen dividedBy3
    val div3plus2 = dividedBy3 andThen plus2

    println(plus2Div3(4))
    println(div3plus2(4))

    val curriedSum = ::sum.curry()
    val plus1 = curriedSum(1)
    println(plus1(10))
    println(curriedSum(10)(11))
    concreteMerge()
}

fun sum(x: Int, y: Int) = x + y