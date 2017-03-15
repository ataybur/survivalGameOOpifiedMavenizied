package com.ataybur.survival.survival.pojo;

import com.ataybur.survival.survival.pojo.base.Character;

public class Hero extends Character {

	public Hero() {
		super();
	}

	public Hero(Hero instance) {
		super(instance);
	}

	@Override
	public String toString() {
		return "Hero [getHp()=" + getHp() + ", getAttackPoint()=" + getAttackPoint() + "]";
	}

}
