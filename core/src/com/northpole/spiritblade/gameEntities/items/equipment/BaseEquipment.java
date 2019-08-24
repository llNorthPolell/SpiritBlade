package com.northpole.spiritblade.gameEntities.items.equipment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.northpole.spiritblade.controllers.AnimationController;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.items.Equipment;

public abstract class BaseEquipment implements Equipment{
	protected boolean equipStatus;
	protected int id;
	
	protected AnimationController animationController;

	private float stateTime;
	
	protected SpriteBatch sb;
	protected Texture texture;
	
	
	
	public BaseEquipment(int id) {
		this.id = id;
		this.sb = new SpriteBatch();
	}
	
	public void setEquippedStatus(boolean equipStatus) {
		this.equipStatus = equipStatus;
	}

	public boolean getEquipped() {
		return this.equipStatus;
	}


	public void draw(float x, float y, Direction direction, CharacterState characterState) {
		float deltaTime = Gdx.graphics.getDeltaTime();

		stateTime += deltaTime;

		
        sb.begin();
        sb.draw(animationController.getFrame(direction, characterState,stateTime),x, y);
        sb.end();
	}
	
	public void setProjectionMatrix(Matrix4 projection) {
		this.sb.setProjectionMatrix(projection);
	}
}
