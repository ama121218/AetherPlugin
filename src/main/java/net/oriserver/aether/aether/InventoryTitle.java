package net.oriserver.aether.aether;

import org.bukkit.ChatColor;

public enum InventoryTitle {
    SPEED_SELECT("Speed Select"),
    HOME("Home"),
    ATHLETIC_TELEPORT("Athletic Teleport"),
    MINIGAME("MiniGame"),
    APPEARANCE("Appearance"),
    PARTICLE("Particle"),
    HEADBLOCK("HeadBlock"),
    BADGE("Badge"),
    SETTING("Setting"),
    GIVE_ITEM("Give Item"),
    SAVE_ITEM("Save Item"),
    DELETE_SAVE_ITEM(ChatColor.DARK_RED + "Delete Save Item"),
    SELECT_SAVE_ITEM_1(ChatColor.YELLOW + "Select Save Item 1"),
    SELECT_SAVE_ITEM_2(ChatColor.YELLOW + "Select Save Item 2"),
    ADMIN_SAVE_ITEM(ChatColor.LIGHT_PURPLE + "Admin Save Item"),
    ADMIN_DELETE_SAVE_ITEM(ChatColor.DARK_RED + "Admin Delete Save Item"),
    SAVE_TELEPORT("Save Teleport"),
    DELETE_SAVE_TELEPORT(ChatColor.DARK_RED + "Delete Save Teleport"),
    SELECT_SAVE_TELEPORT_1(ChatColor.YELLOW + "Select Save Teleport 1"),
    SELECT_SAVE_TELEPORT_2(ChatColor.YELLOW + "Select Save Teleport 2"),
    ADMIN_SAVE_TELEPORT(ChatColor.LIGHT_PURPLE + "Admin Save Teleport"),
    ADMIN_DELETE_SAVE_TELEPORT(ChatColor.DARK_RED + "Admin Delete Save Teleport"),
    PHONE_SETTING("Phone Setting"),
    PHONE_APPEARANCE("Phone Appearance"),
    PHONE_PARTITION_1("Phone Partition 1"),
    PHONE_PARTITION_2("Phone Partition 2");

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
