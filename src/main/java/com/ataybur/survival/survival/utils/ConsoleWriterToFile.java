package com.ataybur.survival.survival.utils;

import java.util.ArrayList;
import java.util.Optional;

import com.ataybur.survival.survival.pojo.Context;

public class ConsoleWriterToFile extends WriterToFile {
	private Optional<Context> context;

	public ConsoleWriterToFile(Optional<Context> context) {
		super();
		this.context = context;
	}

	@Override
	public ConsoleWriterToFile prepareFile() {
		this.messages = context.map(Context::getConsole) //
				.orElse(new ArrayList<String>());
		return this;
	}

}
