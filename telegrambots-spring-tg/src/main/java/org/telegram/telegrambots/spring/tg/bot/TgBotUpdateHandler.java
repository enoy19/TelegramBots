package org.telegram.telegrambots.spring.tg.bot;

import org.telegram.telegrambots.spring.tg.bot.TgBotMessageHandler.TgBotMessageHandleException;
import org.telegram.telegrambots.spring.tg.scope.context.TgContext;
import org.telegram.telegrambots.spring.tg.scope.context.TgContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * handles {@link Update}s from all bots that are registered in {@link TgBotRegistry}
 * call {@link TgBotUpdateHandler#handleUpdate(AbsSender, Update)} to
 * @see TgBotRegistry
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TgBotUpdateHandler {

	private final TgBotMessageHandler messageHandler;
	private final TgBotRegistry botRegistry;

	public synchronized void handleUpdate(final AbsSender absSender, final Update update) {
		final String botName = botRegistry.getBotName(absSender).getName();

		if (update.hasMessage()) {
			final Message message = update.getMessage();

			Thread processThread = new Thread(() -> {
				TgContextHolder.setupContext(absSender, botName, message);
				TgContext tgContext = TgContextHolder.currentContext();

				try {
					messageHandler.handleMessage(message, tgContext);
				} catch (TgBotMessageHandleException e) {
					send(absSender, tgContext.getChatId(), e.getMessage());
				}
			});
			processThread.setName("TgRequest");
			processThread.setDaemon(true);
			processThread.start();
		}
	}

	private void send(AbsSender sender, long chatId, String message) {
		try {
			sender.execute(new SendMessage(chatId, message));
		} catch (TelegramApiException e) {
			throw new IllegalStateException(e);
		}
	}

}
