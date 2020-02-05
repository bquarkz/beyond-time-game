package org.bquarkz.beyondtime.simulator.period

import org.bquarkz.beyondtime.simulator.ISimulationControl

class PeriodSimulation(
        private var numberOfCompletedPeriods: Long,
        private var currentTime: Long
        ): ISimulationControl<IPeriodReport>
{
    private val TIME_TO_COMPLETE_A_PERIOD: Long = 10_000_000_000L // 10s
    private val PRECISION: Long = 50_000L // precisao de 50ms
    private var percentPeriod: Double = 0.0

    override fun step( deltaTime: Long )
    {
        currentTime += deltaTime  //linear diretamente
        if( ( TIME_TO_COMPLETE_A_PERIOD - currentTime ) < PRECISION )
        {
            numberOfCompletedPeriods++
            currentTime = 0L
        }
        percentPeriod = currentTime / TIME_TO_COMPLETE_A_PERIOD.toDouble()
    }

    override fun report(): IPeriodReport
    {
        return object: IPeriodReport
        {
            override fun currentPeriodPercent(): Double
            {
                return percentPeriod
            }

            override fun numberOfCompletedPeriods(): Long
            {
                return this@PeriodSimulation.numberOfCompletedPeriods
            }
        }
    }
}