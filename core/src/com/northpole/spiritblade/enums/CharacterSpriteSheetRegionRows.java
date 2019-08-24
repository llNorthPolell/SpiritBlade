package com.northpole.spiritblade.enums;

public enum CharacterSpriteSheetRegionRows {
	WAVE_NORTH(0),
	WAVE_WEST(1),
	WAVE_SOUTH(2),
	WAVE_EAST(3),
	POLEARM_NORTH(4),
	POLEARM_WEST(5),
	POLEARM_SOUTH(6),
	POLEARM_EAST(7),
	WALK_NORTH(8),
	WALK_WEST(9),
	WALK_SOUTH(10),
	WALK_EAST(11),
	SLASH_NORTH(12),
	SLASH_WEST(13),
	SLASH_SOUTH(14),
	SLASH_EAST(15),
	BOW_NORTH(16),
	BOW_WEST(17),
	BOW_SOUTH(18),
	BOW_EAST(19),
	OTHER(20)
	;
	
    private final int id;
    CharacterSpriteSheetRegionRows(int id) { this.id = id; }
    public int getValue() { return id; }
}
