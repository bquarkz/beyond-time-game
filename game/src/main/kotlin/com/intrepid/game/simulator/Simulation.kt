package org.bquarkz.beyondtime.simulator

import org.bquarkz.beyondtime.simulator.period.IPeriodReport
import org.bquarkz.beyondtime.simulator.period.PeriodSimulation

class Simulation: ISimulationControl< ISimulationReport >
{
    private val periodSimulation: PeriodSimulation
    private val simulations: List< ISimulationControl< out IReport > >

    init
    {
        periodSimulation = PeriodSimulation( 0, 0 )
        simulations = arrayListOf( periodSimulation )
    }

    override fun step( deltaTime: Long )
    {
        if( deltaTime <= 0 )
        {
            simulations.forEach { simulation -> simulation.step( 1 ) }
        }
        else
        {
            simulations.forEach { simulation -> simulation.step( deltaTime ) }
        }
    }

    override fun report(): ISimulationReport
    {
        return object: ISimulationReport
        {
            override fun periodControl(): IPeriodReport
            {
                return periodSimulation.report()
            }
        }
    }
}