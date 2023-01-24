package me.perpltxed.fakeequipmentplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeEquipmentPlugin extends JavaPlugin {
    private ProtocolManager protocolManager;

    public void onLoad() {
        protocolManager = ProtocolLibrary.getProtocolManager();
    }
    @Override
    public void onEnable() {
        new FakeEquipment(this) {
            @Override
            protected boolean onEquipmentSending(EquipmentSendingEvent equipmentEvent) {
                if (equipmentEvent.getSlot() == EquipmentSlot.HELD) {
                    equipmentEvent.setEquipment(new ItemStack(Material.DIAMOND));
                    return true;
                }
                return false;
            }

            @Override
            protected void onEntitySpawn(Player client, LivingEntity visibleEntity) {
                // Remember to change this if you're intercepting a different slot!
                if (EquipmentSlot.HELD.isEmpty(visibleEntity)) {
                    updateSlot(client, visibleEntity, EquipmentSlot.HELD);
                }
            }
        };
    }
}
