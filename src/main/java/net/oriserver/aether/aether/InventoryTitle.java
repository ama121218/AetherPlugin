package net.oriserver.aether.aether;

public enum InventoryTitle {
    HOME("Home"),
    ATHLETIC_TELEPORT("Athletic_Teleport"),
    MINIGAME("MiniGame"),
    PARTICLE("Particle"),
    HEADBLOCK("HeadBlock"),
    BADGE("Badge"),
    SETTING("Setting");

    private String title;

    InventoryTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    // StringからTitle enumへの変換メソッド
    public static InventoryTitle fromString(String text) {
        for (InventoryTitle t : InventoryTitle.values()) {
            if (t.getTitle().equalsIgnoreCase(text)) {
                return t;
            }
        }
        return null;
    }
}
