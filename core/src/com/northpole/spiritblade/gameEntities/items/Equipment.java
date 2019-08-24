package com.northpole.spiritblade.gameEntities.items;

import com.badlogic.gdx.math.Matrix4;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;

public interface Equipment extends Item {
	public void setEquippedStatus(boolean equipStatus);
	public boolean getEquipped();
	public void draw(float x, float y, Direction direction, CharacterState characterState);
	public void setProjectionMatrix(Matrix4 projection);
}
