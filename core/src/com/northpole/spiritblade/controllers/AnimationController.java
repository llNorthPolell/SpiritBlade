package com.northpole.spiritblade.controllers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;

public interface AnimationController {
	public TextureRegion getFrame(Direction direction, CharacterState characterState, float stateTime); 
}
