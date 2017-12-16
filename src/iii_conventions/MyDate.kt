package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val y = this.year.compareTo(other.year)
        if (y != 0) return y
        val m = this.month.compareTo(other.month)
        if (m != 0) return m
        return this.dayOfMonth.compareTo(other.dayOfMonth)
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

data class RepeatedTimeInterval(val interval: TimeInterval, val repeated: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate = addTimeIntervals(interval.interval, interval.repeated)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var current = start
        override fun next(): MyDate {
            val now = current
            current = current.nextDay()
            return now
        }

        override fun hasNext(): Boolean = current <= endInclusive
    }
}
