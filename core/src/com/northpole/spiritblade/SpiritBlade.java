package com.northpole.spiritblade;

import com.badlogic.gdx.Game;
import com.northpole.spiritblade.enums.AppState;
import com.northpole.spiritblade.screens.GameScreen;

public class SpiritBlade extends Game {

	AppState state;
	
	@Override
	public void create () {
		state = AppState.GAME; 
		setScreen(new GameScreen());
	}


}
