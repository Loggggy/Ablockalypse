package com.github.JamesNorris.Event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.JamesNorris.External;
import com.github.JamesNorris.Data.ConfigurationData;
import com.github.JamesNorris.Data.Data;
import com.github.JamesNorris.Implementation.GameUndead;
import com.github.JamesNorris.Implementation.ZAPlayerBase;

public class EntityDamage implements Listener {
	private ConfigurationData cd;

	/*
	 * Called when an entity is damaged.
	 * 
	 * Used for reviving a player in last stand.
	 */
	@EventHandler public void EDE(EntityDamageEvent event) {
		if (cd == null)
			cd = External.ym.getConfigurationData();
		Entity e = event.getEntity();
		if (e instanceof Player) {
			Player p = (Player) e;
			if (Data.players.containsKey(p)) {
				ZAPlayerBase zap = Data.players.get(p);
				if (p.getHealth() <= cd.lsthresh && !zap.isInLastStand() && !zap.isInLimbo()) {
					p.setHealth(cd.lsthresh);
					zap.toggleLastStand();
				}
			}
		} else if (Data.isZAMob(e) && (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK)) {
			if (e instanceof Zombie) {
				GameUndead u = (GameUndead) Data.getUndead(e);
				if (u.isFireproof())
					event.setCancelled(true);
			}
		}
	}
}
