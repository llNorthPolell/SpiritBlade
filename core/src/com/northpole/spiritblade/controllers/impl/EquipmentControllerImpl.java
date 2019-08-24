package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.math.Matrix4;
import com.northpole.spiritblade.controllers.EquipmentController;
import com.northpole.spiritblade.enums.CharacterState;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.items.Equipment;
import com.northpole.spiritblade.gameEntities.items.equipment.BodyArmor;
import com.northpole.spiritblade.gameEntities.items.equipment.Footwear;
import com.northpole.spiritblade.gameEntities.items.equipment.Handwear;
import com.northpole.spiritblade.gameEntities.items.equipment.Headgear;
import com.northpole.spiritblade.gameEntities.items.equipment.LegArmor;
import com.northpole.spiritblade.gameEntities.items.equipment.ShoulderArmor;
import com.northpole.spiritblade.gameEntities.items.equipment.Weapon;


public class EquipmentControllerImpl implements EquipmentController {
	private Weapon weapon;
	private Headgear head;
	private BodyArmor body;
	private Handwear hands;
	private ShoulderArmor shoulders;
	private LegArmor legs;
	private Footwear foot;
	
	public EquipmentControllerImpl() {
	}
	
	
	public void equip(Equipment equipment) {
		if (equipment instanceof Weapon) {
			if (this.weapon!=null) unequip(this.weapon);
			this.weapon = (Weapon) equipment;
		}
		else if (equipment instanceof Headgear){
			if (this.head!=null) unequip(this.head);
			this.head = (Headgear) equipment;
		}
		else if (equipment instanceof BodyArmor) {
			if (this.body!=null) unequip(this.body);
			this.body = (BodyArmor) equipment;
		}
		else if (equipment instanceof Handwear) {
			if (this.hands!=null) unequip(this.hands);
			this.hands = (Handwear) equipment;
		}
		else if (equipment instanceof ShoulderArmor) {
			if (this.shoulders!=null) unequip(this.shoulders);
			this.shoulders = (ShoulderArmor) equipment;
		}
		else if (equipment instanceof LegArmor) {
			if (this.legs!=null) unequip(this.legs);
			this.legs = (LegArmor) equipment;
		}
		else if (equipment instanceof Footwear) {
			if (this.foot!=null) unequip(this.foot);
			this.foot = (Footwear) equipment;
		}
		equipment.setEquippedStatus(true);
	}

	public void unequip(Equipment equipment) {
		if (equipment instanceof Weapon)
			weapon = null;	
		if (equipment instanceof Headgear)
			head = null;
		if (equipment instanceof BodyArmor)
			body = null;
		if (equipment instanceof Handwear)
			hands = null;
		if (equipment instanceof ShoulderArmor)
			shoulders = null;
		if (equipment instanceof LegArmor)
			legs = null;	
		if (equipment instanceof Footwear)
			foot = null;	
		equipment.setEquippedStatus(false);
	}
	
	public void renderEquips(float x, float y, Direction direction, CharacterState characterState) {
		if(foot!=null) foot.draw(x,y,direction,characterState);
		if(legs!=null) legs.draw(x,y,direction,characterState);	
		if(body!=null) body.draw(x,y,direction,characterState);
		if(hands!=null) hands.draw(x,y,direction,characterState);
		if(shoulders!=null) shoulders.draw(x,y,direction,characterState);
		if(head!=null) head.draw(x,y,direction,characterState);
		if(weapon!=null) weapon.draw(x,y,direction,characterState);
	

	}
	
	public void setProjectionMatrix(Matrix4 projection) {
		if(weapon!=null) weapon.setProjectionMatrix(projection);
		if(head!=null) head.setProjectionMatrix(projection);
		if(body!=null) body.setProjectionMatrix(projection);
		if(hands!=null) hands.setProjectionMatrix(projection);
		if(shoulders!=null) shoulders.setProjectionMatrix(projection);
		if(legs!=null) legs.setProjectionMatrix(projection);		
		if(foot!=null) foot.setProjectionMatrix(projection);
	}

}
