package com.github.ascpixi.stg.listener;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

/**
 * Handles swinging through grass.
 */
public class GrassSwingListener implements Listener {
    /**
     * Checks if the specified material is either an axe or a sword.
     * @param material The target material.
     * @return A value indicating whether the specified material is a sword.
     */
    boolean isWeapon(Material material){
        switch (material){
            // Swords
            case WOODEN_SWORD:
            case STONE_SWORD:
            case GOLDEN_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD:
            case NETHERITE_SWORD:
            // Axes
            case WOODEN_AXE:
            case STONE_AXE:
            case GOLDEN_AXE:
            case IRON_AXE:
            case DIAMOND_AXE:
            case NETHERITE_AXE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if the specified material is a block without a collider hitbox,
     * and takes up two blocks.
     * @param material The target material.
     * @return A value indicating whether the specified material is a double-block.
     */
    boolean isDoubleBlock(Material material){
        switch (material){
            case TALL_GRASS:
            case SUNFLOWER:
            case LARGE_FERN:
            case ROSE_BUSH:
            case LILAC:
            case PEONY:
                return true;
            default:
                return false;
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event){
        Player p = event.getPlayer();
        if(p.getEquipment() == null) return;

        World world = p.getWorld();
        ItemStack weapon = p.getEquipment().getItemInMainHand();

        if(
            p.getAttackCooldown() == 1f &&
            isDoubleBlock(event.getBlock().getType()) &&
            isWeapon(weapon.getType())
        ){
            RayTraceResult result = world.rayTraceEntities(
                p.getEyeLocation(),
                p.getLocation().getDirection(),
                2,
                1f,
                (e) -> e.getType().isAlive() && e != p
            );

            if(result == null) return; // no entity was hit

            // this should never occur!
            // all entity types that return true on "isAlive()" should represent
            // entities that inherit LivingEntity; this is only to ensure we
            // don't get a ClassCastException
            if(!(result.getHitEntity() instanceof LivingEntity)) return;

            LivingEntity hit = (LivingEntity)result.getHitEntity();
            p.attack(hit);
        }
    }
}
