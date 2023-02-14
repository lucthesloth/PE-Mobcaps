package com.codesloth.pemobcaps;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.jetbrains.annotations.NotNull;

import net.minecraft.server.level.ChunkProviderServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EnumCreatureType;
import net.minecraft.world.level.SpawnerCreature;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;


public class PEMobcaps extends PlaceholderExpansion{
    static final int MAGIC_NUMBER = (int) Math.pow(17.0D, 2.0D);
    @Override
    public @NotNull String getIdentifier() {
        return "PEMobcaps";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Luc";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params){
        //Player is offline
        if(player == null){
            return null;
        }
        
        Player p = player.getPlayer();
        Player target = null;
        if (params.startsWith("p_")){
            target = Bukkit.getPlayer(params.substring(2).split(":")[0]);
            if (target == null) return null;
        }
        String treated = params.split(":")[1];
        World world = target != null ? target.getWorld() : p.getPlayer().getWorld();
        EntityPlayer targetEntityPlayer = ((CraftPlayer) target).getHandle();
        
        WorldServer w = ((CraftWorld) world).getHandle();
        ChunkProviderServer cps = w.k();
        SpawnerCreature.d ss = cps.n();
        int chunks = world == null || w == null || cps == null || ss == null ? 0 : ss.a();
        
        switch(treated){
            case "monster":
                return String.format("%d/%d", ss.b().getOrDefault(EnumCreatureType.a, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "creature":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.b, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "ambient":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.c, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "axolotls":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.d, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "underground_water_creature":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.e, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "water_creature":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.f, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "water_ambient":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.g, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            case "misc":
                return String.format("%d/%d",ss.b().getOrDefault(EnumCreatureType.h, 0), world.getSpawnLimit(SpawnCategory.MONSTER) * chunks/MAGIC_NUMBER);
            
        }
        return null;
    }

}
