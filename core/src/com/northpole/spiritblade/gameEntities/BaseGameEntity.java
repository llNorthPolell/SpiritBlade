package com.northpole.spiritblade.gameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.enums.Direction;

public abstract class BaseGameEntity implements GameEntity {
	protected Vector3 position;
	protected Direction direction;
	protected SpriteBatch sb;
	protected Sprite sprite;
    protected Texture texture;
    protected Vector3 destination;
    
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public Vector3 getPosition() {
		return this.position;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDestination(Vector3 destination) {
		this.destination = destination;
	}
	
	public Vector3 getDestination() {
		return this.destination;
	}
	
	
	public void render() {
        sb.begin();
        sprite.setPosition(0, 0);
        sprite.draw(sb);
        sb.end();
	}
	
	public void dispose() {
		sb.dispose();
		texture.dispose();
	}
}
