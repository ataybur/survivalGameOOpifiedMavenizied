package com.ataybur.survival.survival.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import com.ataybur.survival.survival.enums.LineTypes;

public class LineChecker {

	private String line;

	public LineChecker(String line) {
		this.line = line;
	}

	public LineParser parseForLineType() {
		Optional<LineTypes> lineType = Arrays //
				.asList(LineTypes.values()) //
				.stream() //
				.filter(lineTypes -> Pattern //
						.compile(lineTypes.getRegex()) //
						.matcher(line) //
						.find()) //
				.findFirst();
		return new LineParser(lineType, line);
	}
}
