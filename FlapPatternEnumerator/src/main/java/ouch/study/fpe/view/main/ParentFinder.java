package ouch.study.fpe.view.main;

import java.awt.Component;
import java.awt.Container;

public interface ParentFinder {

	public abstract Container find(Class<? extends Object> parentClass, Component c);

}