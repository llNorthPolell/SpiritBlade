package com.northpole.spiritblade.gameEntities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.AnimationController;
import com.northpole.spiritblade.controllers.EquipmentController;
import com.northpole.spiritblade.controllers.impl.CharacterAnimationController;
import com.northpole.spiritblade.controllers.impl.EquipmentControllerImpl;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.items.Equipment;

public class Player extends BaseGameCharacter implements GameCharacter  {
	private AnimationController animationController;
	private EquipmentController equipmentController;
	
	private float stateTime;
	private float movespeed;
	
	public static float STEP_SIZE = 1f;
	
	public static int SPRITE_WIDTH = 64;
	public static int SPRITE_HEIGHT = 64;
	
	public static int COLLIDER_OFFSETX = SPRITE_WIDTH/4+SPRITE_WIDTH/16;
	public static int COLLIDER_OFFSETY = 0;
	
	private Collider collider;
	
	
	private String gender;
	
	public Player(Vector3 position) {
		this.gender = "Male";
		this.sb = new SpriteBatch();
        this.texture = new Texture(Gdx.files.internal("characters/"+this.gender+"Base1.png"));
        
        this.direction = Direction.SOUTH;
        this.characterState = CharacterState.IDLE;
        
        this.position = new Vector3(position);
        this.destination= this.position;
        
        this.animationController = new CharacterAnimationController(sb, texture);
        
        this.movespeed = 203f;
        
        this.collider = new BoxCollider(new Rectangle(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,SPRITE_WIDTH/2.5f,SPRITE_HEIGHT/2.5f));

        
        this.equipmentController = new EquipmentControllerImpl();
        
	}
	
	
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		float stepSize = this.movespeed * Math.min(deltaTime,1/60f);
		
		System.out.println(stepSize);
		stateTime += deltaTime;
		
		this.characterState=(Math.abs((int)position.x-(int)destination.x) < stepSize && 
				Math.abs((int)position.y-(int)destination.y) < stepSize)? 
				CharacterState.IDLE: CharacterState.MOVING;

		if (this.characterState==CharacterState.MOVING) {
			setDirection(calcRotation());

			move(this.direction, stepSize);
		}
		
        sb.begin();
        sb.draw(animationController.getFrame(this.direction, this.characterState,stateTime),position.x, position.y);
        sb.end();
        
        equipmentController.renderEquips(this.position.x, this.position.y, this.direction, this.characterState);
	}

	private void move(Direction direction,float stepSize) {
		switch (direction) {
		case WEST:
			this.position.x -= stepSize;
			break;
		case NORTH:
			this.position.y += stepSize;		
			break;
		case EAST:
			this.position.x += stepSize;
			break;
		case SOUTH:
			this.position.y -= stepSize;
			break;
		}
		this.collider.setPosition(new Vector3(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,0));
	}

	public void applyNormalForce(Collider incomingCollider) {
        float deltaTime = Gdx.graphics.getDeltaTime();
		float stepSize = this.movespeed * Math.min(deltaTime,1/60f);
		if (characterState == CharacterState.MOVING && incomingCollider != null) {
	       setPosition(
	        	(this.direction==Direction.WEST)? this.getPosition().add(stepSize,0,0)
	        		:(this.direction==Direction.EAST)? this.getPosition().sub(stepSize,0,0)
	        		:(this.direction==Direction.NORTH)? this.getPosition().sub(0,stepSize,0)
	        		:(this.direction==Direction.SOUTH)? this.getPosition().add(0,stepSize,0)
	        				:this.getPosition());

	        setDestination(this.position);
        }   
	}
	
	
	public void setPosition(Vector3 position) {
		this.position = new Vector3(position);
		if (this.collider!=null) this.collider.setPosition(new Vector3(position).add(SPRITE_WIDTH/4,0,0));
	}
	
	private void setDirection(double angle) {
		this.direction = (angle > 135 && angle <= 225)? Direction.WEST
				:(angle > 45 && angle <= 135)? Direction.NORTH 
				:((angle > 315 && angle <= 360) || (angle > 0 && angle <= 45))? Direction.EAST
				:(angle > 225 && angle <= 315)? Direction.SOUTH 
				:this.direction;
	}
	
	private double calcRotation() {
		float deltaX = destination.x-position.x;
		float deltaY = destination.y-position.y;
		double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return (angle < 0)? 360+angle: angle;
	}
	

	public void setProjectionMatrix(Matrix4 projection) {
		this.sb.setProjectionMatrix(projection);
		this.equipmentController.setProjectionMatrix(projection);
	}


	public CharacterState getCharacterState() {
		return this.characterState;
	}


	public Collider getCollider() {
		return this.collider;
	}

	public float getMoveSpeed() {
		return movespeed;
	}

	public String getGender() {
		return this.gender;
	}
	
	
	public void equip(Equipment equipment){
		equipmentController.equip(equipment);
	}
	
	public void unequip(Equipment equipment) {
		equipmentController.unequip(equipment);
	}
}
