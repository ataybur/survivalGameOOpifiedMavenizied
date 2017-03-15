package com.ataybur.survival.survival.utils;

import java.util.Arrays;
import java.util.Optional;

import com.ataybur.survival.survival.enums.LineTypes;

public class LineInfo {
	private String[] parsedLine;
	private Optional<LineTypes> lineTypes;

	public LineInfo(String[] parsedLine, Optional<LineTypes> lineTypes) {
		super();
		this.parsedLine = parsedLine;
		this.lineTypes = lineTypes;
	}

	public ContextHelper putParsedLineIntoContext(ContextHelper contextHelper) {
		lineTypes.map(LineTypes::getSetter) //
				.ifPresent(func -> func.apply(contextHelper, parsedLine));
		return contextHelper;
	}

	@Override
	public String toString() {
		return "LineInfo [parsedLine=" + Arrays.toString(parsedLine) + ", lineTypes=" + lineTypes + "]";
	}

}
