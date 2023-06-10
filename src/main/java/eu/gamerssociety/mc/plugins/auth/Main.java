package eu.gamerssociety.mc.plugins.auth;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import eu.gamerssociety.mc.utilities.DatabaseClient.DatabaseClient;
import eu.gamerssociety.mc.utilities.UserCoordinator.UserCoordinator;

public class Main extends JavaPlugin implements Listener {

	DatabaseClient dbClient = DatabaseClient.Get();
	UserCoordinator userCoordinator = UserCoordinator.Get();

	private void loginPlayer(UUID player) {
		userCoordinator.allowLogin(player);
		userCoordinator.initiatePingpong(player);
		userCoordinator.welcomePlayer(player);
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		UUID playerId = event.getPlayer().getUniqueId();
		boolean playerExists = userCoordinator.checkIfPlayerExists(playerId);
		boolean playerVerified = userCoordinator.checkIfPlayerVerified(playerId);
		boolean playerHasMFA = userCoordinator.userHasMFA(playerId);

		boolean playerAllowed = true;
		if (playerAllowed && !playerExists) {
			playerAllowed = false;
		}
		if (playerAllowed && !playerVerified) {
			playerAllowed = false;
		}
		boolean mfaSuccess = true;
		if (playerAllowed && playerHasMFA) {
			mfaSuccess = userCoordinator.requestMFA(playerId);
		}
		if (playerAllowed && mfaSuccess) {
			loginPlayer(playerId);
		} else {
			userCoordinator.kickPlayer(playerId);
		}
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		dbClient.connect();
		dbClient.loadRegisteredPlayers();
		userCoordinator.openRegistration();
		userCoordinator.openSignIn();
		userCoordinator.openVerification();
	}

}
