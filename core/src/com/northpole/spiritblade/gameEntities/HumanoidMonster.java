package com.northpole.spiritblade.gameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.impl.CharacterAnimationController;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.util.Attackable;

public class HumanoidMonster extends BaseNonPlayableCharacter implements Attackable{
	public static int SPRITE_WIDTH = 64;
	public static int SPRITE_HEIGHT = 64;
	
	public static int COLLIDER_OFFSETX = SPRITE_WIDTH/4+SPRITE_WIDTH/16;
	public static int COLLIDER_OFFSETY = 0;
	
	public HumanoidMonster(Vector3 position) {
		super(position);
		
        this.texture = new Texture(Gdx.files.internal("characters/SkeletonBase.png"));

        
        this.animationController = new CharacterAnimationController(sb, texture);
        
        this.movespeed = 100f;
        
        this.collider = new BoxCollider(new Rectangle(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,SPRITE_WIDTH/2.5f,SPRITE_HEIGHT/2.5f));

	}

	
	public void move(Direction direction,float delta) {
		super.move(direction, delta);
		this.collider.setPosition(new Vector3(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,0));
	}

	
	public void setPosition(Vector3 position) {
		this.position = new Vector3(position);
		if (this.collider!=null) this.collider.setPosition(new Vector3(position).add(SPRITE_WIDTH/4,0,0));
	}
	

	
	
}
