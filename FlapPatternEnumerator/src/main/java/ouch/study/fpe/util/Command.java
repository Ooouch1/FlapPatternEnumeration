package ouch.study.fpe.util;

public interface Command<Result, Input> {

	Result run(Input v);
}
