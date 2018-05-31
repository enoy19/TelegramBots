package org.telegram.telegrambots.spring.tg.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.HashMap;
import java.util.Map;

/**
 * used to register Telegram Bots. It holds the references to {@link AbsSender}, name (bean name or custom name) and bot token.
 * @see TgBotUpdateHandler
 * @see TgBotRegistererBeanProcessor
 */
@Service
@RequiredArgsConstructor
class TgBotRegistry {

	private static final Map<String, BotReference> botReferenceMapNameToReference = new HashMap<>();
	private static final Map<AbsSender, BotReference> botReferenceMapAbsSenderToReference = new HashMap<>();

	public synchronized BotReference getBotName(AbsSender absSender) {
		return botReferenceMapAbsSenderToReference.get(absSender);
	}

	public synchronized void registerBot(AbsSender absSender, String name, String botToken) {
		BotReference botReference = new BotReference(absSender, name, botToken);
		botReferenceMapNameToReference.put(name, botReference);
		botReferenceMapAbsSenderToReference.put(absSender, botReference);
	}

	public String getBotToken(AbsSender absSender) {
		return botReferenceMapAbsSenderToReference.get(absSender).getToken();
	}

	@Getter
	@Setter(AccessLevel.PRIVATE)
	@RequiredArgsConstructor
	public class BotReference {
		private final AbsSender absSender;
		private final String name;
		private final String token;
	}

}
