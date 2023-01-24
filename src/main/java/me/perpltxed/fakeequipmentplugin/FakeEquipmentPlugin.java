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
                if (equipmentEvent.getSlot() == EquipmentSlot.HELMET && equipmentEvent.getEquipment().getType() == Material.DIAMOND_HELMET) {
                    equipmentEvent.setEquipment(new ItemStack(Material.LEATHER_HELMET));
                    return true;
                }
                if (equipmentEvent.getSlot() == EquipmentSlot.CHESTPLATE && equipmentEvent.getEquipment().getType() == Material.DIAMOND_CHESTPLATE) {
                    equipmentEvent.setEquipment(new ItemStack(Material.LEATHER_CHESTPLATE));
                    return true;
                }
                if (equipmentEvent.getSlot() == EquipmentSlot.LEGGINGS && equipmentEvent.getEquipment().getType() == Material.DIAMOND_LEGGINGS) {
                    equipmentEvent.setEquipment(new ItemStack(Material.LEATHER_LEGGINGS));
                    return true;
                }
                if (equipmentEvent.getSlot() == EquipmentSlot.BOOTS && equipmentEvent.getEquipment().getType() == Material.DIAMOND_BOOTS) {
                    equipmentEvent.setEquipment(new ItemStack(Material.LEATHER_BOOTS));
                    return true;
                }
                return false;
            }

            @Override
            protected void onEntitySpawn(Player client, LivingEntity visibleEntity) {
                // Remember to change this if you're intercepting a different slot!
                if (EquipmentSlot.HELMET.isEmpty(visibleEntity)) {
                    updateSlot(client, visibleEntity, EquipmentSlot.HELMET);
                }
                if (EquipmentSlot.CHESTPLATE.isEmpty(visibleEntity)) {
                    updateSlot(client, visibleEntity, EquipmentSlot.CHESTPLATE);
                }
                if (EquipmentSlot.LEGGINGS.isEmpty(visibleEntity)) {
                    updateSlot(client, visibleEntity, EquipmentSlot.LEGGINGS);
                }
                if (EquipmentSlot.BOOTS.isEmpty(visibleEntity)) {
                    updateSlot(client, visibleEntity, EquipmentSlot.BOOTS);
                }
            }
        };
    }
}
