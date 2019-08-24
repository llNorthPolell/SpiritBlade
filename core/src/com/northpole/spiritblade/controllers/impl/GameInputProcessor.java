package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.gameEntities.Player;

public class GameInputProcessor implements InputProcessor {
	private OrthographicCamera  camera;
	private Player player;
	
	public GameInputProcessor(OrthographicCamera camera, Player player) {
		this.camera = camera;
		this.player = player;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.W:
			System.out.println("Moving North...");
			camera.translate(0,32);
			break;
		case Input.Keys.A:
			System.out.println("Moving West...");
			camera.translate(-32,0);
			break;	
		case Input.Keys.S:
			System.out.println("Moving South...");
			camera.translate(0,-32);
			break;
		case Input.Keys.D:
			System.out.println("Moving East...");
			camera.translate(32,0);
			break;
		default:
			System.out.println(keycode +"is pressed!");	
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {	
		Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
		Vector3 position = camera.unproject(clickCoordinates);
		player.setDestination(new Vector3(position));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
		Vector3 position = camera.unproject(clickCoordinates);
		player.setDestination(new Vector3(position));
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}

}
