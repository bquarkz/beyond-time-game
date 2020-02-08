/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.nicge.utils.fsmachine;

public interface IFiniteState
{
	/**
	 * Invocado quando o "state" eh selecionado do conjunto e inserido na pilha de execucao.
	 * Nomalmente invocado apos uma chamada via "switchTo".
	 */
	void start();
	
	/**
	 * Invocado quando o "state" eh removido da pilha de execucao.
	 * Nomalmente invocado apos uma chamada via "switchTo".
	 */
	void stop();
	
//	/**
//	 * A execucao propriamente dita quando chamado de dentro da pilha de exececao.
//	 */
//	boolean state_onExecute();
	
	/**
	 * Quando um novo "state" for chamado via "plug". E o atual for posto para "hibernar". 
	 */
	void hibernate();
	
	/**
	 * Quando o "state" atual executar o "unplug". O "state" armazenado na pilha de execu��o "acorada".
	 */
	void wakeup();
	
//	/**
//	 * seta a maquina que o "state" estarah usando.
//	 * @param machine
//	 */
//	void setMachine( FSMachine< ? extends FiniteState > machine );
}
