package ouch.study.fpe.domain.rule;

import java.util.LinkedList;

import ouch.study.fpe.domain.rule.lib.LineGap;
import ouch.study.fpe.domain.rule.lib.LineGapCircle;

public interface FlapPatternConverter {

	public abstract LinkedList<LineGap> toLineGaps(Object pattern);

	public abstract LineGapCircle toLineGapCircle(Object pattern);

}