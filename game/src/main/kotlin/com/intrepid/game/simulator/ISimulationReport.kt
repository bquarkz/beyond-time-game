package org.bquarkz.beyondtime.simulator

import org.bquarkz.beyondtime.simulator.period.IPeriodReport

interface ISimulationReport: IReport
{
    fun periodControl(): IPeriodReport
}