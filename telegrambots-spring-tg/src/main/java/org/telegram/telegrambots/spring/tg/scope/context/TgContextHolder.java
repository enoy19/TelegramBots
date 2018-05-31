package org.telegram.telegrambots.spring.tg.scope.context;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.HashMap;
import java.util.Map;

/**
 * The Telegram context holder. It keeps {@link ThreadLocal} references of all {@link TgContext}s
 *
 * @author enoy19
 */
public class TgContextHolder {

	private static final Map<Long, TgContext> tgUserContexts = new HashMap<>();
	private static final ThreadLocal<TgContext> tgContextThreadLocal = new ThreadLocal<>();

	public static TgContext currentContext() {
		return tgContextThreadLocal.get();
	}

	public static void setupContext(AbsSender absSender, String absSenderName, Message message) {
		User user = message.getFrom();
		Chat chat = message.getChat();
		Long chatId = chat.getId();

		if (!tgUserContexts.containsKey(chatId)) {
			TgContext value = new TgContext();
			value.setAbsSender(absSender);
			value.setAbsSenderName(absSenderName);
			value.setUser(user);
			value.setChat(chat);
			tgUserContexts.put(chatId, value);
		}

		TgContext tgContext = tgUserContexts.get(chatId);

		tgContextThreadLocal.set(tgContext);
	}


}
