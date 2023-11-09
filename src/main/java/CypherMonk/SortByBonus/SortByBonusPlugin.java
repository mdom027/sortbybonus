package CypherMonk.SortByBonus;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.NavigationButton;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Sort By Bonus"
)
public class SortByBonusPlugin extends Plugin
{
	private static final Map<Integer, Integer> EMPTY_MAP = new HashMap<>();
	public static final String CONFIG_GROUP = "sortbybonus";
	@Inject
	private Client client;

	@Inject
	private SortByBonusConfig config;



	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	SortByBonusConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SortByBonusConfig.class);
	}

	private final Map<Integer, Integer> inventoryHashMap = new HashMap<>();
	private NavigationButton navButton;
	//private BankedCalculatorPanel panel;
	private boolean prepared = false;
	private long accountHash = -1;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}
}
