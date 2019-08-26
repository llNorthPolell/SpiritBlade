package com.northpole.spiritblade.util;

import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.enums.Direction;

public interface Movable {
	public void move(Direction direction, float delta);
	
	public void setDestination(Vector3 destination);
}
