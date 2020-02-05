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
package com.intrepid.nicge.utils.reflections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class InheritanceHelper {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************

	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************

	private List< Class< ? > > extendsList;
	private Set< Class< ?  > > implementsSet;
	private Class<?> classChild;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public InheritanceHelper( Class< ? > classChild ) {
		this.classChild = classChild;
		loadInheritance( classChild );
	}
	
	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	/**
	 * 
	 * @param classChild
	 * @return 
	 */
	private void loadInheritance( Class< ? > classChild ) {
		if( classChild == null ) throw new IllegalArgumentException(); //unchecked

		this.extendsList = new ArrayList<>();
		this.implementsSet = new HashSet<>();
		
		if( classChild.isInterface() ) {
			inheritanceInterfaceAdd( classChild, implementsSet );
		} else {
			inheritanceClassAdd( classChild, extendsList );

			for( Class< ? > i : classChild.getInterfaces() ) {
				inheritanceInterfaceAdd( i, implementsSet );
			}

			for( Class< ? > c : extendsList ) {
				for( Class< ? > i : c.getInterfaces() ) {
					inheritanceInterfaceAdd( i, implementsSet );
				}
			}
		}
	}
	
	/**
	 * 
	 * @param child
	 * @param classes
	 */
	public void inheritanceInterfaceAdd( Class< ? > child, Collection< Class< ? > > classes ) {
		classes.add( child );
		Class<?>[] interfaces = child.getInterfaces();
		if( interfaces.length == 0 ) return;
		else {
			for( Class< ? > i : interfaces ) {
				inheritanceInterfaceAdd( i, classes );
			}
		}
	}

	/**
	 * 
	 * @param classChild
	 * @param list
	 */
	private void inheritanceClassAdd( Class<?> classChild, Collection< Class< ? > > list ) {
		Class< ? > clazz = classChild;
		do {
			list.add( clazz );
			clazz = clazz.getSuperclass();
		} while( clazz != Object.class );
		list.add( clazz ); // Object.class
	}
	
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public final boolean isChildOf( Class< ? > parent ) {
		if( extendsList.contains( parent ) ) return true;
		if( implementsSet.contains( parent ) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		StringBuilder tab = new StringBuilder();
		
		sb.append( "Class: " + classChild.getSimpleName() + 
				( this.isChildOf( RuntimeException.class ) ? " (un" : "(" ) + "checked)" + "\n" );
		for( int i = 0; i < extendsList.size(); i++ ) {
			int index = extendsList.size() - i - 1;
			tab.append( "> " );
			sb.append( tab.toString() + extendsList.get( index ).getSimpleName() + "\n" );
		}
		sb.append( "\n * Interfaces:\n" );
		for( Class< ? > c : implementsSet ) {
			sb.append( c.getSimpleName() + ", " );
		}
		sb.append( "\n\n" );
			
		return sb.toString();
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public Collection< Class< ? > > getInterfaces() {
		return Collections.unmodifiableCollection( implementsSet );
	}
	
	public Collection< Class< ? > > getClasses() {
		return Collections.unmodifiableCollection( extendsList );
	}
	
	public Class< ? > getClassChild() {
		return classChild;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************
}
