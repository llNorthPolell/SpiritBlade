package com.northpole.spiritblade.gameEntities;

import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.util.Collidable;
import com.northpole.spiritblade.util.Interactable;
import com.northpole.spiritblade.util.Movable;

public interface GameCharacter extends GameEntity, Interactable, Movable, Collidable{
	public CharacterState getCharacterState();
	public String getGender();
}
