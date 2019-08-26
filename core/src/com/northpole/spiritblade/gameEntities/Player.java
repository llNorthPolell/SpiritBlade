package com.northpole.spiritblade.gameEntities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.EquipmentController;
import com.northpole.spiritblade.controllers.impl.CharacterAnimationController;
import com.northpole.spiritblade.controllers.impl.EquipmentControllerImpl;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.items.Equipment;
import com.northpole.spiritblade.util.Attackable;
import com.northpole.spiritblade.util.Equipable;
import com.northpole.spiritblade.util.Interactable;

public class Player extends BaseGameCharacter implements Equipable, Interactable, Attackable {
	private EquipmentController equipmentController;

	public Player(Vector3 position) {
		super(position);
		this.gender = "Male";
        this.texture = new Texture(Gdx.files.internal("characters/"+this.gender+"Base1.png"));

        
        this.animationController = new CharacterAnimationController(sb, texture);
        
        this.movespeed = 203f;
        
        this.collider = new BoxCollider(new Rectangle(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,SPRITE_WIDTH/2.5f,SPRITE_HEIGHT/2.5f));

        
        this.equipmentController = new EquipmentControllerImpl();
	}
	
	
	public void render() {
		super.render();
        
        equipmentController.renderEquips(this.position.x, this.position.y, this.direction, this.characterState);
	}

	public void move(Direction direction,float delta) {
		super.move(direction, delta);

		this.collider.setPosition(new Vector3(this.position.x+COLLIDER_OFFSETX, this.position.y+COLLIDER_OFFSETY,0));
	}

	public void setProjectionMatrix(Matrix4 projection) {
		super.setProjectionMatrix(projection);
		this.equipmentController.setProjectionMatrix(projection);
	}


	public void equip(Equipment equipment){
		equipmentController.equip(equipment);
	}
	
	public void unequip(Equipment equipment) {
		equipmentController.unequip(equipment);
	}


	
}
