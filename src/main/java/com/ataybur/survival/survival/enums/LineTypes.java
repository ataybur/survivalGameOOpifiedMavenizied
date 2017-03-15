package com.ataybur.survival.survival.enums;

import com.ataybur.survival.survival.constants.RegexConstants;
import com.ataybur.survival.survival.lambda.BiFunctionThrowing;
import com.ataybur.survival.survival.utils.ContextHelper;

public enum LineTypes {
	RANGE(RegexConstants.REGEX_3, ContextHelper::setRange), //
	HP(RegexConstants.REGEX_5, ContextHelper::setCharacterHP), //
	ATTACK_POINT(RegexConstants.REGEX_2, ContextHelper::setCharacterAttackPoint), //
	ENEMY(RegexConstants.REGEX_6, ContextHelper::setEnemy), //
	ENEMY_POSITION(RegexConstants.REGEX_1, ContextHelper::setEnemyPosition);

	private String regex;
	private BiFunctionThrowing<ContextHelper, String[], ContextHelper> setter;

	private LineTypes(String regex, BiFunctionThrowing<ContextHelper, String[], ContextHelper> setter) {
		this.regex = regex;
		this.setter = setter;
	}

	public String getRegex() {
		return regex;
	}

	public BiFunctionThrowing<ContextHelper, String[], ContextHelper> getSetter() {
		return setter;
	}
}
