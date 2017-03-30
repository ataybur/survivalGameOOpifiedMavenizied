package com.ataybur.survival.survival.utils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.IntStream;

import com.ataybur.survival.survival.constants.MessageConstants;
import com.ataybur.survival.survival.lambda.PredicateThrowing;
import com.ataybur.survival.survival.pojo.Context;
import com.ataybur.survival.survival.pojo.Enemy;
import com.ataybur.survival.survival.pojo.Field;

public class Game {
	private Optional<Context> context;

	public Game(Context context) {
		super();
		this.context = Optional.of(context);
	}

	public Optional<Context> startGame() throws InstantiationException, IllegalAccessException {
		if (context.isPresent()) {
			PlayingHero playingHero = context //
					.map(Context::getHero) //
					.map(PlayingHero::new) //
					.orElseThrow(NoSuchElementException::new);

			Optional<Field> fieldOpt = context //
					.map(Context::getField);

			SortedMap<Integer, Enemy> enemyMap = fieldOpt //
					.map(Field::getEnemyMap) //
					.orElseThrow(NoSuchElementException::new);

			Integer range = fieldOpt //
					.map(Field::getRange) //
					.orElseThrow(NoSuchElementException::new);

			new ConsolePrinter(context) //
					.printLog(MessageConstants.MESSAGE_1, playingHero.getInstance().getHp());
			
			PredicateThrowing<PlayingCharacter<Enemy>> predicateThrowing = playingHero::isNotAlive;
			
			final Road road = new Road();
			
			IntStream //
					.rangeClosed(1, range) //
					.mapToObj((i) -> {
						road.setMeter(i); //
						return road.getMeter();
					}) //
					.map(enemyMap::get) //
					.map(Optional::ofNullable) //
					.filter(Optional::isPresent)
					.map(Optional::get) //
					.map(PlayingCharacter<Enemy>::new) //
					.filter(predicateThrowing) //
					.findFirst() //
					.map((pe) -> { //
						new ConsolePrinter(context) //
								.printLog(MessageConstants.MESSAGE_2, road.getMeter());
						return context;
					});
			new ConsolePrinter(context) //
					.printLog(MessageConstants.MESSAGE_5);
		}
		return context;
	}
}
