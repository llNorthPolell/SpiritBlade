package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.northpole.spiritblade.controllers.AnimationController;
import com.northpole.spiritblade.enums.CharacterSpriteSheetRegionRows;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.Player;

public class CharacterAnimationController implements AnimationController {
	private TextureRegion[][] baseRegions;
	private Animation<TextureRegion> walkSouthAnimation;
	private Animation<TextureRegion> walkWestAnimation;
	private Animation<TextureRegion> walkNorthAnimation;
	private Animation<TextureRegion> walkEastAnimation;

	private Animation<TextureRegion> idleSouthAnimation;
	private Animation<TextureRegion> idleWestAnimation;
	private Animation<TextureRegion> idleNorthAnimation;
	private Animation<TextureRegion> idleEastAnimation;
	
	
	public CharacterAnimationController(SpriteBatch sb, Texture texture) {
		this.baseRegions = TextureRegion.split(texture, Player.SPRITE_WIDTH,Player.SPRITE_HEIGHT);

		walkSouthAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_SOUTH, 1,8);
		walkWestAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_WEST, 1,8);
		walkNorthAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_NORTH, 1,8);
		walkEastAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_EAST, 1,8);
		
		idleSouthAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_SOUTH, 0,0);
		idleWestAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_WEST, 0,0);
		idleNorthAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_NORTH, 0,0);
		idleEastAnimation = loadAnimation(CharacterSpriteSheetRegionRows.WALK_EAST, 0,0);
		
	}

	private Animation<TextureRegion> loadAnimation(CharacterSpriteSheetRegionRows row, int start, int end) {
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for (int i = start ; i <= end; i++) 
			frames.add(new TextureRegion(this.baseRegions[row.getValue()][i]));
		return new Animation<TextureRegion>(0.1f, frames);
	}
	
	public TextureRegion getFrame(Direction direction, CharacterState characterState, float stateTime) {
		TextureRegion currentFrame;
		switch(characterState) {
		case MOVING:
			switch(direction) {
			case SOUTH:
				currentFrame = walkSouthAnimation.getKeyFrame(stateTime, true);
				break;
			case WEST:
				currentFrame = walkWestAnimation.getKeyFrame(stateTime, true);
				break;
			case NORTH:
				currentFrame = walkNorthAnimation.getKeyFrame(stateTime, true);
				break;
			case EAST:
				currentFrame = walkEastAnimation.getKeyFrame(stateTime, true);
				break;
			default:
				currentFrame = idleSouthAnimation.getKeyFrame(stateTime, true);
				break;	
			}
			break;
		case IDLE:
			switch(direction) {
			case SOUTH:
				currentFrame = idleSouthAnimation.getKeyFrame(stateTime, true);
				break;
			case WEST:
				currentFrame = idleWestAnimation.getKeyFrame(stateTime, true);
				break;
			case NORTH:
				currentFrame = idleNorthAnimation.getKeyFrame(stateTime, true);
				break;
			case EAST:
				currentFrame = idleEastAnimation.getKeyFrame(stateTime, true);
				break;
			default:
				currentFrame = idleSouthAnimation.getKeyFrame(stateTime, true);
				break;
			}
			break;	
		default:
			currentFrame = idleSouthAnimation.getKeyFrame(stateTime, true);
			break;
		}
		
		return currentFrame;
	}

}
