package ouch.study.fpe.domain.rule;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class OrigamiFoldability extends AbstractRule<AngleUnitFlapPattern> {
	@Override
	public boolean holds(AngleUnitFlapPattern pattern) {
		// TODO 自動生成されたメソッド・スタブ
		return pattern.isProbablyFoldable();
	}
}
