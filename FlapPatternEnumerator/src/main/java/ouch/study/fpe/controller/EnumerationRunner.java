package ouch.study.fpe.controller;

import java.util.Collection;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.value.LineType;
import ouch.study.fpe.util.Command;

public interface EnumerationRunner extends Command<Collection<AngleUnitFlapPattern>, Collection<List<LineType>>> {

	/**
	 * Enumerates all foldable patterns by adding lines one by one.
	 * 
	 * @param elements
	 *            depends on implementation of the algorithm.
	 */
	@Override
	public abstract Collection<AngleUnitFlapPattern> run(Collection<List<LineType>> elements);

}