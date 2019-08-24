package com.northpole.spiritblade.util;

import com.badlogic.gdx.math.Vector3;

public interface Movable {
	public void move(Vector3 delta);
	public void setDestination(Vector3 destination);
}
