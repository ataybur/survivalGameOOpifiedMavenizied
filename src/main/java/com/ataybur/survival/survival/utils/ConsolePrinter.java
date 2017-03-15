package com.ataybur.survival.survival.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ataybur.survival.survival.pojo.Context;

public class ConsolePrinter extends Context {
	private Optional<Context> instance;

	public ConsolePrinter(Optional<Context> instance) {
		this.instance = instance;
	}

	public ConsolePrinter printLog(String str, Object... args) {
		List<String> console = this //
				.instance //
						.map(Context::getConsole) //
						.orElse(new ArrayList<String>());
		String formattedMessage = String.format(str, args);
		console.add(formattedMessage);
		this.instance.get().setConsole(console);
		return this;
	}
}
