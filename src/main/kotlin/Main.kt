package cinema

fun main() {
    println("Enter the number of rows: ")
    val row = readln().toInt()
    println("Enter the number of seats in each row: ")
    val seat = readln().toInt()
    val menuCinemaHall = "1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit"

    println(menuCinemaHall)
    var guestChoice = readln().toInt()
    var seats = MutableList(row) { MutableList(seat) { 'S' } }
    var tickets = 0
    var currentIncome = 0

    while (guestChoice != 0) {
        when (guestChoice) {
            1 -> {cinemaHall(seats)
                println(menuCinemaHall)
                guestChoice = readln().toInt()
            }
            2 -> {
                tickets += 1
                println("Enter a row number: ")
                val rowChoice = readln().toInt()
                println("Enter a seat number in that row: ")
                val seatChoice = readln().toInt()
                if (rowChoice > row || seatChoice > seat){
                    println("Wrong input!")
                }
                else if (seats[rowChoice - 1][seatChoice - 1] == 'B') {
                    println("That ticket has already been purchased!")
                }
                else {
                    when {
                        row * seat < 60 -> {
                            println("Ticket price: $${priceTicketsLess60()}")
                            currentIncome += priceTicketsLess60()
                        }
                        row * seat > 60 -> {
                            println("Ticket price: $${priceTicketsMore60(row, rowChoice)}")
                            currentIncome += priceTicketsMore60(row, rowChoice)
                        }
                    }
                    seats[rowChoice - 1][seatChoice - 1] = 'B'
                    println(menuCinemaHall)
                    guestChoice = readln().toInt()
                }
            }
            3 -> {
                println("Number of purchased tickets: $tickets\n" +
                        "Percentage: ${persentTage(row, seat, tickets)}%\n" +
                        "Current income: $$currentIncome\n" +
                        "Total income: $${priceTicketsTotal(row, seat)}")
                println(menuCinemaHall)
                guestChoice = readln().toInt()
            }
        }

    }
}

fun priceTicketsTotal(rows: Int, seats: Int): Int {
    val numberOfSeats = rows * seats
    var price = 0
    when {
        numberOfSeats < 60 -> price = rows * seats * 10
        numberOfSeats > 60 -> price = (rows / 2 * seats * 10) + ((rows - rows / 2) * seats * 8)
    }
    return price
}

fun persentTage(rows: Int, seats: Int, ticktes: Int): String {
    val pesents = ((ticktes.toDouble() * 100.0) / (rows.toDouble() * seats.toDouble()))
    val formatPercents = "%.2f".format(pesents)
    return formatPercents
}

fun priceTicketsMore60(rows: Int, rowChoice: Int): Int {
    var price = 0
    when (rowChoice) {
        in 1.. rows / 2 -> price = 10
        in rows / 2 + 1..rows -> price = 8
    }
    return price
}

fun priceTicketsLess60() = 10

fun cinemaHall(hall: MutableList<MutableList<Char>>) {
    println("Cinema:")
    print("  ")
    for (i in 1..hall[0].size) {
        print("$i ")
    }
    println()
    for (i in hall.indices) {
        print("${i + 1} ")
        for (j in hall[i].indices) {
            print("${hall[i][j]} ")
        }
        println()
    }
}