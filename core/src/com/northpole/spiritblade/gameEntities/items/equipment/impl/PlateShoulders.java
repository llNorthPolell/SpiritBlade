package com.northpole.spiritblade.gameEntities.items.equipment.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.northpole.spiritblade.controllers.impl.CharacterAnimationController;
import com.northpole.spiritblade.gameEntities.items.equipment.BaseShoulderArmor;

public class PlateShoulders extends BaseShoulderArmor {

	public PlateShoulders(int id) {
		super(id);
        this.texture = new Texture(Gdx.files.internal("items/equipment/male/phoenix_shoulders.png"));
        this.animationController = new CharacterAnimationController(sb, texture);
	}

}
