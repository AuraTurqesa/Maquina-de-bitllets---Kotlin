import java.util.Scanner
import kotlin.math.pow
import java.math.RoundingMode
import java.text.DecimalFormat

fun main() {
    // Declaración de variables y objetos
    val lector = Scanner(System.`in`)
    var opcioCorrecta: Boolean
    var codiSecret = 0
    var opcio = 0
    var numZones: Int
    var preu = 0.00
    var preuTotal = mutableListOf(0.00, 0.00, 0.00) // Lista de valores de la compra
    var preuCompra = 0.00
    var numBitllets = 0
    var continuar = " "
    var resumTotal = mutableListOf(" ", " ", " ") // Lista de los tickets
    var resumZones = mutableListOf(0, 0, 0) // Lista de las zonas de los tickets
    var nomTicket = " "
    var deute = 0.00
    var pagament = 0.00
    val df = DecimalFormat("0.00")
    df.roundingMode = RoundingMode.HALF_UP // Establece el modo de redondeo

    // Mensaje inicial
    do {
        do {
            do {
                do {
                    println("------------------------------")
                    println("Quin desitja adquirir?") // Para seleccionar el tipo de billete
                    println("1 - Bitllet senzill")
                    println("2 - TCasual")
                    println("3 - TUsual")
                    println("4 - TFamiliar")
                    println("5 - TJove")
                    opcio = lector.nextInt()
                    if (opcio < 1 || opcio > 5) {
                        println("El número ha de ser entre 1 i 5")
                    }
                } while (opcio < 1 || opcio > 5) // El valor ha de ser entre 1 y 5
                println("Estàs segur/a de que voleu comprar aquest títol?") // Por si se han equivocado
                opcioCorrecta = lector.nextBoolean()
                if (!opcioCorrecta) {
                    println("Redirigint a la pàgina anterior")
                } else {
                    do {
                        println("Quina zona vol viatjar?")
                        numZones = lector.nextInt()
                        if (numZones < 1 || numZones > 6) {
                            println("El número ha de ser entre 1 i 6")
                        }
                    } while (numZones < 1 || numZones > 6)
                    when (opcio) {
                        1 -> {
                            preu = 2.40
                            nomTicket = "Bitllet Senzill"
                        }
                        2 -> {
                            preu = 11.35
                            nomTicket = "TCasual"
                        }
                        3 -> {
                            preu = 40.00
                            nomTicket = "TUsual"
                        }
                        4 -> {
                            preu = 10.00
                            nomTicket = "TFamiliar"
                        }
                        5 -> {
                            preu = 80.00
                            nomTicket = "TJove"
                        }
                    }
                    df.format(preu)
                    println("Ha escollit la opció: $nomTicket, zona $numZones")
                    preu *= (1.25.pow(numZones - 1))
                    println(preu)
                    preuTotal[numBitllets] = preu
                    resumTotal[numBitllets] = nomTicket
                    resumZones[numBitllets] = numZones
                }
            } while (!opcioCorrecta)
            if (numBitllets < 2) {
                println("Vols continuar?")
                continuar = lector.next()
                if (continuar == "n") {
                    break
                }
                else if (continuar == "s") {
                    println("Tornem a la pàgina d'inici")
                    ++numBitllets
                }
                else {
                    println("Torna a començar")
                }
            }
        } while (numBitllets < 3)

        // Cálculo y pago del total
        for (y in 0..2) {
            preuCompra += preuTotal[y]
        }
        println("Ha comprat ${numBitllets + 1} bitllet(s), ha de pagar ${df.format(preuCompra)}€")
        deute = preuCompra

        while (deute > 0) {
            println("Introdueixi monedes o bitllets vàlids d'EURO")
            pagament = lector.nextDouble()
            if (pagament in listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0)) {
                deute -= pagament
                println("Ha introduit ${df.format(pagament)}€, li resten ${df.format(deute)}€")
            } else {
                println("Ha introduit 0.00€, li manquen ${df.format(deute)}€.")
            }
        }
        if (deute < 0) {
            println("El canvi són: ${df.format(0 - deute)}€")
        }

        // Imprimir resumen
        for (x in 0..numBitllets) {
            println("${resumTotal[x]} zona ${resumZones[x]} - Preu: ${df.format(preuTotal[x])}€")
        }
        println("Codi secret: ")
        codiSecret = lector.nextInt()
    } while (codiSecret != 4321)
}
