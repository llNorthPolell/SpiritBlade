package com.northpole.spiritblade.gameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.AnimationController;
import com.northpole.spiritblade.controllers.MapController;
import com.northpole.spiritblade.controllers.impl.GameController;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;

public abstract class BaseGameCharacter extends BaseGameEntity implements GameCharacter {
	protected MapController mapController;
	protected AnimationController animationController;
	protected CharacterState characterState;
	protected String gender;
	protected Collider collider;
	protected float stateTime;
	protected float movespeed;
	
	
	public static int SPRITE_WIDTH = 64;
	public static int SPRITE_HEIGHT = 64;
	
	public static int COLLIDER_OFFSETX = SPRITE_WIDTH/4+SPRITE_WIDTH/16;
	public static int COLLIDER_OFFSETY = 0;
	
	public BaseGameCharacter(Vector3 position) {
       super(position);
       this.characterState = CharacterState.IDLE;
       mapController = GameController.instance.getMapController();
	}
	
	public void setPosition(Vector3 position) {
		this.position = new Vector3(position);
		if (this.collider!=null) this.collider.setPosition(new Vector3(position).add(SPRITE_WIDTH/4,0,0));
	}
	
	public void move(Direction direction,float delta) {
		switch (direction) {
		case WEST:
			this.position.x -= delta;
			break;
		case NORTH:
			this.position.y += delta;		
			break;
		case EAST:
			this.position.x += delta;
			break;
		case SOUTH:
			this.position.y -= delta;
			break;
		}
		
	}

	protected void setDirection(double angle) {
		this.direction = (angle > 135 && angle <= 225)? Direction.WEST
				:(angle > 45 && angle <= 135)? Direction.NORTH 
				:((angle > 315 && angle <= 360) || (angle > 0 && angle <= 45))? Direction.EAST
				:(angle > 225 && angle <= 315)? Direction.SOUTH 
				:this.direction;
	}
	
	
	protected double calcRotation() {
		float deltaX = destination.x-position.x;
		float deltaY = destination.y-position.y;
		double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return (angle < 0)? 360+angle: angle;
	}
	
	public CharacterState getCharacterState() {
		return this.characterState;
	}
	
	
	public String getGender() {
		return this.gender;
	}
	


	public Collider getCollider() {
		return this.collider;
	}

	public void applyNormalForce(Collider incomingCollider) {
        float deltaTime = Gdx.graphics.getDeltaTime();
		float stepSize = this.movespeed * Math.min(deltaTime,1/60f);
		if (characterState == CharacterState.MOVING && incomingCollider != null) {
	       setPosition(new Vector3(
	        	(this.direction==Direction.WEST)? this.position.add(stepSize,0,0)
	        		:(this.direction==Direction.EAST)? this.position.sub(stepSize,0,0)
	        		:(this.direction==Direction.NORTH)? this.position.sub(0,stepSize,0)
	        		:(this.direction==Direction.SOUTH)? this.position.add(0,stepSize,0)
	        				:this.position));

	        setDestination(this.position);
        }   
		

	}
	

	public float getMoveSpeed() {
		return movespeed;
	}
	
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		float delta = this.movespeed * Math.min(deltaTime,1/60f);

		stateTime += deltaTime;
		
		Collider incomingCollider = this.collider.getCollision(mapController.getNearbyColliders(this.position,100f),delta,this.direction); 
		if (incomingCollider!=null) applyNormalForce(incomingCollider);//setDestination(new Vector3(this.position));
		
		this.characterState=(Math.abs((int)position.x-(int)destination.x) < delta && 
				Math.abs((int)position.y-(int)destination.y) < delta)? 
				CharacterState.IDLE: CharacterState.MOVING;

		if (this.characterState==CharacterState.MOVING) {
			setDirection(calcRotation());

			move(this.direction, delta);
		}
		
        sb.begin();
        sb.draw(animationController.getFrame(this.direction, this.characterState,stateTime),position.x, position.y);
        sb.end();
	}
	

}
