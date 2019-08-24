package com.northpole.spiritblade.controllers;

import com.badlogic.gdx.math.Matrix4;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.items.Equipment;

public interface EquipmentController {
	public void equip(Equipment equipment);
	public void unequip(Equipment equipment);
	public void renderEquips(float x, float y, Direction direction, CharacterState characterState);
	public void setProjectionMatrix(Matrix4 projection);
}
