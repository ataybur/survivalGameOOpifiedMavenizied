package com.ataybur.survival.survival.utils;

import com.ataybur.survival.survival.constants.MessageConstants;
import com.ataybur.survival.survival.pojo.Context;
import com.ataybur.survival.survival.pojo.Enemy;
import com.ataybur.survival.survival.pojo.Hero;

public class PlayingHero extends PlayingCharacter<Hero> {
	public PlayingHero(Hero instance) {
		super(instance);
	}

	public boolean isAlive(PlayingCharacter<Enemy> currentEnemy) throws InstantiationException, IllegalAccessException {
		Double heroHpDouble = super.getCharacterRemainingHp(currentEnemy);
		boolean result;
		if (heroHpDouble > 0d) {
			getInstance().setHp(heroHpDouble.intValue());
			result = true;
			new ConsolePrinter(Context.getInstance()) //
					.printLog(MessageConstants.MESSAGE_3, currentEnemy.getInstance().getSpecies(),
							heroHpDouble.intValue());
		} else {
			Double enemyHpDouble = currentEnemy.getCharacterRemainingHp(getInstance());
			new ConsolePrinter(Context.getInstance()) //
					.printLog(MessageConstants.MESSAGE_4, currentEnemy.getInstance().getSpecies(),
							enemyHpDouble.intValue());
			result = false;
		}
		return result;
	}
}
