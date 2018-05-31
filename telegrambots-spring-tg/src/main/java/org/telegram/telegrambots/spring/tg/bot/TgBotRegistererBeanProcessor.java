package org.telegram.telegrambots.spring.tg.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

/**
 * automatically registers telegram bots in the spring context ({@link TelegramLongPollingBot} & {@link TelegramWebhookBot}).
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TgBotRegistererBeanProcessor implements BeanPostProcessor {

	private final TgBotRegistry tgBotRegistry;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof AbsSender) {
			AbsSender absSender = (AbsSender) bean;

			final String botToken;
			if (bean instanceof TelegramLongPollingBot) {
				TelegramLongPollingBot bot = (TelegramLongPollingBot) bean;
				botToken = bot.getBotToken();
			} else if (bean instanceof TelegramWebhookBot) {
				TelegramWebhookBot bot = (TelegramWebhookBot) bean;
				botToken = bot.getBotToken();
			} else {
				log.warn("Unknown bot type: {}", bean.getClass().getSimpleName());
				return bean;
			}

			log.info("[REGISTERING] Telegram Bot: {} ({})", beanName, bean.getClass().getSimpleName());
			tgBotRegistry.registerBot(absSender, beanName, botToken);
		}

		return bean;
	}

}
