package com.ataybur.survival.survival.main;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.ataybur.survival.survival.lambda.FunctionThrowing;
import com.ataybur.survival.survival.lambda.LineInfoCollector;
import com.ataybur.survival.survival.lambda.RunnableThrowing;
import com.ataybur.survival.survival.pojo.Context;
import com.ataybur.survival.survival.utils.ConsoleWriterToFile;
import com.ataybur.survival.survival.utils.ErrorWriterToFile;
import com.ataybur.survival.survival.utils.FileReader;
import com.ataybur.survival.survival.utils.Game;
import com.ataybur.survival.survival.utils.LineChecker;
import com.ataybur.survival.survival.utils.LineParser;

public class App {
	private Optional<Context> context;

	public App() {
		super();
	}

	public void load(File inputFile) {
		handleException(() -> {
			String fileNameInput = inputFile.getAbsolutePath();
			// List<LineInfo> list =
			new FileReader(fileNameInput) //
					.getStream() //
					.map(LineChecker::new) //
					.map(LineChecker::parseForLineType) //
					.map(LineParser::parseLineToInfo) //
					// .collect(Collectors.toList());
					.collect(new LineInfoCollector());
		});
	}

	public void run() {
		context = Context.getInstance();
		FunctionThrowing<Game, Optional<Context>> gameStartGame = Game::startGame;
		context.map(Game::new) //
				.map(gameStartGame);
	}

	public void write(File outputFile) {
		handleException(() -> {
			String fileNameOutput = outputFile.getAbsolutePath() + File.separatorChar + "output.txt";
			new ConsoleWriterToFile(context) //
					.setFileName(fileNameOutput) //
					.prepareFile() //
					.write();
		});
	}

	private void handleException(RunnableThrowing runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				new ErrorWriterToFile() //
						.setException(e) //
						.setFileName(new File("error.log").getAbsolutePath()) //
						.prepareFile() //
						.write();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
