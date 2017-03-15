package com.ataybur.survival.survival.utils;

import com.ataybur.survival.survival.constants.MessageConstants;

public class InputRetriever {
	private String[] args;

	public InputRetriever(String[] args) {
		this.args = args;
	}

	public String retrieveInputFileName() {
		String fileName = null;
		if (args != null && args.length >= 1) {
			fileName = args[0];
		} else {
			throw new RuntimeException(MessageConstants.MESSAGE_7);
		}
		return fileName;
	}

	public String retrieveOutputFileName() {
		String fileName = null;
		if (args != null && args.length >= 2) {
			fileName = args[1];
		} else {
			throw new RuntimeException(MessageConstants.MESSAGE_8);
		}
		return fileName;
	}
}
