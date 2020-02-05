package org.bquarkz.beyondtime.simulator

interface ISimulationControl< REPORT: IReport >
{
    /**
     * delta time in nano
     */
    fun step( deltaTime: Long )

    /**
     * report
     */
    fun report(): REPORT
}