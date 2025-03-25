package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = allDrivers.filter { driver ->
    driver !in trips.map { tripDriver -> tripDriver.driver }.toSet()
}.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> = allPassengers.filter { passenger ->
    trips.count { trip -> passenger in trip.passengers } >= minTrips
}.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = allPassengers.filter { passenger ->
    trips.count { trip -> trip.driver.equals(driver) && passenger in trip.passengers } > 1
}.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> = allPassengers.filter { passenger ->
    val pTrips = trips.filter { trip -> passenger in trip.passengers }
    pTrips.count { trip -> trip.discount != null } > pTrips.size / 2
}.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips
        .groupBy { it.duration / 10 * 10..it.duration / 10 * 10 + 9 }
        .maxByOrNull { (_, group) -> group.size }
        ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false
    val driversWithIncomesSorted = allDrivers.map { driver ->
        driver to trips.filter { trip -> trip.driver == driver }.sumOf { trip -> trip.cost }
    }.sortedWith(compareByDescending { it.second })

    val income80 = trips.sumOf { it.cost } * 0.8
    val incomeFrom20Drivers = driversWithIncomesSorted.take(allDrivers.size / 5).sumOf { it.second }
    return incomeFrom20Drivers >= income80
}

//fun TaxiPark.checkParetoPrinciple1(): Boolean {
//    if (trips.isEmpty()) return false
//
//    val totalIncome = trips.sumOf { it.cost }
//    val sortedDriversIncome: List<Double> = trips
//        .groupBy { it.driver }
//        .map { (_, tripsByDriver) -> tripsByDriver.sumOf { tripByDriver -> tripByDriver.cost } }
//        .sortedDescending()
//
//    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
//    val incomeByTopDrivers = sortedDriversIncome
//        .take(numberOfTopDrivers)
//        .sum()
//
//    return incomeByTopDrivers >= 0.8 * totalIncome
//}