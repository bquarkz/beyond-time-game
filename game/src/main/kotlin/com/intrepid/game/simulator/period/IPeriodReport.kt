package org.bquarkz.beyondtime.simulator.period

import org.bquarkz.beyondtime.simulator.IReport

interface IPeriodReport: IReport
{
    fun currentPeriodPercent(): Double
    fun numberOfCompletedPeriods(): Long
}